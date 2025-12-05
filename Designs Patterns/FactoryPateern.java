// The Factory Pattern is a creational design pattern that provides an
// interface for creating objects but allows subclasses or factories to
// decide which class to instantiate. It removes the need to use 'new'
// in client code and promotes loose coupling. When new types are added,
// only the factory requires modification, keeping the main code closed
// for modification but open for extension (OCP).

// Example: A ShapeFactory class creates Shape objects like Circle or
// Rectangle based on input. The client simply calls factory.getShape()
// without knowing the actual class being created.
import java.util.*;
interface Shape {
    public void computeArea();
}
class Circle implements Shape {
    @Override
    public void computeArea() {
        System.out.println("Compute Circle Area");
    }
}
class Rectangle implements Shape {
    @Override
    public void computeArea() {
        System.out.println("Compute Rectangle Area");
    }
}
class ShapeInstanceFactory {
    public Shape getShapeInstance(String val){
        if(val.equals("Circle")){
            return new Circle();
        }
        if(val.equals("Square") || val.equals("Rectangle")){
            return new Rectangle();
        }
        return null;
    }
}
public class FactoryPateern {
    public static void main(String[] args) {
        ShapeInstanceFactory factoryobj = new ShapeInstanceFactory();
        Shape circleObj = factoryobj.getShapeInstance("Circle");
        Shape squareObj = factoryobj.getShapeInstance("Square");
        circleObj.computeArea();
        squareObj.computeArea();
    }
}
