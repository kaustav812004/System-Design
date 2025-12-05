// Builder Pattern is a creational design pattern used to construct
// complex objects step-by-step. It helps avoid telescoping constructors
// when a class has many optional fields. The builder class provides
// methods to set individual fields and a build() method to create the
// final immutable object. This improves readability, flexibility, and
// maintainability of object creation.

// Class → Student
// Builder → StudentBuilder

import java.util.*;
abstract class StudentBuilder {
    int rollno;
    int age;
    String name;
    String fathername;
    String mothername;
    List<String> subjects;
    
    public StudentBuilder setRollno(int rollno){
        this.rollno = rollno;
        return this;
    }
    public StudentBuilder setAge(int age){
        this.age = age;
        return this;
    }
    public StudentBuilder setName(String name){
        this.name = name;
        return this;
    }
    public StudentBuilder setFname(String fathername){
        this.fathername = fathername;
        return this;
    }
    public StudentBuilder setMname(String mothername){
        this.mothername = mothername;
        return this;
    }
    abstract public StudentBuilder setSubjects();
    public Student build(){
        return new Student(this);
    }
}
class MBAStudentBuilder extends StudentBuilder{
    @Override
    public StudentBuilder setSubjects(){
        List<String> subs = new ArrayList<>();
        subs.add("Micro Economics");
        subs.add("Business Studies");
        subs.add("Operations Management");
        this.subjects = subs;
        return this;
    }
}
class EngineeringStudentBuilder extends StudentBuilder{
    @Override
    public StudentBuilder setSubjects(){
        List<String> subs = new ArrayList<>();
        subs.add("DAA");
        subs.add("OS");
        subs.add("COA");
        this.subjects = subs;
        return this;
    }
}
class Student {
    int rollno;
    int age;
    String name;
    String fathername;
    String mothername;
    List<String> subjects;
    
    Student(StudentBuilder builder) {
        this.rollno = builder.rollno;
        this.age = builder.age;
        this.name = builder.name;
        this.fathername = builder.fathername;
        this.mothername = builder.mothername;
        this.subjects = builder.subjects;
    }
    @Override
    public String toString(){
        return "name: "+this.name+"\n"+ 
        "rollno: "+this.rollno+"\n"+
        "age: "+this.age+"\n"+
        "father name: "+this.fathername+"\n"+
        "mother name: "+this.mothername+"\n"+
        "Subjects: "+subjects;
    }
}
//Director class is just for example not in use in actual coding of lld
// class Director {
//     StudentBuilder studentbuilder;
//     Director(StudentBuilder studentbuilder){ this.studentbuilder = studentbuilder;}
//     public Student createStudent(){
//         if(studentbuilder instanceof EngineeringStudentBuilder){
//             return createEngineeringStudent();
//         }
//         if(studentbuilder instanceof MBAStudentBuilder){
//             return createMBAStudent();
//         }
//         return null;
//     }
//     private Student createEngineeringStudent(){
//         return studentbuilder.setRollno(1).setAge(22).setName("kaustav").setSubjects().build();
//     }
//     private Student createMBAStudent(){
//         return studentbuilder.setRollno(2).setAge(25).setName("Rick").setSubjects().build();
//     }
// }
public class BuilderPattern {
    public static void main(String[] args) {
        // Director directorobj1 = new Director(new EngineeringStudentBuilder());
        // Director directorobj2 = new Director(new MBAStudentBuilder());

        // Student engineerStudent = directorobj1.createStudent();
        // Student mbastudent = directorobj2.createStudent();

        Student engineerStudent = new EngineeringStudentBuilder()
                                .setAge(22)
                                .setRollno(38)
                                .setName("Kaustav")
                                .setFname("Saurav")
                                .setSubjects()
                                .setMname("Sonali")
                                .build();

        Student mbastudent = new MBAStudentBuilder()
                                .setAge(16)
                                .setRollno(21)
                                .setName("Rishit")
                                .setFname("Saurav")
                                .setSubjects()
                                .setMname("Sonali")
                                .build();
                                
        System.out.println(engineerStudent.toString());
        System.out.println("-------------------------------------------");
        System.out.println(mbastudent.toString());
    }
}

