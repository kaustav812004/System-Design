import java.util.*;

interface Add {
    int c = 12; 
    public int add(int a, int b);
}
interface Sub {
    int sub(int a, int b);
}
class Calculator implements Add, Sub {
    @Override
    public int add(int a, int b){ return a+b; }
    @Override
    public int sub(int a, int b){ return a-b; }
}
public class MultipInherit {
    public static void main(String[] args) {
        Calculator x = new Calculator();
        System.out.println("Addition : " +x.add(2, 6));
        System.out.println("Subtraction : " +x.sub(7, 6));
        System.out.println(Calculator.c);
    }
}
