# Architecture Documentation

## System Overview

The COBOL to Java Converter is designed with a modular, pipeline-based architecture that processes COBOL source code through multiple stages to produce equivalent Java code.

## Architecture Diagram

```
┌─────────────────┐
│  COBOL Source   │
│     (.cbl)      │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Lexical        │
│  Analysis       │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Syntax         │
│  Parsing        │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  AST            │
│  Generation     │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Semantic       │
│  Analysis       │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Code           │
│  Generation     │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Java Source    │
│     (.java)     │
└─────────────────┘
```

## Component Architecture

### 1. Parser Layer (`com.converter.parser`)

**Purpose**: Parse COBOL source code and build an Abstract Syntax Tree (AST)

**Components**:
- `CobolParser.java`: Main parser class
  - Reads COBOL source files
  - Tokenizes input
  - Identifies divisions, sections, and statements
  - Builds AST using model classes

**Design Pattern**: Builder Pattern

**Key Responsibilities**:
- Line-by-line parsing
- Division detection (IDENTIFICATION, DATA, PROCEDURE)
- Statement recognition
- Data item extraction

### 2. Model Layer (`com.converter.model`)

**Purpose**: Represent COBOL and Java program structures

**COBOL Models**:
- `CobolProgram.java`: Root model for a COBOL program
- `DataItem.java`: Represents COBOL variables (PIC clauses)
- `Paragraph.java`: Represents COBOL paragraphs
- `Section.java`: Represents COBOL sections
- `Statement.java`: Represents COBOL statements

**Java Models**:
- `JavaClass.java`: Represents generated Java class
- `JavaField`: Inner class for Java fields
- `JavaMethod`: Inner class for Java methods

**Design Pattern**: Domain Model Pattern

### 3. Analyzer Layer (`com.converter.analyzer`)

**Purpose**: Perform semantic analysis and validation

**Components**:
- `SemanticAnalyzer.java`: Validates program semantics
  - Symbol table construction
  - Type checking
  - Variable usage validation
  - Control flow analysis

**Design Pattern**: Visitor Pattern (for AST traversal)

**Key Responsibilities**:
- Build symbol table
- Validate variable references
- Check type compatibility
- Detect undefined variables

### 4. Generator Layer (`com.converter.generator`)

**Purpose**: Generate Java source code from COBOL AST

**Components**:
- `JavaCodeGenerator.java`: Main code generator
  - Converts COBOL constructs to Java
  - Applies naming conventions
  - Generates class structure
  - Writes output files

**Design Pattern**: Template Method Pattern

**Key Responsibilities**:
- Type mapping (COBOL → Java)
- Statement conversion
- Method generation
- File output

### 5. Utility Layer (`com.converter.utils`)

**Purpose**: Provide common utilities (future expansion)

**Potential Components**:
- String manipulation utilities
- File I/O helpers
- Logging utilities
- Configuration management

## Data Flow

### Input Processing

```
COBOL File → Reader → Line Buffer → Parser State Machine
```

### Parsing Flow

```
Line → Token Recognition → Statement Classification → AST Node Creation
```

### Analysis Flow

```
AST → Symbol Table Builder → Type Checker → Validator
```

### Generation Flow

```
AST → Type Mapper → Statement Converter → Code Builder → File Writer
```

## Key Design Decisions

### 1. Simplified Parser

**Decision**: Use regex-based parsing instead of full grammar parser

**Rationale**:
- Faster development
- Easier to understand and modify
- Sufficient for basic COBOL constructs
- Can be replaced with ANTLR later

**Trade-offs**:
- Limited to simple COBOL syntax
- May miss edge cases
- Less robust error handling

### 2. Intermediate Representation

**Decision**: Use domain models as intermediate representation

**Rationale**:
- Clear separation of concerns
- Easy to extend
- Testable components
- Language-agnostic representation

**Benefits**:
- Can add other target languages (Python, C#)
- Easier to implement optimizations
- Better maintainability

### 3. Direct Code Generation

**Decision**: Generate code directly without optimization passes

**Rationale**:
- Simpler implementation
- Readable output
- Easier debugging

**Future Enhancement**:
- Add optimization passes
- Implement code beautification
- Add dead code elimination

## Extension Points

### Adding New COBOL Features

1. **Update Parser**:
   - Add regex patterns for new constructs
   - Create new statement types
   - Update AST models

2. **Update Analyzer**:
   - Add validation rules
   - Update symbol table logic

3. **Update Generator**:
   - Add conversion logic
   - Update type mappings

### Adding New Target Languages

1. Create new generator class (e.g., `PythonCodeGenerator`)
2. Implement language-specific type mappings
3. Implement statement converters
4. Add language-specific templates

## Performance Considerations

### Current Implementation

- **Time Complexity**: O(n) where n = number of lines
- **Space Complexity**: O(n) for AST storage
- **Bottlenecks**: File I/O, regex matching

### Optimization Opportunities

1. **Parallel Processing**: Process multiple files concurrently
2. **Caching**: Cache parsed results
3. **Streaming**: Process large files in chunks
4. **Compilation**: Use compiled grammar (ANTLR)

## Error Handling Strategy

### Parser Errors

- Log line number and content
- Continue parsing when possible
- Collect all errors before failing

### Semantic Errors

- Validate after complete parsing
- Provide detailed error messages
- Suggest corrections

### Generation Errors

- Fail fast on critical errors
- Generate partial output with TODOs
- Log warnings for unsupported features

## Testing Strategy

### Unit Tests

- Test individual components
- Mock dependencies
- Test edge cases

### Integration Tests

- Test complete conversion pipeline
- Use sample COBOL programs
- Verify generated Java code

### End-to-End Tests

- Compile generated Java code
- Run and verify output
- Compare with COBOL behavior

## Security Considerations

### Input Validation

- Validate file paths
- Check file sizes
- Sanitize input

### Output Safety

- Prevent path traversal
- Validate output directory
- Handle file permissions

## Future Enhancements

### Phase 1: Core Features
- [ ] Complete COBOL statement support
- [ ] File I/O operations
- [ ] Array handling (OCCURS)
- [ ] REDEFINES support

### Phase 2: Advanced Features
- [ ] ANTLR-based parser
- [ ] COPY statement support
- [ ] Nested programs
- [ ] CALL statements

### Phase 3: Optimization
- [ ] Code optimization passes
- [ ] Performance improvements
- [ ] Parallel processing
- [ ] Incremental compilation

### Phase 4: Enterprise Features
- [ ] Batch processing
- [ ] Configuration management
- [ ] Plugin architecture
- [ ] IDE integration

## Maintenance Guidelines

### Code Style

- Follow Java naming conventions
- Use meaningful variable names
- Add JavaDoc comments
- Keep methods focused and small

### Documentation

- Update README for new features
- Document design decisions
- Maintain architecture diagrams
- Add inline comments for complex logic

### Version Control

- Use semantic versioning
- Tag releases
- Maintain changelog
- Document breaking changes

## References

- [COBOL Language Specification](https://www.ibm.com/docs/en/cobol-zos)
- [Java Language Specification](https://docs.oracle.com/javase/specs/)
- [Design Patterns](https://refactoring.guru/design-patterns)
- [Clean Code Principles](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882)

---

Last Updated: 2026-05-01