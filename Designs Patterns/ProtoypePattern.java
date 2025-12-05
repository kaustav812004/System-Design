// Prototype Pattern is a creational design pattern that allows
// creating new objects by copying (cloning) an existing object.
// Instead of instantiating classes directly, we use a prototype
// interface with a clone() method.

// It is useful when object creation is expensive or complex.
// Cloning provides better performance and avoids re-running
// heavy constructor logic.

// Example: A Student object can implement a Prototype interface
// and define a clone() method that returns a copy of itself.
// This allows making multiple identical Student objects easily
// without using the constructor again.
import java.util.*;
interface prototype{
    prototype clone();
}
class Student implements prototype{
    int age;
    private int rollno;
    String name;

    Student(){}
    Student(int age, int rollno, String name){
        this.age = age;
        this.rollno = rollno;
        this.name = name;
    }
    @Override
    public prototype clone(){
        return new Student(age, rollno, name);
    }
    @Override
    public String toString(){
        return "Student{name: "+name+"; age: "+age+"; rollno: "+rollno+"}";
    }
}
public class ProtoypePattern {
    public static void main(String[] args) {
       Student s1 = new Student(22, 38, "Kaustav");
       Student cloneobj = (Student) s1.clone();
       System.out.println(cloneobj);
//        clone() returns a Prototype reference, but the actual object created is a Student.
// Since the return type doesn’t match the variable type, you must explicitly downcast from Prototype to Student.
// This is safe because clone() always constructs a Student object.
       System.out.println(cloneobj.name);
    }
}
