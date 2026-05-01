package com.converter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generated Java class with its fields and methods.
 */
public class JavaClass {
    private String packageName;
    private String className;
    private List<JavaField> fields;
    private List<JavaMethod> methods;
    private List<String> imports;
    
    public JavaClass(String className) {
        this.className = className;
        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.imports = new ArrayList<>();
    }
    
    public String getPackageName() {
        return packageName;
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public List<JavaField> getFields() {
        return fields;
    }
    
    public void addField(JavaField field) {
        this.fields.add(field);
    }
    
    public List<JavaMethod> getMethods() {
        return methods;
    }
    
    public void addMethod(JavaMethod method) {
        this.methods.add(method);
    }
    
    public List<String> getImports() {
        return imports;
    }
    
    public void addImport(String importStatement) {
        if (!this.imports.contains(importStatement)) {
            this.imports.add(importStatement);
        }
    }
    
    @Override
    public String toString() {
        return "JavaClass{" +
                "className='" + className + '\'' +
                ", fields=" + fields.size() +
                ", methods=" + methods.size() +
                '}';
    }
    
    /**
     * Represents a Java field (instance variable).
     */
    public static class JavaField {
        private String name;
        private String type;
        private String initialValue;
        private String visibility;
        
        public JavaField(String name, String type) {
            this.name = name;
            this.type = type;
            this.visibility = "private";
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public String getInitialValue() {
            return initialValue;
        }
        
        public void setInitialValue(String initialValue) {
            this.initialValue = initialValue;
        }
        
        public String getVisibility() {
            return visibility;
        }
        
        public void setVisibility(String visibility) {
            this.visibility = visibility;
        }
    }
    
    /**
     * Represents a Java method.
     */
    public static class JavaMethod {
        private String name;
        private String returnType;
        private List<String> parameters;
        private List<String> body;
        private String visibility;
        
        public JavaMethod(String name, String returnType) {
            this.name = name;
            this.returnType = returnType;
            this.parameters = new ArrayList<>();
            this.body = new ArrayList<>();
            this.visibility = "public";
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getReturnType() {
            return returnType;
        }
        
        public void setReturnType(String returnType) {
            this.returnType = returnType;
        }
        
        public List<String> getParameters() {
            return parameters;
        }
        
        public void addParameter(String parameter) {
            this.parameters.add(parameter);
        }
        
        public List<String> getBody() {
            return body;
        }
        
        public void addBodyLine(String line) {
            this.body.add(line);
        }
        
        public String getVisibility() {
            return visibility;
        }
        
        public void setVisibility(String visibility) {
            this.visibility = visibility;
        }
    }
}

// Made with Bob
