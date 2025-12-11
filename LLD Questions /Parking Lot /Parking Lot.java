import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
enum vehicleSize{
    BIKE,
    CAR,
    TRUCK
}
interface Vehicle{
    String getlicense();
    vehicleSize getSize();
}
interface  ParkingSpot{
    boolean isAvailable();
    void occupySpot(Vehicle v);
    void vacateSpot();
    int getSpotNum();
    vehicleSize getSpotsize();
}
class CarSpot implements ParkingSpot{
    private int Spotnum;
    private Vehicle v;

    CarSpot(int Spotnum){
        this.Spotnum=Spotnum;
        this.v=null;
    }
    @Override
    public boolean isAvailable(){
        return v == null;
    }
    @Override
    public void occupySpot(Vehicle v){
        if(isAvailable()){
            this.v = v;
        }else{}
    }
    @Override
    public void vacateSpot(){
        this.v = null;
    }
    @Override
    public int getSpotNum(){
        return this.Spotnum;
    }
    @Override
    public vehicleSize getSpotsize(){
        return vehicleSize.CAR;
    }
}
class BikeSpot implements ParkingSpot{
    private int Spotnum;
    private Vehicle v;

    BikeSpot(int Spotnum){
        this.Spotnum=Spotnum;
        this.v=null;
    }
    @Override
    public boolean isAvailable(){
        return v == null;
    }
    @Override
    public void occupySpot(Vehicle v){
        if(isAvailable()){
            this.v = v;
        }else{}
    }
    @Override
    public void vacateSpot(){
        this.v = null;
    }
    @Override
    public int getSpotNum(){
        return this.Spotnum;
    }
    @Override
    public vehicleSize getSpotsize(){
        return vehicleSize.BIKE;
    }
}
class TruckSpot implements ParkingSpot{
    private int Spotnum;
    private Vehicle v;

    TruckSpot(int Spotnum){
        this.Spotnum=Spotnum;
        this.v=null;
    }
    @Override
    public boolean isAvailable(){
        return v == null;
    }
    @Override
    public void occupySpot(Vehicle v){
        if(isAvailable()){
            this.v = v;
        }else{}
    }
    @Override
    public void vacateSpot(){
        this.v = null;
    }
    @Override
    public int getSpotNum(){
        return this.Spotnum;
    }
    @Override
    public vehicleSize getSpotsize(){
        return vehicleSize.TRUCK;
    }
}

class Car implements Vehicle{
    private String license;
    public Car(String license){
        this.license=license;
    }
    @Override
    public String getlicense(){
        return this.license;
    }
    @Override
    public vehicleSize getSize(){
        return vehicleSize.CAR;
    }
}
class Bike implements Vehicle{
    private String license;
    public Bike(String license){
        this.license=license;
    }
    @Override
    public String getlicense(){
        return this.license;
    }
    @Override
    public vehicleSize getSize(){
        return vehicleSize.BIKE;
    }
}
class Truck implements Vehicle{
    private String license;
    public Truck(String license){
        this.license=license;
    }
    @Override
    public String getlicense(){
        return this.license;
    }
    @Override
    public vehicleSize getSize(){
        return vehicleSize.TRUCK;
    }
}
class ParkingManager{
    private final Map<vehicleSize, List<ParkingSpot>> availableSpots;
    private final Map<Vehicle, ParkingSpot> vehicleToSpotMap;
    
    ParkingManager(Map<vehicleSize, List<ParkingSpot>> availableSpots){
        this.availableSpots = availableSpots;
        this.vehicleToSpotMap = new HashMap<>();
    }

    public ParkingSpot findSpot(Vehicle v){
        vehicleSize vsz = v.getSize();
        for(vehicleSize sz : vehicleSize.values()){
            if(sz.ordinal() >= vsz.ordinal()){
                List<ParkingSpot> spots = availableSpots.get(sz);
                for(ParkingSpot spot : spots){
                    if(spot.isAvailable()){
                        return spot;
                    }
                }
            }
        }
        return null;
    }

    public ParkingSpot parkVehicle(Vehicle v){
        ParkingSpot spot = findSpot(v);
        if(spot != null){
            spot.occupySpot(v);
            availableSpots.get(spot.getSpotsize()).remove(spot);
            vehicleToSpotMap.put(v, spot);
            return spot;
        }
        return null;
    }

    public void vacateSpot(Vehicle v){
        ParkingSpot spot = vehicleToSpotMap.get(v);
        if(spot != null){
            spot.vacateSpot();
            availableSpots.get(spot.getSpotsize()).add(spot);
            vehicleToSpotMap.remove(v);
        }
    }
}

class Ticket{
    private final String ticketId;
    private final Vehicle v;
    private final LocalDateTime entry;
    private LocalDateTime exit;

    Ticket(String ticketId, Vehicle v, LocalDateTime entry){
        this.ticketId = ticketId;
        this.v = v;
        this.entry = entry;
        this.exit = null;
    }
    public BigDecimal calculateParkingDuration(){
        return new BigDecimal(
            Duration.between(entry,
                exit != null ? exit : LocalDateTime.now()
            ).toMinutes());
    }
    public Vehicle getVehicle(){
        return this.v;
    }
    public LocalDateTime getEntryTime(){
        return this.entry;
    }
    public LocalDateTime getExitTime(){
        return this.exit;
    }
    public void setExitTime(LocalDateTime t){
        this.exit = t;
    }
}

