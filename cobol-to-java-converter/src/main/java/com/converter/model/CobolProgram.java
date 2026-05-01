package com.converter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a parsed COBOL program with all its divisions and sections.
 */
public class CobolProgram {
    private String programId;
    private String author;
    private String dateWritten;
    
    // Data Division
    private List<DataItem> workingStorageSection;
    private List<DataItem> fileSection;
    private List<DataItem> linkageSection;
    
    // Procedure Division
    private List<Paragraph> paragraphs;
    private List<Section> sections;
    
    public CobolProgram() {
        this.workingStorageSection = new ArrayList<>();
        this.fileSection = new ArrayList<>();
        this.linkageSection = new ArrayList<>();
        this.paragraphs = new ArrayList<>();
        this.sections = new ArrayList<>();
    }
    
    // Getters and Setters
    public String getProgramId() {
        return programId;
    }
    
    public void setProgramId(String programId) {
        this.programId = programId;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getDateWritten() {
        return dateWritten;
    }
    
    public void setDateWritten(String dateWritten) {
        this.dateWritten = dateWritten;
    }
    
    public List<DataItem> getWorkingStorageSection() {
        return workingStorageSection;
    }
    
    public void addWorkingStorageItem(DataItem item) {
        this.workingStorageSection.add(item);
    }
    
    public List<DataItem> getFileSection() {
        return fileSection;
    }
    
    public void addFileItem(DataItem item) {
        this.fileSection.add(item);
    }
    
    public List<DataItem> getLinkageSection() {
        return linkageSection;
    }
    
    public void addLinkageItem(DataItem item) {
        this.linkageSection.add(item);
    }
    
    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }
    
    public void addParagraph(Paragraph paragraph) {
        this.paragraphs.add(paragraph);
    }
    
    public List<Section> getSections() {
        return sections;
    }
    
    public void addSection(Section section) {
        this.sections.add(section);
    }
    
    @Override
    public String toString() {
        return "CobolProgram{" +
                "programId='" + programId + '\'' +
                ", workingStorageItems=" + workingStorageSection.size() +
                ", paragraphs=" + paragraphs.size() +
                '}';
    }
}

// Made with Bob
