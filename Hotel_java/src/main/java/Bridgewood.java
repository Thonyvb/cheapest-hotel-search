public class Bridgewood extends HotelBase {

    public Bridgewood(String name) {
        super(160.00, 60.00, 4, name);
    }

    public Bridgewood(String name, String CustomerType) {
        this(name);
        setCustomerType(CustomerType);
    }

    @Override
    public void setCustomerType(String CustomerType) {
        if (CustomerType.equalsIgnoreCase("rewards")) {
            super.setWeekRate(110.00);
            super.setWeekendRate(50.00);
        } else {
            super.setWeekRate(160.00);
            super.setWeekendRate(60.00);
        }
    }
}
