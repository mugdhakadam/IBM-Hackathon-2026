package com.converter.utils;

import java.util.regex.Pattern;

/**
 * Utility class for common conversion operations.
 */
public class ConversionUtils {
    
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^-?\\d+(\\.\\d+)?$");
    private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("^[A-Z][A-Z0-9-]*$", Pattern.CASE_INSENSITIVE);
    
    /**
     * Check if a string is a numeric literal.
     */
    public static boolean isNumericLiteral(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return NUMERIC_PATTERN.matcher(value.trim()).matches();
    }
    
    /**
     * Check if a string is a string literal (quoted).
     */
    public static boolean isStringLiteral(String value) {
        if (value == null || value.length() < 2) {
            return false;
        }
        String trimmed = value.trim();
        return (trimmed.startsWith("'") && trimmed.endsWith("'")) ||
               (trimmed.startsWith("\"") && trimmed.endsWith("\""));
    }
    
    /**
     * Check if a string is a valid COBOL identifier.
     */
    public static boolean isValidIdentifier(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return IDENTIFIER_PATTERN.matcher(value.trim()).matches();
    }
    
    /**
     * Remove quotes from a string literal.
     */
    public static String removeQuotes(String value) {
        if (value == null || value.length() < 2) {
            return value;
        }
        String trimmed = value.trim();
        if (isStringLiteral(trimmed)) {
            return trimmed.substring(1, trimmed.length() - 1);
        }
        return value;
    }
    
    /**
     * Convert COBOL naming convention to Java camelCase.
     * Example: WS-CUSTOMER-NAME -> wsCustomerName
     */
    public static String toJavaCamelCase(String cobolName) {
        if (cobolName == null || cobolName.isEmpty()) {
            return cobolName;
        }
        
        String[] parts = cobolName.split("-");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i].toLowerCase();
            if (i == 0) {
                result.append(part);
            } else {
                result.append(Character.toUpperCase(part.charAt(0)));
                if (part.length() > 1) {
                    result.append(part.substring(1));
                }
            }
        }
        
        return result.toString();
    }
    
    /**
     * Convert COBOL naming convention to Java PascalCase.
     * Example: CUSTOMER-PROGRAM -> CustomerProgram
     */
    public static String toJavaPascalCase(String cobolName) {
        if (cobolName == null || cobolName.isEmpty()) {
            return cobolName;
        }
        
        String[] parts = cobolName.split("-");
        StringBuilder result = new StringBuilder();
        
        for (String part : parts) {
            String lower = part.toLowerCase();
            result.append(Character.toUpperCase(lower.charAt(0)));
            if (lower.length() > 1) {
                result.append(lower.substring(1));
            }
        }
        
        return result.toString();
    }
    
    /**
     * Sanitize a string for use in Java code.
     */
    public static String sanitizeForJava(String value) {
        if (value == null) {
            return null;
        }
        
        // Escape special characters
        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }
    
    /**
     * Extract variable names from a COBOL expression.
     */
    public static String[] extractVariables(String expression) {
        if (expression == null || expression.isEmpty()) {
            return new String[0];
        }
        
        // Split on operators and whitespace, filter out literals and operators
        String[] tokens = expression.split("[+\\-*/()=<>\\s]+");
        return java.util.Arrays.stream(tokens)
            .filter(token -> !token.isEmpty())
            .filter(token -> !isNumericLiteral(token))
            .filter(token -> !isStringLiteral(token))
            .filter(ConversionUtils::isValidIdentifier)
            .toArray(String[]::new);
    }
    
    /**
     * Format a Java type name based on COBOL picture clause.
     */
    public static String getJavaTypeForPicture(String picture) {
        if (picture == null || picture.isEmpty()) {
            return "Object";
        }
        
        String upper = picture.toUpperCase();
        
        // Alphanumeric: PIC X(n)
        if (upper.contains("X")) {
            return "String";
        }
        
        // Alphabetic: PIC A(n)
        if (upper.contains("A") && !upper.contains("9")) {
            return "String";
        }
        
        // Decimal: PIC 9(n)V9(m)
        if (upper.contains("V") || upper.contains(".")) {
            return "BigDecimal";
        }
        
        // Integer: PIC 9(n)
        if (upper.contains("9")) {
            // Count the number of 9s to determine size
            int nines = countOccurrences(upper, '9');
            if (nines <= 4) {
                return "int";
            } else if (nines <= 9) {
                return "long";
            } else {
                return "BigDecimal";
            }
        }
        
        return "Object";
    }
    
    /**
     * Count occurrences of a character in a string.
     */
    private static int countOccurrences(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
}

// Made with Bob