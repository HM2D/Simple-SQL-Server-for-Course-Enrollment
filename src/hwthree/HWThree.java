/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hwthree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author HM2D
 */
public class HWThree {

    
      public static  Student[] students = new Student[1000];
       public static Course[]  courses = new Course[1000];
        public  static StudentCourse[] studentCourse = new StudentCourse[1000];
       public static int studentCounter = 0;
       public static int courseCounter = 0;
       public static int studentCourseCounter = 0;
       public static String Output = "\n";
               
		 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        LoadDataBase();
                String firstline;
                int contactCounter = 0;
		 String fileName = "input.txt";
		 String line = null;
		 String[] splited;
                 String[] splited2;
                
                /////FileReader
                	 
		try {

			 FileReader fileReader = new FileReader(fileName);
			 BufferedReader bufferedReader = new BufferedReader(fileReader);
			 firstline = bufferedReader.readLine();
			 String[] splitStrings = firstline.split(" ");
                         /////////Read From File
			 while((line = bufferedReader.readLine()) != null) {
                             splited = line.split(" ");
                             if(splited[0].contains("select")){
                             Select(line);
                             }
                             if(splited[0].contains("insert")){
                             Insert(line);
                             }
                             if(splited[0].contains("update")){
                             Update(line);
                             }
                         
                             if(splited[0].contains("delete")){
                             Delete(line);
                             }
                                     
			 } 

			 bufferedReader.close(); 
			 }
			 catch(FileNotFoundException ex) {
			 System.out.println(
			 "Unable to open file '" + 
			 fileName + "'"); 
			 }
			 catch(IOException ex) {
			 System.out.println(
			 "Error reading file '" 
			 + fileName + "'"); 
			 // Or we could just do this: 
			 // ex.printStackTrace();
			 }

    
    //
        System.out.println("//////////////Students/////////////////");
        for (int i = 0; i < studentCounter; i++) {
            System.out.println(students[i].Name + " " + students[i].Family + " " + students[i].ID);
            
        }
        System.out.println("//////////////Courses/////////////////");
        
        for (int i = 0; i < courseCounter; i++) {
            System.out.println(courses[i].Name + " " + courses[i].ID );
            
        }
        System.out.println("//////////////student_Course/////////////////");
        for (int i = 0; i < studentCourseCounter; i++) {
            System.out.println(studentCourse[i].Course + " " + studentCourse[i].Student + " " + studentCourse[i].taken );
            
        }
        
