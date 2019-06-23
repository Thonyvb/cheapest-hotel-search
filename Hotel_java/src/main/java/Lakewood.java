public class Lakewood extends HotelBase {

    public Lakewood(String name) {
        super(110.00, 90.00, 3, name);
    }

    public Lakewood(String name, String CustomerType) {
        this(name);
        setCustomerType(CustomerType);
    }

    @Override
    public void setCustomerType(String CustomerType){
        if (CustomerType.equalsIgnoreCase("rewards")) {
            super.setWeekRate(80.00);
            super.setWeekendRate(80.00);
        } else {
            super.setWeekRate(110.00);
            super.setWeekendRate(90.00);
        }
    }
}
