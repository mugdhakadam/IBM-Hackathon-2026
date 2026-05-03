# COBOL to Java Converter - Test Cases Documentation

## Overview
This document describes the comprehensive test cases implemented to validate the COBOL to Java converter functionality. The test suite covers all supported features including arithmetic operations, control flow, data types, I/O operations, and semantic analysis.

---

## Test Programs

### 1. HELLO-WORLD.cbl
**Purpose**: Basic syntax validation and program structure testing

**Features Tested**:
- IDENTIFICATION DIVISION parsing
- PROGRAM-ID extraction
- AUTHOR information
- DATA DIVISION with WORKING-STORAGE SECTION
- Basic PIC clauses (X for alphanumeric)
- PROCEDURE DIVISION structure
- Simple DISPLAY statement
- STOP RUN statement

**Expected Output**: A Java class with a main method that prints "Hello, World!"

---

### 2. ADVANCED-FEATURES.cbl
**Purpose**: Test arithmetic operations, control flow, and COMPUTE statements

**Features Tested**:
- Multiple data types (numeric, alphanumeric, decimal)
- ADD statement (TO and GIVING clauses)
- SUBTRACT statement (FROM and GIVING clauses)
- MULTIPLY statement (BY and GIVING clauses)
- DIVIDE statement (BY and GIVING clauses)
- COMPUTE statement with expressions
- IF statement with comparison operators
- PERFORM statement for paragraph execution
- Enhanced DISPLAY with multiple items

**Key Test Cases**:
1. **Arithmetic Operations**:
   - ADD 10 TO WS-NUM1
   - SUBTRACT 5 FROM WS-NUM2
   - MULTIPLY WS-NUM1 BY 2 GIVING WS-RESULT
   - DIVIDE WS-NUM2 BY 4 GIVING WS-RESULT

2. **COMPUTE Expressions**:
   - COMPUTE WS-RESULT = WS-NUM1 + WS-NUM2
   - COMPUTE WS-TOTAL = WS-SALARY * 1.10

3. **Conditional Logic**:
   - IF WS-NUM1 GREATER THAN 50
   - IF WS-RESULT = 100

**Expected Output**: Java class with proper arithmetic operations and conditional logic

---

### 3. COMPREHENSIVE-TEST.cbl
**Purpose**: Extensive testing of all supported features and edge cases

**Features Tested**:
- Complex data structures (hierarchical data items)
- All arithmetic operations with multiple variants
- Nested IF statements
- Logical operators (AND, OR, NOT)
- Multiple loop constructs (PERFORM variations)
- Complex COMPUTE expressions
- String operations
- Data validation logic
- Business logic scenarios

---

## Test Categories

### 1. Data Division Tests

#### 1.1 Basic Data Types
- **Test**: Various PIC clauses (numeric, alphanumeric, decimal)
- **Variables**:
  - `WS-EMP-ID PIC 9(6)` - 6-digit numeric → `int` or `long`
  - `WS-EMP-NAME PIC X(30)` - 30-character string → `String`
  - `WS-EMP-SALARY PIC 9(7)V99` - Decimal with 2 decimal places → `BigDecimal`
  - `WS-EMP-DEPT PIC X(20)` - Department string → `String`

#### 1.2 Hierarchical Data Structures
- **Test**: Group-level data items (01 level with 05 sub-items)
- **Structure**: `WS-EMPLOYEE-RECORD` with nested fields
- **Validation**: Proper Java class structure with nested objects or flat fields

#### 1.3 Numeric Variables
- **Test**: Various numeric formats for calculations
- **Variables**: Counters, totals, rates, amounts with different precision
- **Type Mapping**:
  - PIC 9(1-4) → `int`
  - PIC 9(5-9) → `long`
  - PIC 9(10+) → `BigDecimal`
  - PIC 9(n)V9(m) → `BigDecimal`

#### 1.4 String Variables
- **Test**: Character data with SPACES initialization
- **Variables**: Names, messages, full names
- **Validation**: Proper String initialization in Java

