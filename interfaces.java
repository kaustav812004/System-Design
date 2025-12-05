import java.util.*;

interface test{
    final int a = 20;
    void display();
}
class testClass implements test{
    public void display(){
        System.out.println("Hello World");
    }
}
public class interfaces {
    public static void main(String[] args){
        testClass t = new testClass();
        t.display();
        System.out.println(t.a);
    }
}
