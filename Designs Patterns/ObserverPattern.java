// Observer Pattern is a behavioral design pattern where one object
// (Subject) maintains a list of dependent objects (Observers) and
// automatically notifies them when its state changes. Observers update
// themselves accordingly without the Subject knowing implementation
// details. This implements loose coupling and supports event-driven systems.

// Example: WeatherStation notifies Phone and TV observers when
// weather changes.

import java.util.*;
interface Observer{
    public void update(String weather);
}
interface Subject{
    public void add(Observer ob);
    public void remove(Observer ob);
    public void notifyUsers();
}
class WeatherStation implements Subject{
    private String weather;
    List<Observer> arr = new ArrayList<>();
    @Override
    public void add(Observer ob){
        arr.add(ob);
    }
    @Override
    public void remove(Observer ob){
        arr.remove(ob);
    }
    @Override
    public void notifyUsers(){
        for(Observer ob : arr){
            ob.update(weather);
        }
    }
    public void setWeather(String weather){
        this.weather = weather;
        notifyUsers();
    }
}
class PhoneObs implements Observer {
    String weather;
    @Override
    public void update(String weather){
        this.weather = weather;
        display();
    }
    public void display(){
        System.out.println("Phone display: The weather now is-> "+weather);
    }
}
class TVObs implements Observer {
    String weather;
    @Override
    public void update(String weather){
        this.weather = weather;
        display();
    }
    public void display(){
        System.out.println("TV display: The weather now is-> "+weather);
    }
}
public class ObserverPattern {
    public static void main(String[] args) {
        WeatherStation w1 = new WeatherStation();
        Observer p1 = new PhoneObs();
        Observer t1 = new TVObs();

        w1.add(p1);
        w1.add(t1);

        w1.setWeather("Cloudy");
        w1.setWeather("Sunny");
    }
}
