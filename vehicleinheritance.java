import java.io.*;

interface Vehicle {
    
    // Abstract methods defined
    void changeGear(int a);
    void speedUp(int a);
    void applyBrakes(int a);
}

// Class implementing vehicle interface
class Bicycle implements Vehicle{
    
    int speed;
    int gear;
    
    // Change gear
    @Override
    public void changeGear(int newGear){
        gear = newGear;
    }
    
    // Increase speed
    @Override
    public void speedUp(int increment){
        speed = speed + increment;
    }
    
    // Decrease speed
    @Override
    public void applyBrakes(int decrement){
        speed = speed - decrement;
    }
    
    public void printStates() {
        System.out.println("speed: " + speed
            + " gear: " + gear);
    }
}

// Class implementing vehicle interface
class Bike implements Vehicle {
    
    int speed;
    int gear;
    
    // Change gear
    @Override
    public void changeGear(int newGear){
        gear = newGear;
    }
    
    // Increase speed
    @Override
    public void speedUp(int increment){
        speed = speed + increment;
    }
    
    // Decrease speed
    @Override
    public void applyBrakes(int decrement){
        speed = speed - decrement;
    }
    
    public void printStates() {
        System.out.println("speed: " + speed
            + " gear: " + gear);
    }
    
}

class vehicleinheritance
{    
    public static void main (String[] args) 
    {
    
        // Instance of Bicycle(Object)
        Bicycle bicycle = new Bicycle();
        
      	bicycle.changeGear(2);
        bicycle.speedUp(3);
        bicycle.applyBrakes(1);
        
        System.out.print("Bicycle present state : ");
        bicycle.printStates();
        
        // Instance of Bike (Object)
        Bike bike = new Bike();
        bike.changeGear(1);
        bike.speedUp(4);
        bike.applyBrakes(3);
        
        System.out.print("Bike present state : ");
        bike.printStates();
    }
}