#### 1.5 Boolean/Flag Variables
- **Test**: Single-digit numeric flags (0/1)
- **Variables**: `WS-IS-VALID`, `WS-IS-PROCESSED`, `WS-ERROR-FLAG`
- **Conversion**: Numeric flags in COBOL → `int` in Java (could be `boolean` in future)

#### 1.6 Constants
- **Test**: Initialized values that act as constants
- **Variables**: Company name, version, min/max salary limits
- **Validation**: Proper VALUE clause conversion to Java initialization

---

### 2. Arithmetic Operations Tests

#### 2.1 ADD Operation
- **Test Case 1**: `ADD WS-NUM1 TO WS-NUM2`
  - **Input**: 100 + 50
  - **Expected**: 150 stored in WS-NUM2
  - **Java**: `wsNum2 = wsNum2 + wsNum1;`
  - **Validates**: Basic addition, variable modification

- **Test Case 2**: `ADD WS-NUM1 WS-NUM2 TO WS-RESULT`
  - **Input**: 10 + 5 + existing value
  - **Expected**: Sum added to WS-RESULT
  - **Java**: `wsResult = wsResult + wsNum1 + wsNum2;`
  - **Validates**: Multiple operand addition

- **Test Case 3**: `ADD WS-NUM1 TO WS-NUM2 GIVING WS-RESULT`
  - **Input**: 10 + 5
  - **Expected**: 15 stored in WS-RESULT
  - **Java**: `wsResult = wsNum1 + wsNum2;`
  - **Validates**: GIVING clause (non-destructive)

#### 2.2 SUBTRACT Operation
- **Test Case 1**: `SUBTRACT 75 FROM WS-NUM1`
  - **Input**: 200 - 75
  - **Expected**: 125 stored in WS-NUM1
  - **Java**: `wsNum1 = wsNum1 - 75;`
  - **Validates**: Subtraction with literal values

- **Test Case 2**: `SUBTRACT WS-NUM1 FROM WS-NUM2 GIVING WS-RESULT`
  - **Input**: 100 - 25
  - **Expected**: 75 stored in WS-RESULT
  - **Java**: `wsResult = wsNum2 - wsNum1;`
  - **Validates**: GIVING clause with variables

#### 2.3 MULTIPLY Operation
- **Test Case 1**: `MULTIPLY WS-NUM1 BY WS-NUM2 GIVING WS-RESULT`
  - **Input**: 15 * 4
  - **Expected**: 60 stored in WS-RESULT
  - **Java**: `wsResult = wsNum1 * wsNum2;`
  - **Validates**: Multiplication with GIVING clause

- **Test Case 2**: `MULTIPLY 2 BY WS-NUM1`
  - **Input**: 50 * 2
  - **Expected**: 100 stored in WS-NUM1
  - **Java**: `wsNum1 = wsNum1 * 2;`
  - **Validates**: In-place multiplication

#### 2.4 DIVIDE Operation
- **Test Case 1**: `DIVIDE WS-NUM1 BY WS-NUM2 GIVING WS-RESULT`
  - **Input**: 100 / 4
  - **Expected**: 25 stored in WS-RESULT
  - **Java**: `wsResult = wsNum1 / wsNum2;`
  - **Validates**: Division with GIVING clause

- **Test Case 2**: `DIVIDE 2 INTO WS-NUM1`
  - **Input**: 100 / 2
  - **Expected**: 50 stored in WS-NUM1
  - **Java**: `wsNum1 = wsNum1 / 2;`
  - **Validates**: INTO clause (reverse operands)

#### 2.5 DIVIDE with REMAINDER
- **Test Case**: `DIVIDE WS-NUM1 BY WS-NUM2 GIVING WS-RESULT REMAINDER WS-REMAINDER`
  - **Input**: 100 / 7
  - **Expected**: Result = 14, Remainder = 2
  - **Java**: `wsResult = wsNum1 / wsNum2; wsRemainder = wsNum1 % wsNum2;`
  - **Validates**: Division with remainder calculation

#### 2.6 COMPUTE Statement
- **Test Case 1**: `COMPUTE WS-TAX-AMOUNT = WS-EMP-SALARY * 0.15`
  - **Input**: Salary = 50000
  - **Expected**: Tax = 7500
  - **Java**: `wsTaxAmount = wsEmpSalary * 0.15;`
  - **Validates**: Multiplication with decimal literal

