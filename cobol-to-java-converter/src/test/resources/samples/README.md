# COBOL Test Samples

This directory contains COBOL test programs designed to validate and demonstrate the capabilities of the COBOL to Java converter.

## Available Test Programs

### 1. HELLO-WORLD.cbl
**Complexity**: Basic  
**Purpose**: Simple program demonstrating basic COBOL structure  
**Features Tested**:
- IDENTIFICATION DIVISION
- DATA DIVISION with WORKING-STORAGE
- Basic PIC clauses (X, 9, V)
- DISPLAY statements
- PERFORM paragraph calls
- STOP RUN

**Use Case**: First test to verify basic converter functionality

---

### 2. ADVANCED-FEATURES.cbl
**Complexity**: Intermediate  
**Purpose**: Tests arithmetic operations and conditional logic  
**Features Tested**:
- Multiple numeric data types
- ADD, SUBTRACT, MULTIPLY, DIVIDE operations
- IF-THEN-END-IF conditionals
- COMPUTE statements
- MOVE operations
- Multiple paragraph execution

**Use Case**: Validate arithmetic and conditional statement conversion

---

### 3. LEAP-YEAR-CHECK.cbl
**Complexity**: Intermediate  
**Purpose**: Complex conditional logic with multiple conditions  
**Features Tested**:
- ACCEPT statement (input)
- DIVIDE with REMAINDER
- Complex IF conditions with AND/OR
- Nested logical expressions
- 77-level data items

**Use Case**: Test complex boolean logic and input handling

---

### 4. COMPREHENSIVE-TEST.cbl ⭐ **NEW**
**Complexity**: Advanced  
**Purpose**: Comprehensive test covering all supported features  
**Features Tested**:
- Hierarchical data structures (01/05 levels)
- All arithmetic operations (ADD, SUBTRACT, MULTIPLY, DIVIDE)
- DIVIDE with REMAINDER
- Multiple operand arithmetic
- Simple and nested IF statements
- IF-ELSE branching
- AND, OR, NOT logical operators
- COMPUTE with complex expressions
- String operations (MOVE)
- Data validation logic
- Business logic scenarios
- Flag/boolean management
- 30+ variables
- 11 paragraphs
- 100+ statements

**Use Case**: Complete validation of converter capabilities and edge cases

**Test Categories**:
1. Data Division Tests (various PIC clauses, hierarchical structures)
2. Arithmetic Operations (all operations with different forms)
3. Conditional Logic (simple, nested, compound conditions)
4. Loop Operations (all PERFORM variations)
5. COMPUTE Statements (simple to complex expressions)
6. String Operations (MOVE, concatenation simulation)
7. Data Validation (min/max checks, range validation)
8. Business Logic (payroll, employee management)

---

## Running the Tests

### Prerequisites
Ensure the converter is built:
```bash
cd cobol-to-java-converter
mvn clean package
```

### Running Individual Tests

#### Test 1: Hello World
```bash
java -jar target/cobol-to-java-converter-1.0-SNAPSHOT.jar src/test/resources/samples/HELLO-WORLD.cbl
```
**Expected Output**: `output/HelloWorld.java`

#### Test 2: Advanced Features
```bash
java -jar target/cobol-to-java-converter-1.0-SNAPSHOT.jar src/test/resources/samples/ADVANCED-FEATURES.cbl
```
**Expected Output**: `output/AdvancedFeatures.java`

#### Test 3: Leap Year Check
```bash
java -jar target/cobol-to-java-converter-1.0-SNAPSHOT.jar src/test/resources/samples/LEAP-YEAR-CHECK.cbl
```
**Expected Output**: `output/LeapYearCheck.java`

#### Test 4: Comprehensive Test ⭐
```bash
java -jar target/cobol-to-java-converter-1.0-SNAPSHOT.jar src/test/resources/samples/COMPREHENSIVE-TEST.cbl
```
**Expected Output**: `output/ComprehensiveTest.java`

---

## Running All Tests

### Batch Conversion (Windows PowerShell)
```powershell
$samples = @(
    "HELLO-WORLD.cbl",
    "ADVANCED-FEATURES.cbl", 
    "LEAP-YEAR-CHECK.cbl",
    "COMPREHENSIVE-TEST.cbl"
)

foreach ($sample in $samples) {
    Write-Host "Converting $sample..." -ForegroundColor Green
    java -jar target/cobol-to-java-converter-1.0-SNAPSHOT.jar "src/test/resources/samples/$sample"
}

Write-Host "`nAll conversions complete!" -ForegroundColor Cyan
```

### Batch Conversion (Linux/Mac)
```bash
#!/bin/bash
samples=(
    "HELLO-WORLD.cbl"
    "ADVANCED-FEATURES.cbl"
    "LEAP-YEAR-CHECK.cbl"
    "COMPREHENSIVE-TEST.cbl"
)

for sample in "${samples[@]}"; do
    echo "Converting $sample..."
    java -jar target/cobol-to-java-converter-1.0-SNAPSHOT.jar "src/test/resources/samples/$sample"
done

