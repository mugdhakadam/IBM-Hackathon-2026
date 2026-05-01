package com.converter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a COBOL paragraph in the Procedure Division.
 */
public class Paragraph {
    private String name;
    private List<Statement> statements;
    
    public Paragraph(String name) {
        this.name = name;
        this.statements = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Statement> getStatements() {
        return statements;
    }
    
    public void addStatement(Statement statement) {
        this.statements.add(statement);
    }
    
    @Override
    public String toString() {
        return "Paragraph{" +
                "name='" + name + '\'' +
                ", statements=" + statements.size() +
                '}';
    }
}

// Made with Bob