- **Test Case 2**: `COMPUTE WS-RESULT = (WS-NUM1 + WS-NUM2) * WS-NUM3 / 5`
  - **Input**: (100 + 50) * 10 / 5
  - **Expected**: 300
  - **Java**: `wsResult = (wsNum1 + wsNum2) * wsNum3 / 5;`
  - **Validates**: Order of operations with parentheses

- **Test Case 3**: `COMPUTE WS-SUBTOTAL = WS-EMP-SALARY * 1.10`
  - **Input**: Salary = 75000
  - **Expected**: 82500 (10% increase)
  - **Java**: `wsSubtotal = wsEmpSalary * 1.10;`
  - **Validates**: Percentage-based calculations

---

### 3. Conditional Logic Tests

#### 3.1 Simple IF Statement
- **Test Case**: `IF WS-COUNTER GREATER THAN 20`
  - **Input**: Counter = 25
  - **Expected**: Condition true, message displayed
  - **Java**: `if (wsCounter > 20) { ... }`
  - **Validates**: Basic comparison operator conversion

#### 3.2 IF with EQUAL TO
- **Test Case**: `IF WS-NUM1 EQUAL TO 100`
  - **Input**: Num1 = 100
  - **Expected**: Condition true
  - **Java**: `if (wsNum1 == 100) { ... }`
  - **Validates**: EQUAL TO → == conversion

#### 3.3 IF with NOT EQUAL TO
- **Test Case**: `IF WS-ERROR-FLAG NOT EQUAL TO 1`
  - **Input**: Error flag = 0
  - **Expected**: Condition true
  - **Java**: `if (wsErrorFlag != 1) { ... }`
  - **Validates**: NOT EQUAL TO → != conversion

#### 3.4 Nested IF Statements
- **Test Case**: Nested IF for salary range checking
  - **Input**: Salary = 50000
  - **Expected**: Both conditions true (> 40000 AND < 80000)
  - **Java**: Nested if blocks
  - **Validates**: Nested conditional logic

#### 3.5 AND Condition
- **Test Case**: `IF WS-IS-VALID = 1 AND WS-ERROR-FLAG = 0`
  - **Input**: Valid = 1, Error = 0
  - **Expected**: Both conditions true
  - **Java**: `if (wsIsValid == 1 && wsErrorFlag == 0) { ... }`
  - **Validates**: Logical AND operator (AND → &&)

#### 3.6 OR Condition
- **Test Case**: `IF WS-COUNTER = 100 OR WS-COUNTER = 200`
  - **Input**: Counter = 100
  - **Expected**: First condition true
  - **Java**: `if (wsCounter == 100 || wsCounter == 200) { ... }`
  - **Validates**: Logical OR operator (OR → ||)

#### 3.7 NOT Condition
- **Test Case**: `IF NOT WS-ERROR-FLAG = 1`
  - **Input**: Error flag = 0
  - **Expected**: Condition true
  - **Java**: `if (!(wsErrorFlag == 1)) { ... }`
  - **Validates**: Logical NOT operator (NOT → !)

---

### 4. Loop Operations Tests

#### 4.1 PERFORM n TIMES
- **Test Case**: `PERFORM LOOP-INCREMENT 5 TIMES`
  - **Expected**: Counter incremented 5 times
  - **Java**: `for (int i = 0; i < 5; i++) { loopIncrement(); }`
  - **Validates**: Fixed iteration loop

#### 4.2 PERFORM UNTIL
- **Test Case**: `PERFORM LOOP-UNTIL-TEST UNTIL WS-LOOP-INDEX GREATER THAN 3`
  - **Expected**: Loop executes 3 times
  - **Java**: `while (!(wsLoopIndex > 3)) { loopUntilTest(); }`
  - **Validates**: Conditional loop termination

