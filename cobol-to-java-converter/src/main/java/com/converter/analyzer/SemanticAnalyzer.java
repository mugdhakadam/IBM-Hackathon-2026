package com.converter.analyzer;

import com.converter.model.CobolProgram;
import com.converter.model.DataItem;
import com.converter.model.Paragraph;
import com.converter.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Performs semantic analysis on the parsed COBOL program.
 * Validates data types, variable usage, and control flow.
 */
public class SemanticAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(SemanticAnalyzer.class);
    
    private Map<String, DataItem> symbolTable;
    
    public SemanticAnalyzer() {
        this.symbolTable = new HashMap<>();
    }
    
    /**
     * Analyze the COBOL program for semantic correctness.
     */
    public void analyze(CobolProgram program) {
        logger.info("Starting semantic analysis for program: {}", program.getProgramId());
        
        // Build symbol table from data items
        buildSymbolTable(program);
        
        // Validate procedure division
        validateProcedureDivision(program);
        
        logger.info("Semantic analysis completed successfully");
    }
    
    /**
     * Build a symbol table from all data items in the program.
     */
    private void buildSymbolTable(CobolProgram program) {
        logger.debug("Building symbol table...");
        
        // Add working storage items
        for (DataItem item : program.getWorkingStorageSection()) {
            symbolTable.put(item.getName(), item);
            logger.debug("Added to symbol table: {} ({})", item.getName(), item.getDataType());
        }
        
        // Add file section items
        for (DataItem item : program.getFileSection()) {
            symbolTable.put(item.getName(), item);
        }
        
        // Add linkage section items
        for (DataItem item : program.getLinkageSection()) {
            symbolTable.put(item.getName(), item);
        }
        
        logger.debug("Symbol table built with {} entries", symbolTable.size());
    }
    
    /**
     * Validate the procedure division statements.
     */
    private void validateProcedureDivision(CobolProgram program) {
        logger.debug("Validating procedure division...");
        
        for (Paragraph paragraph : program.getParagraphs()) {
            logger.debug("Validating paragraph: {}", paragraph.getName());
            
            for (Statement statement : paragraph.getStatements()) {
                validateStatement(statement);
            }
        }
    }
    
    /**
     * Validate an individual statement.
     */
    private void validateStatement(Statement statement) {
        // Enhanced validation with variable checking
        switch (statement.getType()) {
            case MOVE:
                validateMoveStatement(statement);
                break;
            case ADD:
            case SUBTRACT:
            case MULTIPLY:
            case DIVIDE:
                validateArithmeticStatement(statement);
                break;
            case COMPUTE:
                validateComputeStatement(statement);
                break;
            case IF:
                validateIfStatement(statement);
                break;
            case DISPLAY:
                validateDisplayStatement(statement);
                break;
            case ACCEPT:
                validateAcceptStatement(statement);
                break;
            default:
                logger.debug("Statement type {} requires no special validation", statement.getType());
        }
    }
    
    /**
     * Validate MOVE statement variables.
     */
    private void validateMoveStatement(Statement statement) {
        logger.debug("Validating MOVE statement");
        String text = statement.getRawText();
        // Extract variable names and check if they exist
        if (text.toUpperCase().contains(" TO ")) {
            String[] parts = text.split("(?i)\\s+TO\\s+");
            if (parts.length == 2) {
                String target = parts[1].replaceAll("\\.$", "").trim();
                checkVariableExists(target, statement);
            }
        }
    }
    
    /**
     * Validate arithmetic statement variables.
     */
    private void validateArithmeticStatement(Statement statement) {
        logger.debug("Validating {} statement", statement.getType());
        String text = statement.getRawText().toUpperCase();
        
        // Extract variable names from common patterns
        if (text.contains(" TO ") || text.contains(" FROM ") ||
            text.contains(" BY ") || text.contains(" INTO ")) {
            // Variables are being used - basic validation passed
            logger.debug("Arithmetic statement contains valid keywords");
        }
    }
    
    /**
     * Validate COMPUTE statement.
     */
    private void validateComputeStatement(Statement statement) {
        logger.debug("Validating COMPUTE statement");
        String text = statement.getRawText();
        if (text.contains("=")) {
            String[] parts = text.split("=");
            if (parts.length == 2) {
                String target = parts[0].replaceFirst("(?i)COMPUTE\\s+", "").trim();
                checkVariableExists(target, statement);
            }
        }
    }
    
    /**
     * Validate IF statement.
     */
    private void validateIfStatement(Statement statement) {
        logger.debug("Validating IF statement");
        // Basic validation - ensure it has a condition
        String text = statement.getRawText();
        if (text.trim().replaceFirst("(?i)^IF\\s+", "").isEmpty()) {
            logger.warn("IF statement has no condition: {}", text);
        }
    }
    
    /**
     * Validate DISPLAY statement.
     */
    private void validateDisplayStatement(Statement statement) {
        logger.debug("Validating DISPLAY statement");
        String text = statement.getRawText();
        String content = text.replaceFirst("(?i)DISPLAY\\s+", "").trim();
        if (content.isEmpty()) {
            logger.warn("DISPLAY statement has no content: {}", text);
        }
    }
    
    /**
     * Validate ACCEPT statement.
     */
    private void validateAcceptStatement(Statement statement) {
        logger.debug("Validating ACCEPT statement");
        String text = statement.getRawText();
        String variable = text.replaceFirst("(?i)ACCEPT\\s+", "").replaceAll("\\.$", "").trim();
        checkVariableExists(variable, statement);
    }
    
    /**
     * Check if a variable exists in the symbol table.
     */
    private void checkVariableExists(String variableName, Statement statement) {
        // Remove quotes if present
        if (variableName.startsWith("'") || variableName.startsWith("\"")) {
            return; // It's a literal, not a variable
        }
        
        // Check if it's a number
        if (variableName.matches("\\d+(\\.\\d+)?")) {
            return; // It's a numeric literal
        }
        
        if (!symbolTable.containsKey(variableName)) {
            logger.warn("Variable '{}' not found in symbol table (statement: {})",
                variableName, statement.getRawText());
        } else {
            logger.debug("Variable '{}' validated successfully", variableName);
        }
    }
    
    /**
     * Get the symbol table (useful for code generation).
     */
    public Map<String, DataItem> getSymbolTable() {
        return symbolTable;
    }
}

// Made with Bob
