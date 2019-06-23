public class CreateHotel {

    public Hotel getHotel(String hotelName) throws Exception {
        return getHotel(hotelName, null);
    }

    public Hotel getHotel(String hotelName, String customerType) throws Exception {

        if (hotelName.equalsIgnoreCase("Lakewood")) {
            return customerType == null
                    ? new Lakewood(hotelName)
                    : new Lakewood(hotelName, customerType);
        } else if (hotelName.equalsIgnoreCase("Bridgewood")) {
            return customerType == null
                    ? new Bridgewood(hotelName)
                    : new Bridgewood(hotelName, customerType);
        } else if (hotelName.equalsIgnoreCase("Ridgewood")) {
            return customerType == null
                    ? new Ridgewood(hotelName)
                    : new Ridgewood(hotelName, customerType);
        } else {
            throw new Exception("No existing hotel specified");
        }
    }
}