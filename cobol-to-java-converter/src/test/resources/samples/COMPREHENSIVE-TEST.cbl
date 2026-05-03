       IDENTIFICATION DIVISION.
       PROGRAM-ID. COMPREHENSIVE-TEST.
       AUTHOR. COBOL-TO-JAVA-CONVERTER-TEAM.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
      *> Basic data types with various PIC clauses
       01 WS-EMPLOYEE-RECORD.
           05 WS-EMP-ID PIC 9(6) VALUE 55555.
           05 WS-EMP-NAME PIC X(30) VALUE 'Mugdha Kadam'.
           05 WS-EMP-SALARY PIC 9(7)V99 VALUE 175000.50.
           05 WS-EMP-DEPT PIC X(20) VALUE 'Engineering'.
       
      *> Numeric variables for calculations
       01 WS-COUNTER PIC 99 VALUE 0.
       01 WS-TOTAL PIC 9(8)V99 VALUE 0.
       01 WS-SUBTOTAL PIC 9(6)V99 VALUE 0.
       01 WS-TAX-RATE PIC V99 VALUE 0.15.
       01 WS-TAX-AMOUNT PIC 9(6)V99 VALUE 0.
       01 WS-NET-AMOUNT PIC 9(8)V99 VALUE 0.
       
      *> Variables for arithmetic operations
       01 WS-NUM1 PIC 999 VALUE 200.
       01 WS-NUM2 PIC 999 VALUE 50.
       01 WS-NUM3 PIC 999 VALUE 25.
       01 WS-RESULT PIC 9(5) VALUE 0.
       01 WS-REMAINDER PIC 99 VALUE 0.
       
      *> String manipulation variables
       01 WS-FIRST-NAME PIC X(15) VALUE 'Tinker'.
       01 WS-LAST-NAME PIC X(15) VALUE 'Bell'.
       01 WS-FULL-NAME PIC X(30) VALUE SPACES.
       01 WS-MESSAGE PIC X(50) VALUE SPACES.
       
      *> Boolean/flag variables
       01 WS-IS-VALID PIC 9 VALUE 0.
       01 WS-IS-PROCESSED PIC 9 VALUE 0.
       01 WS-ERROR-FLAG PIC 9 VALUE 0.
       
      *> Loop control variables
       01 WS-LOOP-INDEX PIC 99 VALUE 1.
       01 WS-MAX-ITERATIONS PIC 99 VALUE 10.
       
      *> Date and time related
       01 WS-CURRENT-YEAR PIC 9(4) VALUE 2026.
       01 WS-CURRENT-MONTH PIC 99 VALUE 5.
       01 WS-CURRENT-DAY PIC 99 VALUE 2.
       
      *> Constants
       01 WS-COMPANY-NAME PIC X(30) VALUE 'Mugdha Techie Solution'.
       01 WS-VERSION PIC X(10) VALUE 'v1.0.0'.
       01 WS-MAX-SALARY PIC 9(7)V99 VALUE 150000.00.
       01 WS-MIN-SALARY PIC 9(7)V99 VALUE 30000.00.
       
       PROCEDURE DIVISION.
       MAIN-LOGIC.
           DISPLAY '========================================'.
           DISPLAY 'COMPREHENSIVE COBOL TEST PROGRAM'.
           DISPLAY 'Program: ' WS-COMPANY-NAME.
           DISPLAY 'Version: ' WS-VERSION.
           DISPLAY '========================================'.
           DISPLAY ' '.
           
           PERFORM DISPLAY-EMPLOYEE-INFO.
           PERFORM ARITHMETIC-TESTS.
           PERFORM CONDITIONAL-TESTS.
           PERFORM STRING-OPERATIONS.
           PERFORM SALARY-VALIDATION.
           PERFORM FINAL-SUMMARY.
           
           DISPLAY ' '.
           DISPLAY 'Program execution completed successfully.'.
           STOP RUN.
           
       DISPLAY-EMPLOYEE-INFO.
           DISPLAY '--- Employee Information ---'.
           DISPLAY 'Employee ID: ' WS-EMP-ID.
           DISPLAY 'Name: ' WS-EMP-NAME.
           DISPLAY 'Department: ' WS-EMP-DEPT.
           DISPLAY 'Salary: $' WS-EMP-SALARY.
           DISPLAY ' '.
           
       ARITHMETIC-TESTS.
           DISPLAY '--- Arithmetic Operations Test ---'.
           
      *> Test ADD operation
           MOVE 100 TO WS-NUM1.
           MOVE 50 TO WS-NUM2.
           ADD WS-NUM1 TO WS-NUM2.
           DISPLAY 'ADDITION ANS' WS-NUM2.
           
      *> Test SUBTRACT operation
           MOVE 200 TO WS-NUM1.
           SUBTRACT 75 FROM WS-NUM1.
           DISPLAY 'SUBTRACT ANS:  ' WS-NUM1.
           
      *> Test MULTIPLY operation
           MOVE 15 TO WS-NUM1.
           MOVE 4 TO WS-NUM2.
           MULTIPLY WS-NUM1 BY WS-NUM2 GIVING WS-RESULT.
           DISPLAY 'MULTIPLY ANS:  = ' WS-RESULT.
           
      *> Test DIVIDE operation
           MOVE 100 TO WS-NUM1.
           MOVE 4 TO WS-NUM2.
           DIVIDE WS-NUM1 BY WS-NUM2 GIVING WS-RESULT.
           DISPLAY 'DIVIDE ANS = ' WS-RESULT.
           
      *> Test DIVIDE with REMAINDER
           MOVE 100 TO WS-NUM1.
           MOVE 7 TO WS-NUM2.
           DIVIDE WS-NUM1 BY WS-NUM2 GIVING WS-RESULT 
               REMAINDER WS-REMAINDER.
           DISPLAY 'DIVIDE: 100 / 7 = ' WS-RESULT 
               ' remainder ' WS-REMAINDER.
           
                 
           DISPLAY ' '.
           
       CONDITIONAL-TESTS.
           DISPLAY '--- Conditional Logic Tests ---'.
           
      *> Simple IF test
           MOVE 25 TO WS-COUNTER.
           IF WS-COUNTER GREATER THAN 20
               DISPLAY 'Test 1: Counter (25) > 20 - PASS'
           END-IF.
           
      *> IF-ELSE test
           MOVE 15 TO WS-COUNTER.
           IF WS-COUNTER GREATER THAN 20
               DISPLAY 'Test 2: Counter > 20 - FAIL'
           ELSE
               DISPLAY 'Test 2: Counter (15) <= 20 - PASS'
           END-IF.
           
      *> Nested IF test
           MOVE 50000 TO WS-EMP-SALARY.
           IF WS-EMP-SALARY GREATER THAN 40000
               IF WS-EMP-SALARY LESS THAN 80000
                   DISPLAY 'Test 3: Salary in mid range - PASS'
               END-IF
           END-IF.
           
      *> Multiple conditions with AND
           MOVE 1 TO WS-IS-VALID.
           MOVE 0 TO WS-ERROR-FLAG.
           IF WS-IS-VALID = 1 AND WS-ERROR-FLAG = 0
               DISPLAY 'Test 4: Valid with no errors - PASS'
           END-IF.
           
      *> Multiple conditions with OR
           MOVE 100 TO WS-COUNTER.
           IF WS-COUNTER = 100 OR WS-COUNTER = 200
               DISPLAY 'Test 5: Counter matches expected - PASS'
           END-IF.
           
      *> NOT condition
           MOVE 0 TO WS-ERROR-FLAG.
           IF WS-ERROR-FLAG NOT = 1
               DISPLAY 'Test 6: No error flag set - PASS'
           END-IF.
           
           DISPLAY ' '.
           
              
       STRING-OPERATIONS.
           DISPLAY '--- String Operations Test ---'.
           
      *> MOVE string operations
           MOVE 'Mugdha' TO WS-FIRST-NAME.
           MOVE 'Kadam' TO WS-LAST-NAME.
           DISPLAY 'First Name: ' WS-FIRST-NAME.
           DISPLAY 'Last Name: ' WS-LAST-NAME.
           
      *> String concatenation simulation
           MOVE WS-FIRST-NAME TO WS-FULL-NAME.
           DISPLAY 'Full Name: ' WS-FULL-NAME.
           
      *> Message formatting
           MOVE 'Processing employee record...' TO WS-MESSAGE.
           DISPLAY WS-MESSAGE.
           
           DISPLAY ' '.
           
       SALARY-VALIDATION.
           DISPLAY '--- Salary Validation Test ---'.
           
      *> Test minimum salary
           MOVE 25000 TO WS-EMP-SALARY.
           IF WS-EMP-SALARY LESS THAN WS-MIN-SALARY
               DISPLAY 'Salary $25000 is below minimum'
               MOVE WS-MIN-SALARY TO WS-EMP-SALARY
               DISPLAY 'Adjusted to minimum: $' WS-EMP-SALARY
           ELSE
               DISPLAY 'Salary is valid'
           END-IF.
           
      *> Test maximum salary
           MOVE 200000 TO WS-EMP-SALARY.
           IF WS-EMP-SALARY GREATER THAN WS-MAX-SALARY
               DISPLAY 'Salary $200000 exceeds maximum'
               MOVE WS-MAX-SALARY TO WS-EMP-SALARY
               DISPLAY 'Adjusted to maximum: $' WS-EMP-SALARY
           ELSE
               DISPLAY 'Salary is within range'
           END-IF.
           
      *> Test valid salary range
           MOVE 65000 TO WS-EMP-SALARY.
           IF WS-EMP-SALARY GREATER THAN WS-MIN-SALARY 
              AND WS-EMP-SALARY LESS THAN WS-MAX-SALARY
               DISPLAY 'Salary $65000 is in valid range - PASS'
           END-IF.
           
           DISPLAY ' '.
           
       FINAL-SUMMARY.
           DISPLAY '--- Final Summary ---'.
           
      *> Calculate final statistics
           MOVE 5 TO WS-COUNTER.
           MOVE 75000 TO WS-EMP-SALARY.
           COMPUTE WS-TOTAL = WS-EMP-SALARY * WS-COUNTER.
           DISPLAY 'Total employees processed: ' WS-COUNTER.
           DISPLAY 'Average salary: $' WS-EMP-SALARY.
           DISPLAY 'Total payroll: $' WS-TOTAL.
           
      *> Set completion flags
           MOVE 1 TO WS-IS-PROCESSED.
           MOVE 0 TO WS-ERROR-FLAG.
           
           IF WS-IS-PROCESSED = 1 AND WS-ERROR-FLAG = 0
               DISPLAY 'All tests completed successfully!'
           ELSE
               DISPLAY 'Some tests failed - review output'
           END-IF.
           
           DISPLAY ' '.

      *> Made with Bob