interface FareStrategy{
    BigDecimal calculatefare(Ticket t, BigDecimal fee);
}
class BaseFareStrategy implements FareStrategy{
    private final BigDecimal Car_rate = new BigDecimal("20.0");
    private final BigDecimal Bike_rate = new BigDecimal("10.0");
    private final BigDecimal Truck_rate = new BigDecimal("50.0");

    @Override
    public BigDecimal calculatefare(Ticket t, BigDecimal fee){
        BigDecimal totalFare = fee;
        BigDecimal rate;
        if(t.getVehicle().getSize() == vehicleSize.CAR){
            rate = Car_rate;
        }
        else if(t.getVehicle().getSize() == vehicleSize.TRUCK){
            rate = Truck_rate;
        }
        else{
            rate = Bike_rate;
        }
        totalFare = totalFare.add(rate.multiply(t.calculateParkingDuration()));
        return totalFare;
    }
}
class peakHoursFareStrategy implements FareStrategy{
    private final BigDecimal PEAK_HR_MULTIPLIER = new BigDecimal("1.5");
    public peakHoursFareStrategy(){}
    private boolean isPeakhour(LocalDateTime time){
        int hr = time.getHour();
        return (hr >= 8 && hr <= 11) || (hr >= 18 && hr <= 21);
    }
    @Override
    public BigDecimal calculatefare(Ticket t, BigDecimal fee){
        BigDecimal totalFare = fee;
        if(isPeakhour(t.getEntryTime())){
            totalFare = totalFare.multiply(PEAK_HR_MULTIPLIER);
        }
        return totalFare;
    }
}
class FareCalculator{
    private final List<FareStrategy> st;

    public FareCalculator(List<FareStrategy> st){
        this.st = st;
    }
    public BigDecimal calculateFare(Ticket t){
        BigDecimal fare = BigDecimal.ZERO;
        for(FareStrategy fs : st){
            fare = fs.calculatefare(t, fare);
        }
        return fare;
    }
}

class ParkingLotManager{
    private final ParkingManager pm;
    private final FareCalculator fc;

    ParkingLotManager(ParkingManager pm, FareCalculator fc){
        this.pm = pm;
        this.fc = fc;
    }
    
    public Ticket enterVehicle(Vehicle v){
        ParkingSpot spot = pm.parkVehicle(v);
        if(spot != null){
            String ticketID = UUID.randomUUID().toString();
            Ticket t = new Ticket(ticketID, v, LocalDateTime.now());
            return t;
        }
        return null;
    }
    public void exitVehicle(Ticket t){
        if(t != null && t.getExitTime() == null){
            t.setExitTime(LocalDateTime.now());
            pm.vacateSpot(t.getVehicle());
            BigDecimal fare = fc.calculateFare(t);
            System.out.println(
                "Vehicle "+t.getVehicle().getlicense()+
                " | Duration: "+t.calculateParkingDuration()+" min"+
                " | Fare: "+fare
            );
        }
        else{}
    }
}
public class ParkingLot {
    public static void main(String[] args) {
        List<ParkingSpot> bikeSpots = new ArrayList<>();
        bikeSpots.add(new BikeSpot(1));
        bikeSpots.add(new BikeSpot(2));

        List<ParkingSpot> carSpots = new ArrayList<>();
        carSpots.add(new CarSpot(3));
        carSpots.add(new CarSpot(4));

        List<ParkingSpot> truckSpots = new ArrayList<>();
        truckSpots.add(new TruckSpot(5));
        truckSpots.add(new TruckSpot(6));
        
        Map<vehicleSize, List<ParkingSpot>> available = new HashMap<>();
        available.put(vehicleSize.BIKE, bikeSpots);
        available.put(vehicleSize.CAR, carSpots);
        available.put(vehicleSize.TRUCK, truckSpots);

        ParkingManager pm = new ParkingManager(available);

        List<FareStrategy> strategy = new ArrayList<>();
        strategy.add(new BaseFareStrategy());
        strategy.add(new peakHoursFareStrategy());

        FareCalculator fc = new FareCalculator(strategy);
        ParkingLotManager plm = new ParkingLotManager(pm, fc);


        Vehicle bike = new Bike("Bike-101");
        Vehicle car = new Car("Car-202");
        Vehicle truck = new Truck("Truck-303");

        Ticket bikeTicket = plm.enterVehicle(bike);
        System.out.println("Bike parked | Ticket: "+bikeTicket.getEntryTime());
        sleep(10000);
        Ticket carTicket = plm.enterVehicle(car);
        System.out.println("Car parked | Ticket: "+carTicket.getEntryTime());
        sleep(10000);
        Ticket truckTicket = plm.enterVehicle(truck);
        System.out.println("Truck parked | Ticket: "+truckTicket.getEntryTime());
        sleep(10000);

         System.out.println("\n---- EXIT VEHICLES ----");

        plm.exitVehicle(bikeTicket);

        plm.exitVehicle(carTicket);

        plm.exitVehicle(truckTicket);

    }
    public static void sleep(long ms){
        try { Thread.sleep(ms); }
        catch (Exception e) {}  
    }
}