        SaveToFile();
    }
  public static void SaveToFile() throws IOException{
  ///write into courses
  String courseString = "\n";
          for (int i = 0; i < courseCounter; i++) {
              courseString += courses[i].ID + " " + courses[i].Name +  "\n"; 
          }
          
  String studentString = "\n";
          for (int i = 0; i < studentCounter; i++) {
              studentString += students[i].Name + " " + students[i].Family + " "+ students[i].ID +  "\n"; 
          }
          
  String studentCourseString = "\n";
          for (int i = 0; i < studentCourseCounter; i++) {
              studentCourseString += studentCourse[i].Student + " " + studentCourse[i].Course +" " + studentCourse[i].taken +  "\n"; 
          }
       WriteToFile(courseString,"course.txt");   
       WriteToFile(studentCourseString,"studentC.txt");   
       WriteToFile(studentString,"student.txt");   
       WriteToFile(Output,"output.txt");   
          
          return;
      
  }
  public static void WriteToFile(String content,String fileName) throws IOException{
  
  
  
          File file = new File(fileName);
  
   FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
         bw.write(content);
          //System.out.println(content);
          bw.close();
          
  
  }
  public static void Select(String input){
    String[] splitInputBySpace = input.split(" ");
     if(splitInputBySpace[2].contains(",")){
        // System.out.println("Bonous");
     if(splitInputBySpace[4].contains("st_code")){
        int[] courseCodes = new int[1000];
        int courseCodeCounter = 0;   
        String sCode = splitInputBySpace[4].split("=")[1];
             System.out.println(sCode);
       
           for(int i =0;i< studentCourseCounter;i++){
               
               if((studentCourse[i].taken==1) && (studentCourse[i].Student.equals(sCode)))
             courseCodes[courseCodeCounter++] = Integer.parseInt(studentCourse[i].Course);
               //System.out.println(Integer.parseInt(students[i].ID));
               //System.out.println(studentCodes[i]);
           }
           mySort(courseCodes,courseCodeCounter);
           System.out.println(courseCodeCounter);
               for (int i = 0; i < courseCodeCounter; i++) {
                   //System.out.println(studentCodes[i]);
                   for (int j = 0; j < courseCounter; j++) {
                       if(courseCodes[i] == Integer.parseInt(courses[j].ID)){
                       
                           System.out.println(courses[j].ID + "," + courses[j].Name);
                           Output += courses[j].ID + "," + courses[j].Name + "\n";
                       
                       
                       }
                   }
               }
            
           
           
     
     
     }else 
     if(splitInputBySpace[4].contains("course_code")){
     
     
        int[] sCodes = new int[1000];
        int sCodeCounter = 0;
           String sCode = splitInputBySpace[4].split("=")[1];
           for(int i =0;i< studentCourseCounter;i++){
               if((studentCourse[i].taken==1) && (studentCourse[i].Course.equals(sCode)))
             sCodes[sCodeCounter++] = Integer.parseInt(studentCourse[i].Student);
               //System.out.println(Integer.parseInt(students[i].ID));
               //System.out.println(studentCodes[i]);
           }
           mySort(sCodes,sCodeCounter);
               for (int j = 0; j < sCodeCounter; j++) {
                   //System.out.println(studentCodes[i]);
                        for(int i=0;i<studentCounter;i++ ){
                       if(sCodes[j] == Integer.parseInt(students[i].ID)){
                       
                           System.out.println(students[i].ID+","+students[i].Name+","+students[i].Family);
                           Output += students[i].ID+","+students[i].Name+","+students[i].Family+"\n";
                       
                       
                       }}
                   
               }
     
     }
     
     
     
     }else    
    if(splitInputBySpace[2].equals("course")){
        int[] cCodes = new int[1000];
           
           if(splitInputBySpace[4].contains("course_name")){
           String cName = splitInputBySpace[4].split("=")[1];
           for(int i =0;i< courseCounter;i++){
             cCodes[i] = Integer.parseInt(courses[i].ID);
               //System.out.println(Integer.parseInt(students[i].ID));
               //System.out.println(studentCodes[i]);
           }
           mySort(cCodes,courseCounter);
               for (int i = 0; i < courseCounter; i++) {
                   //System.out.println(studentCodes[i]);
                   for (int j = 0; j < courseCounter; j++) {
                       if(cCodes[i] == Integer.parseInt(courses[j].ID) && (courses[j].Name.equals(cName))){
                       
                           System.out.println(courses[j].ID +"," + courses[j].Name);
                          Output += courses[j].ID +"," + courses[j].Name + "\n";
                       }
                       
                   }
                   
               }
           
           }else if(splitInputBySpace[4].contains("course_code")){
           String cCode = splitInputBySpace[4].split("=")[1];
           for(int i =0;i< courseCounter;i++){
             cCodes[i] = Integer.parseInt(courses[i].ID);
               //System.out.println(Integer.parseInt(students[i].ID));
               //System.out.println(studentCodes[i]);
           }
           mySort(cCodes,courseCounter);
               for (int i = 0; i < courseCounter; i++) {
                   //System.out.println(studentCodes[i]);
                   for (int j = 0; j < courseCounter; j++) {
                       if(cCodes[i] == Integer.parseInt(courses[j].ID) && (courses[j].ID.equals(cCode))){
                       
                           System.out.println(courses[j].ID +"," + courses[j].Name);
                          Output += courses[j].ID +"," + courses[j].Name + "\n";
                       }
                       
                   }
                   
               }
               
           
           
           
           }   
       }else
       if(splitInputBySpace[2].equals("student")){
           int[] studentCodes = new int[1000];
           
           if(splitInputBySpace[4].contains("st_name")){
           String stName = splitInputBySpace[4].split("=")[1];
           for(int i =0;i< studentCounter;i++){
             studentCodes[i] = Integer.parseInt(students[i].ID);
               //System.out.println(Integer.parseInt(students[i].ID));
               //System.out.println(studentCodes[i]);
           }
           mySort(studentCodes,studentCounter);
               for (int i = 0; i < studentCounter; i++) {
                   //System.out.println(studentCodes[i]);
                   for (int j = 0; j < studentCounter; j++) {
                       if(studentCodes[i] == Integer.parseInt(students[j].ID) && (students[j].Name.equals(stName))){
                       
                           System.out.println(students[j].ID +"," + students[j].Name + ","+ students[j].Family);
                           Output += students[j].ID +"," + students[j].Name + ","+ students[j].Family + "\n";
                       }
                       
                   }
                   
               }
           
           }
           else if(splitInputBySpace[4].contains("st_Family")){
            String stFamily = splitInputBySpace[4].split("=")[1];
           for(int i =0;i< studentCounter;i++){
             studentCodes[i] = Integer.parseInt(students[i].ID);
               //System.out.println(Integer.parseInt(students[i].ID));
               //System.out.println(studentCodes[i]);
           }
           mySort(studentCodes,studentCounter);
               for (int i = 0; i < studentCounter; i++) {
                   //System.out.println(studentCodes[i]);
                   for (int j = 0; j < studentCounter; j++) {
                       if(studentCodes[i] == Integer.parseInt(students[j].ID) && (students[j].Family.equals(stFamily))){
                           
                           System.out.println(students[j].ID +"," + students[j].Name + ","+ students[j].Family);
                           Output += students[j].ID +"," + students[j].Name + ","+ students[j].Family + "\n";
                       }
                       
                   }
                   
               }
           
           }
           else if(splitInputBySpace[4].contains("st_code")){
            String stCode = splitInputBySpace[4].split("=")[1];
           for(int i =0;i< studentCounter;i++){
             studentCodes[i] = Integer.parseInt(students[i].ID);
               //System.out.println(Integer.parseInt(students[i].ID));
               //System.out.println(studentCodes[i]);
           }
           mySort(studentCodes,studentCounter);
               for (int i = 0; i < studentCounter; i++) {
                   //System.out.println(studentCodes[i]);
                   for (int j = 0; j < studentCounter; j++) {
                       if(studentCodes[i] == Integer.parseInt(students[j].ID) && (students[j].ID.equals(stCode))){
                       
                           System.out.println(students[j].ID +"," + students[j].Name + ","+ students[j].Family);
                       
                           Output += students[j].ID +"," + students[j].Name + ","+ students[j].Family + "\n";
                       }
                       
                   }
                   
               }
           }}else if(splitInputBySpace[2].equals("student_course")){
               int[] courseCodes = new int[1000];
           if(splitInputBySpace[4].contains("course_code")){
            String cCode = splitInputBySpace[4].split("=")[1];
           for(int i =0;i< studentCourseCounter;i++){
             courseCodes[i] = Integer.parseInt(studentCourse[i].Course);
               //System.out.println(Integer.parseInt(students[i].ID));
               //System.out.println(studentCodes[i]);
           }
           mySort(courseCodes,studentCourseCounter);
               for (int i = 0; i < studentCourseCounter; i++) {
                   //System.out.println(studentCodes[i]);
                   for (int j = 0; j < studentCourseCounter; j++) {
                       if(courseCodes[i] == Integer.parseInt(studentCourse[j].Course) && (studentCourse[j].Course.equals(cCode)) && (studentCourse[j].taken == 1)){
                       
                           System.out.println(studentCourse[j].Student);
                           Output += studentCourse[j].Student + "\n";
                       }
                       
                   }
                   
               }
           
           
           
           }else if(splitInputBySpace[4].contains("st_code")){
              int[] courseCodes2 = new int[1000];
           String sCode = splitInputBySpace[4].split("=")[1];
           for(int i =0;i< studentCourseCounter;i++){
             courseCodes2[i] = Integer.parseInt(studentCourse[i].Course);
               //System.out.println(Integer.parseInt(students[i].ID));
               //System.out.println(studentCodes[i]);
           }
           mySort(courseCodes2,studentCourseCounter);
               for (int i = 0; i < studentCourseCounter; i++) {
                   //System.out.println(studentCodes[i]);
                   for (int j = 0; j < studentCourseCounter; j++) {
                       if(courseCodes2[i] == Integer.parseInt(studentCourse[j].Course) && (studentCourse[j].Student.equals(sCode)) && (studentCourse[j].taken == 1)){
                       
                           System.out.println(studentCourse[j].Course);
                           Output += studentCourse[j].Course + "\n";
                       }
                       
                   }
                   
               }
            
           
           }
               
           else{
               System.out.println("Input error");
               return;
           }
       }else 
       if(splitInputBySpace[2].contains("student_course")){
           if(splitInputBySpace[3].contains("st_code") && splitInputBySpace[4].contains("course_code")){
           studentCourse[studentCourseCounter++] = new StudentCourse(splitInputBySpace[4].split(("="))[1],splitInputBySpace[3].split(("="))[1],1);
           System.out.println("Inserted into courses CourseName: "  + studentCourse[studentCourseCounter-1].Course + " sID: " + studentCourse[studentCourseCounter-1].Student);
           }else {
               System.out.println("input student_course  error!"); return;
            }
       
       }else{ System.out.println("input fully error!");
       return;
       }
    }
  public static void Insert(String input){
       String[] splitInputBySpace = input.split(" ");
      // System.out.println(splitInputBySpace[2]);
       if(splitInputBySpace[2].equals("course")){
           if(splitInputBySpace[3] != null){
           if(splitInputBySpace[3].contains("course_code") && splitInputBySpace[4].contains("course_name")){
           courses[courseCounter++] = new Course(splitInputBySpace[4].split(("="))[1],splitInputBySpace[3].split(("="))[1]);
           System.out.println("Inserted into courses CourseName: "  + courses[courseCounter-1].Name + " CourseID: " + courses[courseCounter-1].ID);
           }else {
               System.out.println("input error!"); return;
            }}else{
               System.out.println("input error!"); return;
           
           }
           
       }else
       if(splitInputBySpace[2].equals("student")){
           
           if(splitInputBySpace[3].contains("st_name") && splitInputBySpace[4].contains("st_family") && splitInputBySpace[5].contains("st_code")){
           
           students[studentCounter++] = new Student(splitInputBySpace[5].split(("="))[1],splitInputBySpace[3].split(("="))[1],splitInputBySpace[4].split(("="))[1]);
           System.out.println("Inserted into students sName: "  + students[studentCounter-1].Name + " sFamily: " + students[studentCounter-1].Family + " sID" + students[studentCounter-1].ID);
           }else {
               System.out.println("input student error!"); return;
            }
       }else 
       if(splitInputBySpace[2].contains("student_course")){
           if(splitInputBySpace[3].contains("st_code") && splitInputBySpace[4].contains("course_code")){
           studentCourse[studentCourseCounter++] = new StudentCourse(splitInputBySpace[4].split(("="))[1],splitInputBySpace[3].split(("="))[1],1);
           System.out.println("Inserted into courses CourseName: "  + studentCourse[studentCourseCounter-1].Course + " sID: " + studentCourse[studentCourseCounter-1].Student);
           }else {
               System.out.println("input student_course  error!"); return;
            }
       
       }else{ System.out.println("input fully error!");
       return;
       }
    //  System.out.println("Insert");
  
  }
  public static void Update(String input){
      //System.out.println("Update");
  
       String[] splitInputBySpace = input.split(" ");
      // System.out.println(splitInputBySpace[2]);
       if(splitInputBySpace[1].equals("course")){
           if(splitInputBySpace[2].split("=")[0].contains("course_name")){
           String cName = splitInputBySpace[2].split(("="))[1];
           String cCode  =splitInputBySpace[4].split(("="))[1];
               for (int i = 0; i < courseCounter; i++) {
                   if(courses[i].ID.equals(cCode)){
                   
                   courses[i].Name = cName;
                       System.out.println("Updated " + cCode + " " +cName);
                       
       }}
           }else if(splitInputBySpace[2].split("=")[0].contains("course_code")){
                 String cName = splitInputBySpace[4].split(("="))[1];
                 String cCode  =splitInputBySpace[2].split(("="))[1];
               for (int i = 0; i < courseCounter; i++) {
                   if(courses[i].Name.equals(cName)){
                   
                   courses[i].ID = cCode;
                       System.out.println("Updated " + cCode + " " + cName);
                       
       }}
           
           }else {
               System.out.println("input error"); return;
           }
       }else
       if(splitInputBySpace[1].equals("student")){
           if(splitInputBySpace[2].split("=")[0].contains("st_name")){
           String stName = splitInputBySpace[2].split(("="))[1];
           if(splitInputBySpace[4].split("=")[0].contains("st_code")){
           String stCode  =splitInputBySpace[4].split(("="))[1];
               for (int i = 0; i < studentCounter; i++) {
                   if(students[i].ID.equals(stCode)){
                   
                   students[i].Name = stName;
                       System.out.println("Updated " + stCode + " " + stName);
                            }
               }}else if(splitInputBySpace[4].split("=")[0].contains("st_name")){
                 String stName2  =splitInputBySpace[4].split(("="))[1];
               for (int i = 0; i < studentCounter; i++) {
                   if(students[i].Name.equals(stName2)){
                   
                   students[i].Name = stName;
                       System.out.println("Updated " + stName2 + " " + stName);
                            }
               }
           
           
           }else if(splitInputBySpace[4].split("=")[0].contains("st_family")){
              String stFamily  =splitInputBySpace[4].split(("="))[1];
               for (int i = 0; i < studentCounter; i++) {
                   if(students[i].Family.equals(stFamily)){
                   
                   students[i].Name = stName;
                       System.out.println("Updated " + stFamily + " " + stName);
                            }
               }
           
           
           
           }}
           else if(splitInputBySpace[2].split("=")[0].contains("st_code")){
             String stCode = splitInputBySpace[2].split(("="))[1];
           if(splitInputBySpace[4].split("=")[0].contains("st_code")){
           String stCode2  =splitInputBySpace[4].split(("="))[1];
               for (int i = 0; i < studentCounter; i++) {
                   if(students[i].ID.equals(stCode2)){
                   
                   students[i].Name = stCode;
                       System.out.println("Updated " + stCode2 + " " + stCode);
                            }
               }}else if(splitInputBySpace[4].split("=")[0].contains("st_name")){
                 String stName2  =splitInputBySpace[4].split(("="))[1];
               for (int i = 0; i < studentCounter; i++) {
                   if(students[i].Name.equals(stName2)){
                   
                   students[i].ID = stCode;
                       System.out.println("Updated " + stName2 + " " + stCode);
                            }
               }
           
           
           }else if(splitInputBySpace[4].split("=")[0].contains("st_family")){
              String stFamily  =splitInputBySpace[4].split(("="))[1];
               for (int i = 0; i < studentCounter; i++) {
                   if(students[i].Family.equals(stFamily)){
                   
                   students[i].Name = stCode;
                       System.out.println("Updated " + stFamily + " " + stCode);
                            }
               }
           
           
           
           }  
           }else if(splitInputBySpace[2].split("=")[0].contains("st_family")){
             String stFamily = splitInputBySpace[2].split(("="))[1];
           if(splitInputBySpace[4].split("=")[0].contains("st_code")){
           String stCode2  =splitInputBySpace[4].split(("="))[1];
               for (int i = 0; i < studentCounter; i++) {
                   if(students[i].ID.equals(stCode2)){
                   
                   students[i].Name = stFamily;
                       System.out.println("Updated " + stCode2 + " " + stFamily);
                            }
               }}else if(splitInputBySpace[4].split("=")[0].contains("st_name")){
                 String stName2  =splitInputBySpace[4].split(("="))[1];
               for (int i = 0; i < studentCounter; i++) {
                   if(students[i].Name.equals(stName2)){
                   
                   students[i].Name = stFamily;
                       System.out.println("Updated " + stName2 + " " + stFamily);
                            }
               }
           
           
           }else if(splitInputBySpace[4].split("=")[0].contains("st_family")){
              String stFamily2  =splitInputBySpace[4].split(("="))[1];
               for (int i = 0; i < studentCounter; i++) {
                   if(students[i].Family.equals(stFamily2)){
                   
                   students[i].Name = stFamily;
                       System.out.println("Updated " + stFamily2 + " " + stFamily);
                            }
               }
           
           
           
           }  
           
           }
       }else{ System.out.println("input fully error!");
       return;
       }
      
      
      
  }
   
  public static void Delete(String input){
      //System.out.println("Delete");
   String[] splitInputBySpace = input.split(" ");
      // System.out.println(splitInputBySpace[2]);
       
       if(splitInputBySpace[2].contains("student_course")){
           if(splitInputBySpace[4].contains("st_code") && splitInputBySpace[6].contains("course_code")){
           String stCode = splitInputBySpace[4].split(("="))[1];
           String cCode  =splitInputBySpace[6].split(("="))[1];
               for (int i = 0; i < studentCourseCounter; i++) {
                   if(studentCourse[i].Course.equals(cCode) && studentCourse[i].Student.equals(stCode)){
                   
                   studentCourse[i].taken = 0;
                       System.out.println("Deleted " + cCode + " " +stCode);
                       
                   }
               }
           }else {
               System.out.println("input student_course delete error!"); return;
            }
       
       }else{ System.out.println("input fully error!");
       return;
       }
    //  System.out.println("Insert");
  
  } 
  
  
 public static void LoadDataBase(){
 
        LoadStudents();
        LoadCourses();
        LoadStudentCourses();
 
 }
 
 public static void LoadStudentCourses(){
      
     
            String firstline;
                String fileName = "studentC.txt";
		 String line = null;
		 String[] splited;
                 String[] splited2;
                
                /////FileReader
                	 
		try {

			 FileReader fileReader = new FileReader(fileName);
			 BufferedReader bufferedReader = new BufferedReader(fileReader);
			 firstline = bufferedReader.readLine();
			 String[] splitStrings = firstline.split(" ");
                         /////////Read From File
			 while((line = bufferedReader.readLine()) != null) {
                             splited = line.split(" ");
                             studentCourse[studentCourseCounter++] = new StudentCourse(splited[1],splited[0],Integer.parseInt(splited[2]));
                            // System.out.println(studentCourse[studentCourseCounter-1].Student);
			 } 

			 bufferedReader.close(); 
                         fileReader.close();
                         
			 }
			 catch(FileNotFoundException ex) {
			 System.out.println(
			 "Unable to open file '" + 
			 fileName + "'"); 
			 }
			 catch(IOException ex) {
			 System.out.println(
			 "Error reading file '" 
			 + fileName + "'"); 
			 // Or we could just do this: 
			 // ex.printStackTrace();
			 }

    
 
 
 
 }
 public static void LoadCourses(){
 
     
            String firstline;
                String fileName = "course.txt";
		 String line = null;
		 String[] splited;
                 String[] splited2;
                
                /////FileReader
                	 
		try {

			 FileReader fileReader = new FileReader(fileName);
			 BufferedReader bufferedReader = new BufferedReader(fileReader);
			 firstline = bufferedReader.readLine();
			 String[] splitStrings = firstline.split(" ");
                         /////////Read From File
			 while((line = bufferedReader.readLine()) != null) {
                             splited = line.split(" ");
                             courses[courseCounter++] = new Course(splited[1],splited[0]);
                             
			 } 

			 bufferedReader.close(); 
                         fileReader.close();
                         
			 }
			 catch(FileNotFoundException ex) {
			 System.out.println(
			 "Unable to open file '" + 
			 fileName + "'"); 
			 }
			 catch(IOException ex) {
			 System.out.println(
			 "Error reading file '" 
			 + fileName + "'"); 
			 // Or we could just do this: 
			 // ex.printStackTrace();
			 }

    
 
     
 
 
 }
 public static void LoadStudents(){
 
 
 
 
            String firstline;
                String fileName = "student.txt";
		 String line = null;
		 String[] splited;
                 String[] splited2;
                
                /////FileReader
                	 
		try {

			 FileReader fileReader = new FileReader(fileName);
			 BufferedReader bufferedReader = new BufferedReader(fileReader);
			 firstline = bufferedReader.readLine();
			 String[] splitStrings = firstline.split(" ");
                         /////////Read From File
			 while((line = bufferedReader.readLine()) != null) {
                             splited = line.split(" ");
                             students[studentCounter++] = new Student(splited[2],splited[0],splited[1]);
                             
			 } 

			 bufferedReader.close(); 
                         fileReader.close();
                         
			 }
			 catch(FileNotFoundException ex) {
			 System.out.println(
			 "Unable to open file '" + 
			 fileName + "'"); 
			 }
			 catch(IOException ex) {
			 System.out.println(
			 "Error reading file '" 
			 + fileName + "'"); 
			 // Or we could just do this: 
			 // ex.printStackTrace();
			 }

    
 
 }
 public static void mySort(int[] temp,int count){
     int tempSwap=0;
     for (int i = 0; i < count; i++) {
         for (int j = i; j < count; j++) {
             if(temp[i] > temp[j]){
              tempSwap = temp[i];
              temp[i] = temp[j];
              temp[j] = tempSwap;
             }
         }
     }
 
 
 }
}
