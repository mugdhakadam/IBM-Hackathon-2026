# Feature Documentation

## Overview

This document describes all features supported by the COBOL to Java Converter, including recently added enhancements.

## Table of Contents

1. [Basic Features](#basic-features)
2. [Arithmetic Operations](#arithmetic-operations)
3. [Control Flow](#control-flow)
4. [Data Types](#data-types)
5. [I/O Operations](#io-operations)
6. [Utility Functions](#utility-functions)

---

## Basic Features

### Program Structure

The converter supports the standard COBOL program structure:

```cobol
IDENTIFICATION DIVISION.
PROGRAM-ID. program-name.
AUTHOR. author-name.

DATA DIVISION.
WORKING-STORAGE SECTION.
01 variable-name PIC X(10) VALUE 'initial'.

PROCEDURE DIVISION.
paragraph-name.
    DISPLAY 'Hello World'.
    STOP RUN.
```

**Java Output:**
```java
public class ProgramName {
    private String variableName = "initial";
    
    public void paragraphName() {
        System.out.println("Hello World");
        return;
    }
    
    public static void main(String[] args) {
        ProgramName program = new ProgramName();
        program.paragraphName();
    }
}
```

---

## Arithmetic Operations

### ADD Statement

**Syntax:**
- `ADD A TO B` - Adds A to B, stores result in B
- `ADD A B TO C` - Adds A and B to C, stores result in C
- `ADD A TO B GIVING C` - Adds A to B, stores result in C

**COBOL Example:**
```cobol
01 WS-NUM1 PIC 99 VALUE 10.
01 WS-NUM2 PIC 99 VALUE 5.
01 WS-RESULT PIC 999.

ADD WS-NUM1 TO WS-NUM2.
ADD WS-NUM1 WS-NUM2 TO WS-RESULT.
ADD WS-NUM1 TO WS-NUM2 GIVING WS-RESULT.
```

**Java Output:**
```java
private int wsNum1 = 10;
private int wsNum2 = 5;
private int wsResult;

wsNum2 = wsNum2 + wsNum1;
wsResult = wsResult + wsNum1 + wsNum2;
wsResult = wsNum1 + wsNum2;
```

### SUBTRACT Statement

**Syntax:**
- `SUBTRACT A FROM B` - Subtracts A from B, stores result in B
- `SUBTRACT A FROM B GIVING C` - Subtracts A from B, stores result in C

**COBOL Example:**
```cobol
SUBTRACT 5 FROM WS-NUM1.
SUBTRACT WS-NUM1 FROM WS-NUM2 GIVING WS-RESULT.
```

**Java Output:**
```java
wsNum1 = wsNum1 - 5;
wsResult = wsNum2 - wsNum1;
```

### MULTIPLY Statement

**Syntax:**
- `MULTIPLY A BY B` - Multiplies B by A, stores result in B
- `MULTIPLY A BY B GIVING C` - Multiplies A by B, stores result in C

**COBOL Example:**
```cobol
MULTIPLY 2 BY WS-NUM1.
MULTIPLY WS-NUM1 BY WS-NUM2 GIVING WS-RESULT.
```

**Java Output:**
```java
wsNum1 = wsNum1 * 2;
wsResult = wsNum1 * wsNum2;
```

### DIVIDE Statement

**Syntax:**
- `DIVIDE A INTO B` - Divides B by A, stores result in B
- `DIVIDE A BY B GIVING C` - Divides A by B, stores result in C

**COBOL Example:**
```cobol
DIVIDE 2 INTO WS-NUM1.
DIVIDE WS-NUM1 BY WS-NUM2 GIVING WS-RESULT.
```

**Java Output:**
```java
wsNum1 = wsNum1 / 2;
wsResult = wsNum1 / wsNum2;
```

### COMPUTE Statement

**Syntax:**
- `COMPUTE variable = expression`

**COBOL Example:**
```cobol
COMPUTE WS-RESULT = WS-NUM1 + WS-NUM2 * 2.
COMPUTE WS-TOTAL = (WS-SALARY + WS-BONUS) * 1.05.
```

**Java Output:**
```java
wsResult = wsNum1 + wsNum2 * 2;
wsTotal = (wsSalary + wsBonus) * 1.05;
```

---

## Control Flow

### IF Statement

**Syntax:**
- `IF condition ... END-IF`

**Supported Operators:**
- `EQUAL TO` or `=` → `==`
- `NOT EQUAL TO` → `!=`
- `GREATER THAN` → `>`
- `LESS THAN` → `<`
- `>=` → `>=`
- `<=` → `<=`
- `AND` → `&&`
- `OR` → `||`
- `NOT` → `!`

**COBOL Example:**
```cobol
IF WS-AGE GREATER THAN 18
    DISPLAY 'Adult'
END-IF.

IF WS-SALARY >= 50000 AND WS-YEARS > 5
    DISPLAY 'Senior Employee'
END-IF.
```

**Java Output:**
```java
if (wsAge > 18) {
    System.out.println("Adult");
}

if (wsSalary >= 50000 && wsYears > 5) {
    System.out.println("Senior Employee");
}
```

### PERFORM Statement

**Syntax:**
- `PERFORM paragraph-name`

**COBOL Example:**
```cobol
MAIN-PARAGRAPH.
    PERFORM CALCULATE-TOTAL.
    STOP RUN.

CALCULATE-TOTAL.
    ADD WS-NUM1 TO WS-NUM2.
```

**Java Output:**
```java
public void mainParagraph() {
    calculateTotal();
    return;
}

public void calculateTotal() {
    wsNum2 = wsNum2 + wsNum1;
}
```

---

## Data Types

### Type Mapping

| COBOL Type | COBOL Example | Java Type | Notes |
|------------|---------------|-----------|-------|
| PIC X(n) | PIC X(20) | String | Alphanumeric |
| PIC A(n) | PIC A(10) | String | Alphabetic |
| PIC 9(n) | PIC 99 | int | Integer (≤4 digits) |
| PIC 9(n) | PIC 9(9) | long | Integer (5-9 digits) |
| PIC 9(n) | PIC 9(10) | BigDecimal | Integer (>9 digits) |
| PIC 9(n)V9(m) | PIC 9(5)V99 | BigDecimal | Decimal |

### VALUE Clause

**COBOL Example:**
```cobol
01 WS-NAME PIC X(20) VALUE 'John Doe'.
01 WS-AGE PIC 99 VALUE 25.
01 WS-SALARY PIC 9(5)V99 VALUE 50000.00.
```

**Java Output:**
```java
private String wsName = "John Doe";
private int wsAge = 25;
private BigDecimal wsSalary = new BigDecimal("50000.00");
```

---

## I/O Operations

### DISPLAY Statement

**Syntax:**
- `DISPLAY literal` - Display a string literal
- `DISPLAY variable` - Display a variable value
- `DISPLAY literal variable` - Display multiple items

**COBOL Example:**
```cobol
DISPLAY 'Hello World'.
DISPLAY WS-NAME.
DISPLAY 'Name: ' WS-NAME.
DISPLAY 'Age: ' WS-AGE ' years'.
```

**Java Output:**
```java
System.out.println("Hello World");
System.out.println(wsName);
System.out.print("Name: " + wsName);
System.out.print("Age: " + wsAge + " years");
```

### ACCEPT Statement

**Syntax:**
- `ACCEPT variable`

**Note:** Currently generates a TODO comment. Full implementation requires Scanner integration.

**COBOL Example:**
```cobol
ACCEPT WS-NAME.
```

**Java Output:**
```java
// TODO: Implement input for wsName using Scanner
```

---

## Utility Functions

### ConversionUtils Class

The converter includes a utility class with helper methods:

#### Naming Conversion

```java
// COBOL to Java camelCase
ConversionUtils.toJavaCamelCase("WS-CUSTOMER-NAME")
// Returns: "wsCustomerName"

// COBOL to Java PascalCase
ConversionUtils.toJavaPascalCase("CUSTOMER-PROGRAM")
// Returns: "CustomerProgram"
```

#### Type Checking

```java
// Check if numeric literal
ConversionUtils.isNumericLiteral("123.45")  // true
ConversionUtils.isNumericLiteral("ABC")     // false

// Check if string literal
ConversionUtils.isStringLiteral("'Hello'")  // true
ConversionUtils.isStringLiteral("Hello")    // false

// Check if valid identifier
ConversionUtils.isValidIdentifier("WS-NAME")  // true
ConversionUtils.isValidIdentifier("123ABC")   // false
```

#### String Operations

```java
// Remove quotes
ConversionUtils.removeQuotes("'Hello'")  // "Hello"

// Sanitize for Java
ConversionUtils.sanitizeForJava("Line\nBreak")  // "Line\\nBreak"

// Extract variables from expression
ConversionUtils.extractVariables("A + B * 2")  // ["A", "B"]
```

---

## Semantic Analysis

The converter performs semantic analysis to validate:

1. **Variable Declarations**: Ensures all used variables are declared
2. **Type Compatibility**: Validates operations on compatible types
3. **Statement Structure**: Checks for proper statement syntax
4. **Symbol Table**: Maintains a symbol table for variable tracking

### Validation Features

- **MOVE Statement**: Validates target variable exists
- **Arithmetic Operations**: Checks variable references
- **COMPUTE Statement**: Validates target variable
- **IF Statement**: Ensures condition is present
- **DISPLAY Statement**: Validates content exists
- **ACCEPT Statement**: Validates target variable

---

## Error Handling

### Parser Errors

The parser logs detailed errors including:
- Line numbers
- Error descriptions
- Suggested fixes

### Semantic Errors

The analyzer provides warnings for:
- Undefined variables
- Invalid operations
- Missing required clauses

### Generation Warnings

The generator creates TODO comments for:
- Unsupported features
- Complex conversions requiring manual review
- Incomplete implementations

---

## Limitations

### Current Limitations

1. **IF-ELSE**: Only basic IF supported, ELSE not yet implemented
2. **EVALUATE**: SWITCH/CASE statements not supported
3. **File I/O**: FILE SECTION not implemented
4. **Arrays**: OCCURS clause not supported
5. **REDEFINES**: Not implemented
6. **COPY**: Include statements not supported
7. **CALL**: External program calls not supported

### Planned Enhancements

See [ARCHITECTURE.md](ARCHITECTURE.md) for the roadmap of planned features.

---

## Examples

### Complete Example

**Input COBOL:**
```cobol
IDENTIFICATION DIVISION.
PROGRAM-ID. PAYROLL-CALC.

DATA DIVISION.
WORKING-STORAGE SECTION.
01 WS-SALARY PIC 9(5)V99 VALUE 50000.00.
01 WS-BONUS PIC 9(5)V99 VALUE 0.
01 WS-TOTAL PIC 9(6)V99 VALUE 0.

PROCEDURE DIVISION.
MAIN-LOGIC.
    COMPUTE WS-BONUS = WS-SALARY * 0.10.
    ADD WS-SALARY TO WS-BONUS GIVING WS-TOTAL.
    DISPLAY 'Total Compensation: ' WS-TOTAL.
    STOP RUN.
```

**Output Java:**
```java
import java.math.BigDecimal;
import java.io.*;

/**
 * Generated from COBOL program
 * Auto-generated by COBOL-to-Java Converter
 */
public class PayrollCalc {

    private BigDecimal wsSalary = new BigDecimal("50000.00");
    private BigDecimal wsBonus = new BigDecimal("0");
    private BigDecimal wsTotal = new BigDecimal("0");

    public void mainLogic() {
        wsBonus = wsSalary * 0.10;
        wsTotal = wsSalary + wsBonus;
        System.out.print("Total Compensation: " + wsTotal);
        return;
    }

    public static void main(String[] args) {
        PayrollCalc program = new PayrollCalc();
        program.mainLogic();
    }

}
```

---

## Best Practices

### For COBOL Code

1. Use clear, descriptive variable names
2. Follow standard COBOL formatting
3. Add comments for complex logic
4. Use consistent indentation
5. Avoid vendor-specific extensions

### For Generated Java Code

1. Review all TODO comments
2. Test arithmetic operations with edge cases
3. Add proper exception handling
4. Implement Scanner for ACCEPT statements
5. Add unit tests for business logic

---

## Support

For issues, questions, or feature requests, please refer to the main [README.md](../README.md).

---

Last Updated: 2026-05-01