package com.converter.model;

/**
 * Represents a COBOL statement in the Procedure Division.
 */
public class Statement {
    private StatementType type;
    private String rawText;
    private String target;
    private String source;
    private String condition;
    
    public Statement(StatementType type, String rawText) {
        this.type = type;
        this.rawText = rawText;
    }
    
    public StatementType getType() {
        return type;
    }
    
    public void setType(StatementType type) {
        this.type = type;
    }
    
    public String getRawText() {
        return rawText;
    }
    
    public void setRawText(String rawText) {
        this.rawText = rawText;
    }
    
    public String getTarget() {
        return target;
    }
    
    public void setTarget(String target) {
        this.target = target;
    }
    
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public String getCondition() {
        return condition;
    }
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    @Override
    public String toString() {
        return "Statement{" +
                "type=" + type +
                ", rawText='" + rawText + '\'' +
                '}';
    }
    
    /**
     * Enum representing different types of COBOL statements.
     */
    public enum StatementType {
        MOVE,
        DISPLAY,
        ACCEPT,
        IF,
        ELSE,
        PERFORM,
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        COMPUTE,
        OPEN,
        CLOSE,
        READ,
        WRITE,
        STOP,
        GOBACK,
        EXIT,
        EVALUATE,
        CALL,
        UNKNOWN
    }
}

// Made with Bob
