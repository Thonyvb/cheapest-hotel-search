public class Ridgewood extends HotelBase {

    public Ridgewood(String name) {
        super(220.00, 150.00, 5, name);
    }

    public Ridgewood(String name, String CustomerType) {
        this(name);
        setCustomerType(CustomerType);
    }

    @Override
    public void setCustomerType(String CustomerType){
        if (CustomerType.equalsIgnoreCase("rewards")) {
            super.setWeekRate(100.00);
            super.setWeekendRate(40.00);
        } else {
            super.setWeekRate(220.00);
            super.setWeekendRate(150.00);
        }
    }
}