#### 4.3 PERFORM VARYING
- **Test Case**: `PERFORM VARYING WS-COUNTER FROM 1 BY 1 UNTIL WS-COUNTER GREATER THAN 5`
  - **Expected**: Sum of 1+2+3+4+5 = 15
  - **Java**: `for (wsCounter = 1; !(wsCounter > 5); wsCounter += 1) { ... }`
  - **Validates**: Loop with automatic increment

#### 4.4 Simple PERFORM
- **Test Case**: `PERFORM CALCULATE-TOTAL`
  - **Expected**: Paragraph method called
  - **Java**: `calculateTotal();`
  - **Validates**: Method invocation

---

### 5. String Operations Tests

#### 5.1 MOVE String Literal
- **Test Case**: `MOVE 'John' TO WS-FIRST-NAME`
  - **Expected**: Variable contains 'John'
  - **Java**: `wsFirstName = "John";`
  - **Validates**: String literal assignment

#### 5.2 String Variable Copy
- **Test Case**: `MOVE WS-FIRST-NAME TO WS-FULL-NAME`
  - **Expected**: Full name contains first name
  - **Java**: `wsFullName = wsFirstName;`
  - **Validates**: Variable-to-variable string copy

#### 5.3 MOVE Numeric
- **Test Case**: `MOVE 100 TO WS-COUNTER`
  - **Expected**: Counter = 100
  - **Java**: `wsCounter = 100;`
  - **Validates**: Numeric literal assignment

#### 5.4 MOVE SPACES
- **Test Case**: `MOVE SPACES TO WS-NAME`
  - **Expected**: Variable initialized to empty/spaces
  - **Java**: `wsName = "";`
  - **Validates**: SPACES keyword conversion

---

### 6. I/O Operations Tests

#### 6.1 Simple DISPLAY
- **Test Case**: `DISPLAY 'Hello World'`
  - **Expected**: Prints "Hello World"
  - **Java**: `System.out.println("Hello World");`
  - **Validates**: Basic output

#### 6.2 DISPLAY Variable
- **Test Case**: `DISPLAY WS-NAME`
  - **Expected**: Prints variable value
  - **Java**: `System.out.println(wsName);`
  - **Validates**: Variable output

#### 6.3 DISPLAY Multiple Items
- **Test Case**: `DISPLAY 'Name: ' WS-NAME`
  - **Expected**: Prints "Name: " followed by variable value
  - **Java**: `System.out.print("Name: " + wsName);`
  - **Validates**: Multiple item concatenation

#### 6.4 ACCEPT Statement
- **Test Case**: `ACCEPT WS-NAME`
  - **Expected**: TODO comment for Scanner implementation
  - **Java**: `// TODO: Implement input for wsName using Scanner`
  - **Validates**: Structure in place for future implementation

---

### 7. Data Validation Tests

#### 7.1 Minimum Salary Validation
- **Test Case**: Check if salary < minimum, adjust if needed
  - **Input**: Salary = 25000, Min = 30000
  - **Expected**: Salary adjusted to 30000
  - **Validates**: Lower bound validation and correction

#### 7.2 Maximum Salary Validation
- **Test Case**: Check if salary > maximum, adjust if needed
  - **Input**: Salary = 200000, Max = 150000
  - **Expected**: Salary adjusted to 150000
  - **Validates**: Upper bound validation and correction

#### 7.3 Range Validation
- **Test Case**: Check if salary within valid range
  - **Input**: Salary = 65000, Min = 30000, Max = 150000
  - **Expected**: Validation passes
  - **Validates**: Range checking with AND condition

---

### 8. Semantic Analysis Tests

#### 8.1 Variable Existence Validation
- **Test**: Reference to undefined variable
- **Expected**: Warning message logged
- **Validates**: Symbol table checking

#### 8.2 Type Compatibility
- **Test**: Operations on compatible types
- **Expected**: No warnings for valid operations
- **Validates**: Type checking in semantic analyzer

#### 8.3 Statement Structure Validation
- **Test**: Proper statement syntax
- **Expected**: Validation passes for correct syntax
- **Validates**: Statement structure verification

#### 8.4 MOVE Statement Validation
- **Test**: `MOVE source TO target`
- **Expected**: Both variables exist in symbol table
- **Validates**: `validateMoveStatement()` method

