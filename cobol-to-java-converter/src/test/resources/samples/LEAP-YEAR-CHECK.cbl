       IDENTIFICATION DIVISION.
       PROGRAM-ID. LEAP-YEAR-CHECK.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       77 YEAR PIC 9(4).
       77 REMAINDER-4 PIC 9(2).
       77 REMAINDER-100 PIC 9(2).
       77 REMAINDER-400 PIC 9(2).
       
       PROCEDURE DIVISION.
           ACCEPT YEAR.
           DIVIDE YEAR BY 4 GIVING YEAR REMAINDER REMAINDER-4.
           DIVIDE YEAR BY 100 GIVING YEAR REMAINDER REMAINDER-100.
           DIVIDE YEAR BY 400 GIVING YEAR REMAINDER REMAINDER-400.
           
           IF (REMAINDER-4 = 0 AND REMAINDER-100 NOT = 0) 
              OR (REMAINDER-400 = 0)
               DISPLAY 'Leap Year'
           ELSE
               DISPLAY 'Not a Leap Year'
           END-IF.
           
           STOP RUN.

      *> Made with Bob
