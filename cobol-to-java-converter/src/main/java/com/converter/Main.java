package com.converter;

import com.converter.parser.CobolParser;
import com.converter.analyzer.SemanticAnalyzer;
import com.converter.generator.JavaCodeGenerator;
import com.converter.model.CobolProgram;
import com.converter.model.JavaClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main entry point for the COBOL to Java converter.
 * 
 * Usage: java -jar cobol-to-java-converter.jar <input-cobol-file> <output-directory>
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length < 2) {
            printUsage();
            System.exit(1);
        }

        String inputFile = args[0];
        String outputDir = args[1];

        try {
            logger.info("Starting COBOL to Java conversion...");
            logger.info("Input file: {}", inputFile);
            logger.info("Output directory: {}", outputDir);

            // Step 1: Parse COBOL source code
            logger.info("Step 1: Parsing COBOL source code...");
            CobolParser parser = new CobolParser();
            CobolProgram cobolProgram = parser.parse(Paths.get(inputFile));
            logger.info("Parsing completed. Program ID: {}", cobolProgram.getProgramId());

            // Step 2: Analyze semantics
            logger.info("Step 2: Analyzing semantics...");
            SemanticAnalyzer analyzer = new SemanticAnalyzer();
            analyzer.analyze(cobolProgram);
            logger.info("Semantic analysis completed.");

            // Step 3: Generate Java code
            logger.info("Step 3: Generating Java code...");
            JavaCodeGenerator generator = new JavaCodeGenerator();
            JavaClass javaClass = generator.generate(cobolProgram);
            logger.info("Java code generation completed. Class: {}", javaClass.getClassName());

            // Step 4: Write output
            logger.info("Step 4: Writing output files...");
            Path outputPath = Paths.get(outputDir);
            Files.createDirectories(outputPath);
            generator.writeToFile(javaClass, outputPath);
            logger.info("Conversion completed successfully!");
            logger.info("Output written to: {}", outputPath.resolve(javaClass.getClassName() + ".java"));

        } catch (IOException e) {
            logger.error("I/O error during conversion: {}", e.getMessage(), e);
            System.exit(1);
        } catch (Exception e) {
            logger.error("Error during conversion: {}", e.getMessage(), e);
            System.exit(1);
        }
    }

    private static void printUsage() {
        System.out.println("COBOL to Java Converter");
        System.out.println("=======================");
        System.out.println();
        System.out.println("Usage: java -jar cobol-to-java-converter.jar <input-cobol-file> <output-directory>");
        System.out.println();
        System.out.println("Arguments:");
        System.out.println("  input-cobol-file   : Path to the COBOL source file (.cbl or .cob)");
        System.out.println("  output-directory   : Directory where Java files will be generated");
        System.out.println();
        System.out.println("Example:");
        System.out.println("  java -jar cobol-to-java-converter.jar sample.cbl ./output");
    }
}

// Made with Bob
