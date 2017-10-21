# Simple-SQL-Server-for-Course-Enrollment
A simple sql server written in java. the program takes a sql query as input and retrieves the result. 

The Program Takes 4 text files as input.  (Delimiter is the space)
 - Course file has 2 columns ID, courseName.
 - Student file has 3 columns Name, FamilyName, ID.
 - StudentCourse file has 3 columns, SID, CID, TakenFlag.
 - Input file that contains the queries. 
 
 
 #Sample Queries:
  
- select from student where st_name=hooman
- select from student where st_Family=jafary

- select from student where st_code=9000

- select from course where course_code=0004

- select from course where course_name=zakhire

- select from student_course where course_code=0001

- select from student_course where st_code=9231615

- inssert into student st_name=ali_reza st_family=rezayi st_code=9123

- inssert into course course_code=0010 course_name=reyazi

- inssert into student_course st_code=9213151 course_code=0001

- update course course_name=reyazi2 where course_code=0010

- update course course_code=0110 where course_name=reyazi2

- update student st_name=ali_reza2 where st_name=ali_reza

- update student st_code=9999 where st_name=ali_reza2

- update student st_family=yarshenas where st_code=9031421

- update student st_name=HM2D where st_code=9231615

- update student st_code=121212 where st_code=9512314

- delete from student_course where st_code=9213151 and course_code=0001

- select from student,course,student_course where st_code=9000

- select from student,course,student_course where course_code=0001

 