#### 8.5 Arithmetic Statement Validation
- **Test**: ADD, SUBTRACT, MULTIPLY, DIVIDE
- **Expected**: All referenced variables exist
- **Validates**: `validateArithmeticStatement()` method

#### 8.6 COMPUTE Statement Validation
- **Test**: `COMPUTE target = expression`
- **Expected**: Target variable exists, expression is valid
- **Validates**: `validateComputeStatement()` method

#### 8.7 IF Statement Validation
- **Test**: `IF condition ... END-IF`
- **Expected**: Condition is present and valid
- **Validates**: `validateIfStatement()` method

---

### 9. Utility Functions Tests

#### 9.1 Naming Conversion
- **Test**: `ConversionUtils.toJavaCamelCase("WS-CUSTOMER-NAME")`
- **Expected**: "wsCustomerName"
- **Validates**: COBOL to Java camelCase conversion

- **Test**: `ConversionUtils.toJavaPascalCase("CUSTOMER-PROGRAM")`
- **Expected**: "CustomerProgram"
- **Validates**: COBOL to Java PascalCase conversion

#### 9.2 Type Checking
- **Test**: `ConversionUtils.isNumericLiteral("123.45")`
- **Expected**: true
- **Validates**: Numeric literal detection

- **Test**: `ConversionUtils.isStringLiteral("'Hello'")`
- **Expected**: true
- **Validates**: String literal detection

- **Test**: `ConversionUtils.isValidIdentifier("WS-NAME")`
- **Expected**: true
- **Validates**: COBOL identifier validation

#### 9.3 String Operations
- **Test**: `ConversionUtils.removeQuotes("'Hello'")`
- **Expected**: "Hello"
- **Validates**: Quote removal

- **Test**: `ConversionUtils.sanitizeForJava("Line\nBreak")`
- **Expected**: "Line\\nBreak"
- **Validates**: Special character escaping

- **Test**: `ConversionUtils.extractVariables("A + B * 2")`
- **Expected**: ["A", "B"]
- **Validates**: Variable extraction from expressions

#### 9.4 Type Mapping
- **Test**: `ConversionUtils.getJavaTypeForPicture("PIC 99")`
- **Expected**: "int"
- **Validates**: COBOL to Java type mapping

---

## Edge Cases Covered

1. **Zero Values**: Variables initialized to zero
2. **Decimal Precision**: Values with 2 decimal places (V99)
3. **Large Numbers**: 7-digit numeric values → BigDecimal
4. **String Padding**: SPACES initialization
5. **Nested Structures**: Group-level data items
6. **Multiple Conditions**: AND, OR, NOT combinations
7. **Loop Boundaries**: UNTIL conditions with GREATER THAN
8. **Division by Zero Prevention**: Using safe divisors
9. **Remainder Calculations**: DIVIDE with REMAINDER clause
10. **Complex Expressions**: Parentheses and operator precedence
11. **Variable Name Conversion**: Hyphens to camelCase
12. **Mixed Literals and Variables**: In DISPLAY statements
13. **Expression Parsing**: Variable extraction from COMPUTE

---

## Expected Converter Behavior

### Parser Requirements
The parser should correctly identify and extract:
- PROGRAM-ID and AUTHOR
- All data items with level numbers, names, PIC clauses, and VALUES
- All paragraph names
- All statement types (MOVE, DISPLAY, ADD, SUBTRACT, MULTIPLY, DIVIDE, COMPUTE, IF, etc.)
- Conditional structures (IF/END-IF)
- Loop structures (PERFORM variations)
- Arithmetic operators and expressions

### Generator Requirements
The Java generator should produce:
- A class named after the PROGRAM-ID (PascalCase)
- Instance variables for all WORKING-STORAGE items
- Methods for each paragraph (camelCase)
- Proper Java equivalents for all COBOL statements
- Correct data type mappings (int, long, BigDecimal, String)
- Proper control flow structures (if/else, for/while loops)
- Arithmetic operations with correct operator precedence
- TODO comments for unsupported features

