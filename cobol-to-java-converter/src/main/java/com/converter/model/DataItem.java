package com.converter.model;

/**
 * Represents a COBOL data item (variable) with its level, name, picture clause, and value.
 */
public class DataItem {
    private int level;
    private String name;
    private String pictureClause;
    private String value;
    private DataType dataType;
    private int length;
    private int decimalPlaces;
    private boolean isSigned;
    private boolean isComputed;
    
    public DataItem(int level, String name) {
        this.level = level;
        this.name = name;
    }
    
    // Getters and Setters
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPictureClause() {
        return pictureClause;
    }
    
    public void setPictureClause(String pictureClause) {
        this.pictureClause = pictureClause;
        this.dataType = inferDataType(pictureClause);
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public DataType getDataType() {
        return dataType;
    }
    
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
    
    public int getLength() {
        return length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    public int getDecimalPlaces() {
        return decimalPlaces;
    }
    
    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }
    
    public boolean isSigned() {
        return isSigned;
    }
    
    public void setSigned(boolean signed) {
        isSigned = signed;
    }
    
    public boolean isComputed() {
        return isComputed;
    }
    
    public void setComputed(boolean computed) {
        isComputed = computed;
    }
    
    /**
     * Infer the data type from the COBOL picture clause.
     */
    private DataType inferDataType(String picture) {
        if (picture == null) {
            return DataType.UNKNOWN;
        }
        
        String upperPic = picture.toUpperCase();
        
        if (upperPic.contains("X")) {
            return DataType.ALPHANUMERIC;
        } else if (upperPic.contains("9")) {
            if (upperPic.contains("V") || upperPic.contains(".")) {
                return DataType.DECIMAL;
            }
            return DataType.NUMERIC;
        } else if (upperPic.contains("A")) {
            return DataType.ALPHABETIC;
        }
        
        return DataType.UNKNOWN;
    }
    
    @Override
    public String toString() {
        return "DataItem{" +
                "level=" + level +
                ", name='" + name + '\'' +
                ", pictureClause='" + pictureClause + '\'' +
                ", dataType=" + dataType +
                ", value='" + value + '\'' +
                '}';
    }
    
    /**
     * Enum representing COBOL data types.
     */
    public enum DataType {
        ALPHANUMERIC,  // PIC X
        NUMERIC,       // PIC 9
        DECIMAL,       // PIC 9V9
        ALPHABETIC,    // PIC A
        UNKNOWN
    }
}

// Made with Bob
