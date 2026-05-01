# Quick Start Guide

## Getting Started with COBOL to Java Converter

This guide will help you quickly set up and use the COBOL to Java converter.

## Prerequisites

Before you begin, ensure you have:
- Java Development Kit (JDK) 11 or higher
- Apache Maven 3.6 or higher
- A text editor or IDE (VS Code, IntelliJ IDEA, Eclipse)

## Installation

### 1. Verify Java Installation

```bash
java -version
```

Expected output: `java version "11.0.x"` or higher

### 2. Verify Maven Installation

```bash
mvn -version
```

Expected output: `Apache Maven 3.6.x` or higher

### 3. Build the Project

Navigate to the project directory and build:

```bash
cd cobol-to-java-converter
mvn clean package
```

This will:
- Compile all Java source files
- Run tests
- Create an executable JAR file in the `target/` directory

## Your First Conversion

### Step 1: Prepare a COBOL File

Create a simple COBOL program or use the provided sample:

```cobol
       IDENTIFICATION DIVISION.
       PROGRAM-ID. SAMPLE-PROG.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01 WS-MESSAGE PIC X(30) VALUE 'Hello from COBOL!'.
       
       PROCEDURE DIVISION.
       MAIN-LOGIC.
           DISPLAY WS-MESSAGE.
           STOP RUN.
```

Save this as `sample.cbl`

### Step 2: Run the Converter

```bash
java -jar target/cobol-to-java-converter-1.0.0-SNAPSHOT.jar sample.cbl ./output
```

### Step 3: View the Generated Java Code

Check the `output/` directory for `SampleProg.java`:

```java
public class SampleProg {
    private String wsMessage = "Hello from COBOL!";
    
    public void mainLogic() {
        System.out.println(wsMessage);
        return;
    }
    
    public static void main(String[] args) {
        SampleProg program = new SampleProg();
        program.mainLogic();
    }
}
```

### Step 4: Compile and Run the Java Code

```bash
cd output
javac SampleProg.java
java SampleProg
```

Expected output: `Hello from COBOL!`

## Using Bob IDE Features

### 1. Code Mode - For Implementation

Use Bob's Code Mode to:
- Create new parser rules
- Implement conversion logic
- Add new features

Example:
```
"Add support for IF-ELSE statements in the parser"
```

### 2. Ask Mode - For Questions

Use Ask Mode to:
- Understand the codebase
- Get explanations of conversion logic
- Learn about COBOL constructs

Example:
```
"How does the converter handle PIC clauses?"
```

### 3. Advanced Mode - For Complex Tasks

Use Advanced Mode when you need to:
- Integrate external libraries
- Set up ANTLR grammar files
- Configure build tools

## Common Use Cases

### Converting Multiple Files

Create a batch script (Windows):

```batch
@echo off
for %%f in (*.cbl) do (
    java -jar cobol-to-java-converter.jar %%f ./output
)
```

Or shell script (Linux/Mac):

```bash
#!/bin/bash
for file in *.cbl; do
    java -jar cobol-to-java-converter.jar "$file" ./output
done
```

### Customizing Output

Modify `JavaCodeGenerator.java` to:
- Change package names
- Add custom imports
- Modify naming conventions
- Add comments or documentation

### Testing Conversions

1. Create test COBOL files in `src/test/resources/samples/`
2. Run the converter
3. Compile and test the generated Java code
4. Compare outputs

## Troubleshooting

### Issue: "Command not found: java"

**Solution**: Install JDK and add it to your PATH

### Issue: "Command not found: mvn"

**Solution**: Install Maven and add it to your PATH

### Issue: Parser errors

**Solution**: 
- Check COBOL syntax
- Ensure proper indentation (columns 7-72)
- Verify division headers are present

### Issue: Generated code doesn't compile

**Solution**:
- Review the generated Java code
- Check for unsupported COBOL features
- Manually adjust complex logic

## Next Steps

1. **Explore the Sample**: Try converting `HELLO-WORLD.cbl`
2. **Read the Documentation**: Check `README.md` for detailed information
3. **Extend the Converter**: Add support for more COBOL features
4. **Contribute**: Submit improvements via pull requests

## Example Workflow with Bob IDE

### 1. Analyze a COBOL Program

```
Bob: "Read and analyze the COBOL file at src/test/resources/samples/HELLO-WORLD.cbl"
```

### 2. Understand the Conversion

```
Bob: "Explain how the converter maps COBOL data types to Java types"
```

### 3. Add a New Feature

```
Bob: "Add support for COBOL COMPUTE statements in the converter"
```

### 4. Test the Changes

```
Bob: "Create a test COBOL file with COMPUTE statements and convert it"
```

### 5. Review Generated Code

```
Bob: "Review the generated Java code and suggest improvements"
```

## Tips for Success

1. **Start Simple**: Begin with basic COBOL programs
2. **Incremental Conversion**: Convert one feature at a time
3. **Test Thoroughly**: Always test generated code
4. **Manual Review**: Review and adjust generated code as needed
5. **Document Changes**: Keep track of manual modifications

## Resources

- **COBOL Reference**: IBM COBOL Language Reference
- **Java Tutorials**: Oracle Java Documentation
- **Maven Guide**: Apache Maven Getting Started
- **Bob IDE**: Use different modes for different tasks

## Getting Help

If you encounter issues:

1. Check the error messages carefully
2. Review the logs in the console
3. Consult the README.md
4. Ask Bob IDE for help in Ask Mode
5. Create an issue in the project repository

---

Happy Converting! 🚀