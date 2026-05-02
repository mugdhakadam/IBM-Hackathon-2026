       IDENTIFICATION DIVISION.
       PROGRAM-ID. ADVANCED-FEATURES.
       AUTHOR. COBOL-TO-JAVA-CONVERTER.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01 WS-NUM1 PIC 99 VALUE 20.
       01 WS-NUM2 PIC 99 VALUE 5.
       01 WS-RESULT PIC 999 VALUE 0.
       01 WS-SALARY PIC 9(5)V99 VALUE 50000.00.
       01 WS-BONUS PIC 9(5)V99 VALUE 0.
       01 WS-TOTAL PIC 9(6)V99 VALUE 0.
       01 WS-NAME PIC X(30) VALUE 'John Smith'.
       01 WS-COUNTER PIC 99 VALUE 0.
       
       PROCEDURE DIVISION.
       MAIN-LOGIC.
           DISPLAY 'Advanced Features Demo'.
           DISPLAY '====================='.
           PERFORM ARITHMETIC-OPERATIONS.
           PERFORM CONDITIONAL-LOGIC.
           PERFORM COMPUTE-EXAMPLE.
           STOP RUN.
           
       ARITHMETIC-OPERATIONS.
           DISPLAY 'Arithmetic Operations:'.
           
           ADD WS-NUM1 TO WS-NUM2.
           DISPLAY 'After ADD: ' WS-NUM2.
           
           SUBTRACT 3 FROM WS-NUM1.
           DISPLAY 'After SUBTRACT: ' WS-NUM1.
           
           MULTIPLY WS-NUM1 BY 2 GIVING WS-RESULT.
           DISPLAY 'After MULTIPLY: ' WS-RESULT.
           
           DIVIDE WS-RESULT BY 4 GIVING WS-NUM2.
           DISPLAY 'After DIVIDE: ' WS-NUM2.
           
       CONDITIONAL-LOGIC.
           DISPLAY 'Conditional Logic:'.
           
           MOVE 25 TO WS-COUNTER.
           
           IF WS-COUNTER GREATER THAN 20
               DISPLAY 'Counter is greater than 20'
           END-IF.
           
       COMPUTE-EXAMPLE.
           DISPLAY 'Compute Example:'.
           
           COMPUTE WS-BONUS = WS-SALARY * 0.10.
           DISPLAY 'Bonus: ' WS-BONUS.
           
           COMPUTE WS-TOTAL = WS-SALARY + WS-BONUS.
           DISPLAY 'Total Compensation: ' WS-TOTAL.

      *> Made with Bob