echo "All conversions complete!"
```

---

## Validation Steps

After running the converter, validate the output:

### 1. Check File Creation
```bash
ls -la output/
```
Expected files:
- `HelloWorld.java`
- `AdvancedFeatures.java`
- `LeapYearCheck.java`
- `ComprehensiveTest.java`

### 2. Verify Java Compilation
```bash
cd output
javac HelloWorld.java
javac AdvancedFeatures.java
javac LeapYearCheck.java
javac ComprehensiveTest.java
```
All files should compile without errors.

### 3. Run Generated Java Programs
```bash
java HelloWorld
java AdvancedFeatures
java ComprehensiveTest
```

---

## Expected Results

### Parser Validation
✅ All COBOL files should parse without errors  
✅ All data items should be extracted correctly  
✅ All paragraphs should be identified  
✅ All statements should be recognized  

### Generator Validation
✅ Java files should be created in the output directory  
✅ Class names should match PROGRAM-ID (converted to PascalCase)  
✅ All variables should be declared as instance fields  
✅ All paragraphs should become methods  
✅ All statements should have Java equivalents  

### Semantic Validation
✅ No undefined variable references  
✅ No undefined paragraph references  
✅ Data type compatibility maintained  
✅ No semantic errors reported  

### Compilation Validation
✅ Generated Java code should compile without errors  
✅ No syntax errors in generated code  
✅ Proper imports included  

### Execution Validation
✅ Programs should run without runtime errors  
✅ Output should match expected COBOL behavior  
✅ Calculations should produce correct results  

---

## Test Coverage Matrix

| Feature | HELLO-WORLD | ADVANCED | LEAP-YEAR | COMPREHENSIVE |
|---------|-------------|----------|-----------|---------------|
| PROGRAM-ID | ✅ | ✅ | ✅ | ✅ |
| AUTHOR | ✅ | ✅ | ❌ | ✅ |
| WORKING-STORAGE | ✅ | ✅ | ✅ | ✅ |
| PIC X | ✅ | ✅ | ❌ | ✅ |
| PIC 9 | ✅ | ✅ | ✅ | ✅ |
| PIC V99 | ✅ | ✅ | ❌ | ✅ |
| 77-level | ❌ | ❌ | ✅ | ❌ |
| 01-level | ✅ | ✅ | ❌ | ✅ |
| 05-level | ❌ | ❌ | ❌ | ✅ |
| DISPLAY | ✅ | ✅ | ✅ | ✅ |
| MOVE | ❌ | ✅ | ❌ | ✅ |
| ACCEPT | ❌ | ❌ | ✅ | ❌ |
| ADD | ❌ | ✅ | ❌ | ✅ |
| SUBTRACT | ❌ | ✅ | ❌ | ✅ |
| MULTIPLY | ❌ | ✅ | ❌ | ✅ |
| DIVIDE | ❌ | ✅ | ✅ | ✅ |
| REMAINDER | ❌ | ❌ | ✅ | ✅ |
| COMPUTE | ❌ | ✅ | ❌ | ✅ |
| IF-THEN | ❌ | ✅ | ✅ | ✅ |
| IF-ELSE | ❌ | ❌ | ✅ | ✅ |
| Nested IF | ❌ | ❌ | ❌ | ✅ |
| AND | ❌ | ❌ | ✅ | ✅ |
| OR | ❌ | ❌ | ✅ | ✅ |
| NOT | ❌ | ❌ | ✅ | ✅ |
| PERFORM | ✅ | ✅ | ❌ | ✅ |
| PERFORM n TIMES | ❌ | ❌ | ❌ | ✅ |
| PERFORM UNTIL | ❌ | ❌ | ❌ | ✅ |
| PERFORM VARYING | ❌ | ❌ | ❌ | ✅ |
| STOP RUN | ✅ | ✅ | ✅ | ✅ |

**Legend**: ✅ Tested | ❌ Not tested

---

## Troubleshooting

### Issue: Parser Errors
**Solution**: Check COBOL syntax, ensure proper spacing and periods

### Issue: Java Compilation Errors
**Solution**: Review generated code, check for unsupported COBOL features

### Issue: Runtime Errors
**Solution**: Verify data type conversions, check for null pointer issues

### Issue: Incorrect Output
**Solution**: Compare COBOL logic with Java translation, verify arithmetic operations

---

## Adding New Test Cases

To add a new test program:

1. Create a new `.cbl` file in this directory
2. Follow COBOL syntax conventions
3. Include comments explaining the test purpose
4. Add entry to this README
5. Update the test coverage matrix
6. Document expected behavior

### Template for New Tests
```cobol
       IDENTIFICATION DIVISION.
       PROGRAM-ID. YOUR-TEST-NAME.
       AUTHOR. YOUR-NAME.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01 WS-TEST-VAR PIC X(20) VALUE 'Test'.
       
       PROCEDURE DIVISION.
       MAIN-LOGIC.
           DISPLAY 'Test: YOUR-TEST-NAME'.
           PERFORM YOUR-TEST-PARAGRAPH.
           STOP RUN.
           
       YOUR-TEST-PARAGRAPH.
           DISPLAY 'Testing feature...'.

      *> Made with Bob
```

---

## Documentation

For detailed test case documentation, see:
- [`TEST-CASES-DOCUMENTATION.md`](./TEST-CASES-DOCUMENTATION.md) - Comprehensive test case details

For converter documentation, see:
- [`../../docs/FEATURES.md`](../../docs/FEATURES.md) - Supported features
- [`../../docs/QUICK_START.md`](../../docs/QUICK_START.md) - Getting started guide
- [`../../README.md`](../../README.md) - Project overview

---

## Contributing

When adding test cases:
1. Ensure they test specific, well-defined features
2. Include both positive and negative test scenarios
3. Document expected behavior clearly
4. Keep tests focused and maintainable
5. Update all relevant documentation

---

*Made with Bob*