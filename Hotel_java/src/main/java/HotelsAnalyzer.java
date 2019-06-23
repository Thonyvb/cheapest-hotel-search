import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HotelsAnalyzer {
    private String[] availableHotels;
    private String dateFormat;

    public HotelsAnalyzer(String[] availableHotels, String dateFormat) {
        this.availableHotels = availableHotels;
        this.dateFormat = dateFormat;
    }

    //allows for different Date formats if givenFormat is specified different to "ddMMMyyyy(E)"
    public Date parseDate(String unformattedDate, String givenFormat) throws ParseException {
        String preProcessed = unformattedDate;

        if (givenFormat.equals("ddMMMyyyy(E)")) {
            int trimIndex = unformattedDate.indexOf("("); //eliminates unnecessary information
            preProcessed = unformattedDate.substring(0, trimIndex);
            givenFormat = "ddMMMyyyy"; //eliminates typos when user inserts dates. ex: tues instead of tue
        }

        SimpleDateFormat format = new SimpleDateFormat(givenFormat);
        return format.parse(preProcessed.replaceAll("\\s",""));
    }

    public double calculatePrice(Hotel hotel, List<String> dates) throws ParseException {
        double total = 0.0;
        for (String d : dates) {
            Date aDate = parseDate(d, dateFormat);
            if ((aDate.getDay() == 0) || (aDate.getDay() == 6)) {
                total += hotel.getWeekendRate();
            } else {
                total += hotel.getWeekRate();
            }
        }
        return total;
    }

    /*
     * getCheapestHotels
     * get a map of the hotels with matching minimum total rates
     * @param query is an input line from text file:  <customer_type>: <date1>, <date2>, <date3>, ..
     * @param availableHotelsArray array containing names existing instances of Hotel interface
     * @return Map<Hotel, Double> with Hotel objects that have the minimum total rates between dates specified in query.
     * */
    public Map<Hotel, Double> getCheapestHotels(
            List<String> inputQuery, String[] availableHotelsArray) throws Exception {
        CreateHotel createHotel = new CreateHotel();

        Map<Hotel, Double> tempMapHotelsPrices = new HashMap<>();
        Map<Hotel, Double> resultMap = new HashMap<>();

        if(isValidInput(inputQuery)) {
            double rateTotal;
            double minTotalRate ;

            String customerType = inputQuery.get(0);
            inputQuery.remove(0);

            for (String hotelName : availableHotelsArray) {
                if (hotelName != null && !hotelName.isEmpty()) {
                    Hotel aHotel = createHotel.getHotel(hotelName, customerType);
                    rateTotal = calculatePrice(aHotel, inputQuery);
                    tempMapHotelsPrices.put(aHotel, rateTotal);
                }
            }

            if (!tempMapHotelsPrices.isEmpty()) {
                minTotalRate = Collections.min(tempMapHotelsPrices.values());

                for (Map.Entry<Hotel, Double> item : tempMapHotelsPrices.entrySet()) {
                    if (item.getValue().equals(minTotalRate)) {
                        resultMap.put(item.getKey(), minTotalRate);
                    }
                }
            }
        }
        return resultMap;
    }

    public Hotel getHotelMaxRating(List<Hotel> hotelList) {
        List<Hotel> maxRatingList = new ArrayList<>();

        if(hotelList.isEmpty()) {
            return null;
        }

        if (hotelList.size() > 1) {
            int maxRating = 0;
            for (Hotel aHotel : hotelList){
                if ( maxRating < aHotel.getRating()) {
                    maxRating = aHotel.getRating();
                    maxRatingList.add(0, aHotel);
                }
            }
        } else {
            maxRatingList.add(hotelList.get(0));
        }

        return maxRatingList.get(0);
    }

    /*
     * isValidInput
     * Validates List format.
     * @param input is an input line parsed to list
     * from text file:  <customer_type>: <date1>, <date2>, <date3>, ..
     * @return true if List contains more than one element after CustomerType
     * else it does not contains dates
     * */
    public boolean isValidInput(List<String> input) {
        if ( input.size() < 1) {
            System.out.println("No dates provided" +
                    " / No hay fechas disponibles");
            return false;
        }
        return true;
    }


    public List<String> getCheapestMaxRatingHotel(List<List<String>> inputList) throws Exception {
        List<String> outputMessages = new ArrayList<>();

        for (List<String> query : inputList) {
            Map<Hotel, Double> minRates = getCheapestHotels(query, availableHotels);
            List<Hotel> ratingsList = new ArrayList<>(minRates.keySet());
            String outputMessage = null;
            Hotel result;

            if (minRates.isEmpty()) {
                System.out.println("Error occurred while parsing the input file: List empty");
            } else if (minRates.size() == 1) {
                Map.Entry<Hotel, Double> rateEntry = minRates.entrySet().iterator().next();
                result = rateEntry.getKey();
                outputMessage = result.toString();
            } else {
                result = getHotelMaxRating(ratingsList);
                outputMessage = result == null ? "Error with the input file" : result.toString();
            }

            outputMessages.add(outputMessage);

            //clear data for next iteration if available
            minRates.clear();
            ratingsList.clear();
        }
        return outputMessages;
    }
}
