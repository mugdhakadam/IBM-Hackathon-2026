       IDENTIFICATION DIVISION.
       PROGRAM-ID. HELLO-WORLD.
       AUTHOR. COBOL-TO-JAVA-CONVERTER.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01 WS-NAME PIC X(20) VALUE 'John Doe'.
       01 WS-AGE  PIC 99 VALUE 25.
       01 WS-SALARY PIC 9(5)V99 VALUE 50000.00.
       01 WS-MESSAGE PIC X(50) VALUE 'Welcome to COBOL!'.
       
       PROCEDURE DIVISION.
       MAIN-PARAGRAPH.
           DISPLAY 'Program: HELLO-WORLD'.
           DISPLAY 'Name: ' WS-NAME.
           DISPLAY 'Age: ' WS-AGE.
           DISPLAY WS-MESSAGE.
           PERFORM CALCULATE-BONUS.
           STOP RUN.
           
       CALCULATE-BONUS.
           DISPLAY 'Calculating bonus...'.
           DISPLAY 'Salary: ' WS-SALARY.

      *> Made with Bob
