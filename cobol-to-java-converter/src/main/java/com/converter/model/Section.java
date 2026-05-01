package com.converter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a COBOL section in the Procedure Division.
 */
public class Section {
    private String name;
    private List<Paragraph> paragraphs;
    
    public Section(String name) {
        this.name = name;
        this.paragraphs = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }
    
    public void addParagraph(Paragraph paragraph) {
        this.paragraphs.add(paragraph);
    }
    
    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", paragraphs=" + paragraphs.size() +
                '}';
    }
}

// Made with Bob
