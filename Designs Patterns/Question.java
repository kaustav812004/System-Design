import java.util.*;
interface Observer {
    public void update(String stock);
}
interface Subject{
    public void subscribe(Observer ob);
    public void unsubscribe(Observer ob);
    public void notifyUsers();
}
class WalmartProduct implements Subject {
    
    Boolean status;
    String stock;
    private List<Observer> arr = new ArrayList<>();

    WalmartProduct(String stock){
        this.stock = stock;
    }
    @Override
    public void subscribe(Observer ob){
        arr.add(ob);
    }
    @Override
    public void unsubscribe(Observer ob){
        arr.remove(ob);
    }
    @Override
    public void notifyUsers() {
        for(Observer ob : arr){
            ob.update(stock);
        }
    }
    public void setStock(Boolean status) {
        this.status = status;
        if(status){
            notifyUsers();
        }
    }
}
class EmailCustomer implements Observer{
    String stock;
    String email;
    EmailCustomer(String email){
        this.email = email;
    }
    @Override
    public void update(String stock){
        this.stock = stock;
        display();
    }
    public void display(){
        System.out.println("Email sent to " + email + ": " + stock + " is now available!");
    }
}
class SMSCustomer implements Observer{
    String stock;
    String phoneNo;
    SMSCustomer(String phoneNo){
        this.phoneNo = phoneNo;
    }
    @Override
    public void update(String stock){
        this.stock = stock;
        display();
    }
    public void display(){
        System.out.println("SMS sent to " + phoneNo + ": " + stock + " is now available!");
    }
}
public class Question {
    public static void main(String[] args) {
        WalmartProduct laptop = new WalmartProduct("Macbook");

        Observer kaus = new EmailCustomer("kaustav812004@gmail.com");
        Observer rick = new SMSCustomer("8910258458");

        laptop.subscribe(kaus);
        laptop.subscribe(rick);

        laptop.setStock(true);
    }    
}
