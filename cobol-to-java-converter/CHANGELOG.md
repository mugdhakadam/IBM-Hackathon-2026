# Changelog

All notable changes to the COBOL to Java Converter project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.0] - 2026-05-01

### Added

#### Arithmetic Operations
- **ADD Statement Support**: Full implementation of ADD with TO and GIVING clauses
  - `ADD A TO B` - Adds A to B, stores in B
  - `ADD A B TO C` - Adds multiple values to C
  - `ADD A TO B GIVING C` - Adds A and B, stores in C
  
- **SUBTRACT Statement Support**: Complete SUBTRACT implementation
  - `SUBTRACT A FROM B` - Subtracts A from B, stores in B
  - `SUBTRACT A FROM B GIVING C` - Subtracts A from B, stores in C
  
- **MULTIPLY Statement Support**: Full MULTIPLY functionality
  - `MULTIPLY A BY B` - Multiplies B by A, stores in B
  - `MULTIPLY A BY B GIVING C` - Multiplies A by B, stores in C
  
- **DIVIDE Statement Support**: Complete DIVIDE implementation
  - `DIVIDE A INTO B` - Divides B by A, stores in B
  - `DIVIDE A BY B GIVING C` - Divides A by B, stores in C
  
- **COMPUTE Statement Support**: Expression evaluation
  - `COMPUTE variable = expression` - Evaluates complex expressions
  - Supports standard arithmetic operators (+, -, *, /)
  - Automatic variable name conversion in expressions

#### Control Flow
- **IF Statement Support**: Basic conditional logic
  - Comparison operators: EQUAL TO, NOT EQUAL TO, GREATER THAN, LESS THAN
  - Logical operators: AND, OR, NOT
  - Automatic conversion to Java comparison operators
  - Variable name conversion in conditions

#### I/O Enhancements
- **Enhanced DISPLAY Statement**: Multiple item support
  - `DISPLAY 'text' variable` - Concatenates literals and variables
  - Improved handling of mixed literal and variable output
  - Better formatting for multi-item displays

- **ACCEPT Statement**: Basic structure (TODO for full implementation)
  - Generates placeholder for Scanner-based input
  - Validates target variable existence

#### Semantic Analysis
- **Enhanced Validation**: Comprehensive statement validation
  - Variable existence checking in symbol table
  - Type compatibility validation
  - Statement structure verification
  - Detailed warning messages for issues

- **Improved Symbol Table**: Better variable tracking
  - Validates MOVE statement targets
  - Checks arithmetic operation variables
  - Validates COMPUTE statement targets
  - Verifies IF statement conditions
  - Validates I/O statement variables

#### Utility Functions
- **ConversionUtils Class**: New utility library
  - `isNumericLiteral()` - Detects numeric literals
  - `isStringLiteral()` - Detects string literals
  - `isValidIdentifier()` - Validates COBOL identifiers
  - `removeQuotes()` - Strips quotes from literals
  - `toJavaCamelCase()` - Converts COBOL names to camelCase
  - `toJavaPascalCase()` - Converts COBOL names to PascalCase
  - `sanitizeForJava()` - Escapes special characters
  - `extractVariables()` - Extracts variables from expressions
  - `getJavaTypeForPicture()` - Maps COBOL types to Java types

#### Documentation
- **FEATURES.md**: Comprehensive feature documentation
  - Detailed examples for all supported features
  - Type mapping tables
  - Best practices guide
  - Limitations and planned enhancements

- **Enhanced README.md**: Updated with new features
  - Expanded statement mapping table
  - Updated supported features list
  - Clear distinction between implemented and planned features

#### Test Resources
- **ADVANCED-FEATURES.cbl**: New comprehensive test file
  - Demonstrates all arithmetic operations
  - Shows conditional logic usage
  - Includes COMPUTE examples
  - Tests multiple statement types

### Changed
- **JavaCodeGenerator**: Major refactoring
  - Added 8 new statement conversion methods
  - Improved DISPLAY statement handling
  - Better error messages with TODO comments
  - More robust expression parsing

- **SemanticAnalyzer**: Enhanced validation
  - Added 6 new validation methods
  - Improved variable checking
  - Better warning messages
  - More comprehensive error detection

### Fixed
- **DISPLAY Statement**: Now properly handles multiple items
- **Variable Name Conversion**: More consistent camelCase conversion
- **Type Mapping**: Better handling of decimal types

### Technical Details

#### New Methods in JavaCodeGenerator
- `convertAddStatement()` - Converts ADD statements
- `convertSubtractStatement()` - Converts SUBTRACT statements
- `convertMultiplyStatement()` - Converts MULTIPLY statements
- `convertDivideStatement()` - Converts DIVIDE statements
- `convertComputeStatement()` - Converts COMPUTE statements
- `convertIfStatement()` - Converts IF statements
- `convertAcceptStatement()` - Converts ACCEPT statements

#### New Methods in SemanticAnalyzer
- `validateMoveStatement()` - Validates MOVE operations
- `validateArithmeticStatement()` - Validates arithmetic operations
- `validateComputeStatement()` - Validates COMPUTE expressions
- `validateIfStatement()` - Validates IF conditions
- `validateDisplayStatement()` - Validates DISPLAY content
- `validateAcceptStatement()` - Validates ACCEPT targets
- `checkVariableExists()` - Verifies variable declarations

#### Code Quality Improvements
- Added comprehensive inline documentation
- Improved error handling and logging
- Better separation of concerns
- More maintainable code structure

## [1.0.0] - 2026-04-30

### Added
- Initial release
- Basic COBOL parsing
- Simple statement conversion
- IDENTIFICATION DIVISION support
- DATA DIVISION (WORKING-STORAGE) support
- PROCEDURE DIVISION support
- Basic DISPLAY and MOVE statements
- PERFORM statement support
- STOP RUN support

---

## Future Releases

### Planned for [1.2.0]
- IF-ELSE-END-IF complete support
- EVALUATE (SWITCH) statements
- PERFORM UNTIL/TIMES/VARYING loops
- Enhanced error recovery
- Better code formatting

### Planned for [1.3.0]
- FILE SECTION support
- File I/O operations (OPEN, CLOSE, READ, WRITE)
- LINKAGE SECTION support
- OCCURS clause (arrays)
- REDEFINES clause

### Planned for [2.0.0]
- ANTLR-based parser
- COPY statement support
- Nested programs
- CALL statements
- Complete COBOL 85 standard support

---

## Contributing

When contributing, please:
1. Update this CHANGELOG with your changes
2. Follow semantic versioning
3. Add tests for new features
4. Update documentation

---

## References

- [Semantic Versioning](https://semver.org/)
- [Keep a Changelog](https://keepachangelog.com/)
- [COBOL Language Reference](https://www.ibm.com/docs/en/cobol-zos)

---

Last Updated: 2026-05-01