### Semantic Analyzer Requirements
The analyzer should:
- Build and maintain a symbol table
- Validate variable references in all statements
- Check data type compatibility
- Identify undefined variables
- Verify paragraph references in PERFORM statements
- Validate statement structure
- Detect potential runtime issues
- Provide detailed warning messages

### Utility Functions Requirements
The ConversionUtils class should provide:
- Naming conversion (camelCase, PascalCase)
- Type checking (numeric, string, identifier)
- String operations (sanitize, remove quotes)
- Variable extraction from expressions
- Type mapping (COBOL PIC to Java types)

---

## Running the Tests

### Command
```bash
# Build the project
mvn clean install

# Test HELLO-WORLD.cbl
java -jar target/cobol-to-java-converter-1.0-SNAPSHOT.jar src/test/resources/samples/HELLO-WORLD.cbl

# Test ADVANCED-FEATURES.cbl
java -jar target/cobol-to-java-converter-1.0-SNAPSHOT.jar src/test/resources/samples/ADVANCED-FEATURES.cbl

# Test COMPREHENSIVE-TEST.cbl
java -jar target/cobol-to-java-converter-1.0-SNAPSHOT.jar src/test/resources/samples/COMPREHENSIVE-TEST.cbl
```

### Expected Output
- Java file: `output/<ProgramName>.java`
- No parsing errors
- All statements converted
- Compilable Java code
- Semantic analysis warnings (if any issues detected)

### Validation Steps
1. Verify the Java file is created in the output directory
2. Check that all paragraphs are converted to methods
3. Ensure all variables are declared with correct types
4. Validate arithmetic operations are correctly translated
5. Confirm conditional logic is properly structured
6. Verify loop constructs are appropriate Java equivalents
7. Check that variable names follow Java conventions
8. Ensure COMPUTE expressions maintain operator precedence
9. Validate that semantic warnings are appropriate

---

## Success Criteria

✅ **Parser**: Successfully parses all test files without errors  
✅ **Data Items**: All variables correctly extracted with proper types  
✅ **Paragraphs**: All paragraphs identified and converted to methods  
✅ **Statements**: All supported statements parsed and converted  
✅ **Arithmetic**: ADD, SUBTRACT, MULTIPLY, DIVIDE, COMPUTE work correctly  
✅ **Control Flow**: IF statements with comparison and logical operators  
✅ **Generator**: Produces valid, compilable Java code  
✅ **Analyzer**: Semantic validation catches undefined variables and type issues  
✅ **Utilities**: ConversionUtils provides accurate conversions  
✅ **Output**: Java code executes and produces expected output  
✅ **Naming**: COBOL names properly converted to Java conventions  

---

## Version History

### Version 1.1.0 (2026-05-01)
- Added arithmetic operations tests (ADD, SUBTRACT, MULTIPLY, DIVIDE, COMPUTE)
- Added control flow tests (IF with comparison and logical operators)
- Added semantic analysis validation tests
- Added utility functions tests (ConversionUtils)
- Enhanced I/O operations tests (multiple item DISPLAY)
- Added ADVANCED-FEATURES.cbl test file
- Updated documentation with new features

### Version 1.0.0 (2026-04-30)
- Initial test suite
- Basic COBOL parsing tests
- Simple statement conversion tests
- COMPREHENSIVE-TEST.cbl created

---

## Additional Test Programs

For comparison and additional coverage:
- **HELLO-WORLD.cbl** - Basic program structure and simple output
- **ADVANCED-FEATURES.cbl** - Arithmetic operations, conditionals, and COMPUTE
- **COMPREHENSIVE-TEST.cbl** - Extensive testing of all features and edge cases
- **LEAP-YEAR-CHECK.cbl** - Complex conditional logic example

---

## References

- [FEATURES.md](../../docs/FEATURES.md) - Comprehensive feature documentation
- [CHANGELOG.md](../../CHANGELOG.md) - Version history and changes
- [ENHANCEMENTS_SUMMARY.md](../../ENHANCEMENTS_SUMMARY.md) - Enhancement details
- [README.md](../../README.md) - Main project documentation

---

*Last Updated: 2026-05-02*  
*Made with Bob*