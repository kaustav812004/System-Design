// Strategy Pattern is a behavioral design pattern that lets a class
// select one of many algorithms at runtime. The behavior is defined
// through a Strategy interface and implemented by separate strategy
// classes. The Context class uses a Strategy instance and delegates the
// work to it. This removes if-else blocks, supports runtime swapping,
// and follows Open-Closed Principle.
// Example: ShoppingCart chooses different payment strategies like
// UPIPayment, CreditCardPayment, or WalletPayment.

// Strategy Interface  → defines behavior
//  |
//  |-- Concrete Strategies → different implementations
//  |
// Context Class → uses strategy and can switch strategies

import java.util.*;
//Strategy Interface
interface PaymentStrategy {
    void pay(int amt);
}
//Concrete Strategies
class UPIPayment implements PaymentStrategy{
    @Override
    public void pay(int amt){
        System.out.println("Paid "+ amt +" via UPI");
    }
}
class CreditCardPayment implements PaymentStrategy{
    @Override
    public void pay(int amt){
        System.out.println("Paid "+ amt +" via credit card");
    }
}
class WalletPayment implements PaymentStrategy{
    @Override
    public void pay(int amt){
        System.out.println("Paid "+ amt +" from wallet");
    }
}
// Context Class 
class ShoppingCart {
    private PaymentStrategy payStrategy;
    ShoppingCart(PaymentStrategy payStrategy){
        this.payStrategy = payStrategy;
    }
    public void setPaymentStrategy(PaymentStrategy payStrategy){
        this.payStrategy = payStrategy;
    }
    public void checkout(int amt){
        payStrategy.pay(amt);
    }
}
// Client Code
public class Strategy {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart(new UPIPayment());
        cart.checkout(500);

        cart.setPaymentStrategy(new WalletPayment());
        cart.checkout(10000);

        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout(100000);
    }    
}
