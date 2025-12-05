class ParentFactory {
    public AbstractFactory createInstance(String val){
        if(val.equals("Cheap") || val.equals("Affordable")){
            return new Economy();
        }
        else if(val.equals("Luxury") || val.equals("Expensive")){
            return new Luxury();
        }
        return null;
    }
}
interface AbstractFactory{
    public Car getinstance(int price);
}
class Economy implements AbstractFactory{
    @Override
    public Car getinstance(int price){
        if(price < 300000){
            return new EconomyCar1();
        }
        else if(price >= 300000) return new EconomyCar2();
        return null;
    }
}
class Luxury implements AbstractFactory{
    @Override
    public Car getinstance(int price){
        if(price < 2000000){
            return new LuxuryCar1();
        }
        else if(price >= 2000000) return new LuxuryCar2();
        return null;
    }
}
interface Car{
    public int getTopSpeed();
}
class EconomyCar1 implements Car{
    @Override
    public int getTopSpeed(){
        return 100;
    }
}
class EconomyCar2 implements Car{
    @Override
    public int getTopSpeed(){
        return 125;
    }
}
class LuxuryCar1 implements Car{
    @Override
    public int getTopSpeed(){
        return 170;
    }
}
class LuxuryCar2 implements Car{
    @Override
    public int getTopSpeed(){
        return 250;
    }
}
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        ParentFactory factory = new ParentFactory();
        AbstractFactory abtfactory = factory.createInstance("Expensive");
        Car carobj = abtfactory.getinstance(12000000);
        System.out.println(carobj.getTopSpeed());
    }    
}
