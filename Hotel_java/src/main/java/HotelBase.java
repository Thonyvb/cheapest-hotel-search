public class HotelBase implements Hotel {
    private double weekRate;
    private double weekendRate;
    private int rating;
    private String name;

    //Child classes can have two constructors
    //Useful if the user does not specify CustomerType at instantiation
    public HotelBase(double weekRate, double weekendRate, int rating, String name) {
        this.weekRate = weekRate;
        this.weekendRate = weekendRate;
        this.rating = rating;
        this.name = name;
    }

    //If a regular customer becomes a reward customer
    //allow customers to change Type
    @Override
    public void setCustomerType(String type) {
        System.out.println(type);
    }

    @Override
    public int getRating(){
        return rating;
    }

    @Override
    public void setWeekRate(double weekRate) {
        this.weekRate = weekRate;
    }

    @Override
    public void setWeekendRate(double weekendRate) {
        this.weekendRate = weekendRate;
    }

    @Override
    public double getWeekRate() {
        return weekRate;
    }

    @Override
    public double getWeekendRate() {
        return weekendRate;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name;
    }
}
