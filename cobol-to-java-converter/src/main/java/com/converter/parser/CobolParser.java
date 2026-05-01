package com.converter.parser;

import com.converter.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for COBOL source code.
 * This is a simplified parser that handles basic COBOL constructs.
 * For production use, consider using ANTLR with a complete COBOL grammar.
 */
public class CobolParser {
    private static final Logger logger = LoggerFactory.getLogger(CobolParser.class);
    
    // Regex patterns for COBOL constructs
    private static final Pattern PROGRAM_ID_PATTERN = Pattern.compile("PROGRAM-ID\\.\\s+(\\S+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern AUTHOR_PATTERN = Pattern.compile("AUTHOR\\.\\s+(.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DATA_ITEM_PATTERN = Pattern.compile("^\\s*(\\d{2})\\s+(\\S+)\\s+PIC\\s+([^\\s.]+)(?:\\s+VALUE\\s+(.+?))?\\.", Pattern.CASE_INSENSITIVE);
    private static final Pattern PARAGRAPH_PATTERN = Pattern.compile("^\\s*([A-Z][A-Z0-9-]+)\\.", Pattern.CASE_INSENSITIVE);
    
    public CobolProgram parse(Path filePath) throws IOException {
        logger.info("Parsing COBOL file: {}", filePath);
        
        List<String> lines = Files.readAllLines(filePath);
        CobolProgram program = new CobolProgram();
        
        ParserState state = ParserState.IDENTIFICATION_DIVISION;
        Paragraph currentParagraph = null;
        
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            
            // Skip empty lines and comments
            if (line.isEmpty() || line.startsWith("*")) {
                continue;
            }
            
            // Detect division changes
            if (line.toUpperCase().contains("IDENTIFICATION DIVISION")) {
                state = ParserState.IDENTIFICATION_DIVISION;
                continue;
            } else if (line.toUpperCase().contains("DATA DIVISION")) {
                state = ParserState.DATA_DIVISION;
                continue;
            } else if (line.toUpperCase().contains("WORKING-STORAGE SECTION")) {
                state = ParserState.WORKING_STORAGE;
                continue;
            } else if (line.toUpperCase().contains("PROCEDURE DIVISION")) {
                state = ParserState.PROCEDURE_DIVISION;
                continue;
            }
            
            // Parse based on current state
            switch (state) {
                case IDENTIFICATION_DIVISION:
                    parseIdentificationDivision(line, program);
                    break;
                    
                case WORKING_STORAGE:
                    parseWorkingStorage(line, program);
                    break;
                    
                case PROCEDURE_DIVISION:
                    // Check if this is a paragraph header
                    Matcher paragraphMatcher = PARAGRAPH_PATTERN.matcher(line);
                    if (paragraphMatcher.find() && !line.toUpperCase().startsWith("PERFORM")) {
                        String paragraphName = paragraphMatcher.group(1);
                        currentParagraph = new Paragraph(paragraphName);
                        program.addParagraph(currentParagraph);
                        logger.debug("Found paragraph: {}", paragraphName);
                    } else if (currentParagraph != null) {
                        // Parse statement
                        Statement statement = parseStatement(line);
                        if (statement != null) {
                            currentParagraph.addStatement(statement);
                        }
                    }
                    break;
            }
        }
        
        logger.info("Parsing completed. Program ID: {}", program.getProgramId());
        return program;
    }
    
    private void parseIdentificationDivision(String line, CobolProgram program) {
        Matcher programIdMatcher = PROGRAM_ID_PATTERN.matcher(line);
        if (programIdMatcher.find()) {
            program.setProgramId(programIdMatcher.group(1));
            logger.debug("Found PROGRAM-ID: {}", program.getProgramId());
        }
        
        Matcher authorMatcher = AUTHOR_PATTERN.matcher(line);
        if (authorMatcher.find()) {
            program.setAuthor(authorMatcher.group(1).trim());
            logger.debug("Found AUTHOR: {}", program.getAuthor());
        }
    }
    
    private void parseWorkingStorage(String line, CobolProgram program) {
        Matcher dataItemMatcher = DATA_ITEM_PATTERN.matcher(line);
        if (dataItemMatcher.find()) {
            int level = Integer.parseInt(dataItemMatcher.group(1));
            String name = dataItemMatcher.group(2);
            String picture = dataItemMatcher.group(3);
            String value = dataItemMatcher.groupCount() >= 4 ? dataItemMatcher.group(4) : null;
            
            DataItem item = new DataItem(level, name);
            item.setPictureClause(picture);
            if (value != null) {
                item.setValue(value.replace("'", "").replace("\"", "").trim());
            }
            
            program.addWorkingStorageItem(item);
            logger.debug("Found data item: {} PIC {} VALUE {}", name, picture, value);
        }
    }
    
    private Statement parseStatement(String line) {
        String upperLine = line.toUpperCase();
        
        if (upperLine.startsWith("MOVE")) {
            Statement stmt = new Statement(Statement.StatementType.MOVE, line);
            return stmt;
        } else if (upperLine.startsWith("DISPLAY")) {
            Statement stmt = new Statement(Statement.StatementType.DISPLAY, line);
            return stmt;
        } else if (upperLine.startsWith("ACCEPT")) {
            Statement stmt = new Statement(Statement.StatementType.ACCEPT, line);
            return stmt;
        } else if (upperLine.startsWith("IF")) {
            Statement stmt = new Statement(Statement.StatementType.IF, line);
            return stmt;
        } else if (upperLine.startsWith("PERFORM")) {
            Statement stmt = new Statement(Statement.StatementType.PERFORM, line);
            return stmt;
        } else if (upperLine.startsWith("ADD")) {
            Statement stmt = new Statement(Statement.StatementType.ADD, line);
            return stmt;
        } else if (upperLine.startsWith("SUBTRACT")) {
            Statement stmt = new Statement(Statement.StatementType.SUBTRACT, line);
            return stmt;
        } else if (upperLine.startsWith("MULTIPLY")) {
            Statement stmt = new Statement(Statement.StatementType.MULTIPLY, line);
            return stmt;
        } else if (upperLine.startsWith("DIVIDE")) {
            Statement stmt = new Statement(Statement.StatementType.DIVIDE, line);
            return stmt;
        } else if (upperLine.startsWith("COMPUTE")) {
            Statement stmt = new Statement(Statement.StatementType.COMPUTE, line);
            return stmt;
        } else if (upperLine.startsWith("STOP RUN") || upperLine.startsWith("GOBACK")) {
            Statement stmt = new Statement(Statement.StatementType.STOP, line);
            return stmt;
        }
        
        return null;
    }
    
    private enum ParserState {
        IDENTIFICATION_DIVISION,
        DATA_DIVISION,
        WORKING_STORAGE,
        PROCEDURE_DIVISION
    }
}

// Made with Bob
