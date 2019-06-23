import org.junit.Before;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

public class HotelsAnalyzerTest {
    private HotelsAnalyzer analyzer;

    @Before
    public void setUp() {
        String[] availableHotels = {"Lakewood", "Bridgewood", "Ridgewood"};
        String dateFormat = "ddMMMyyyy(E)";
        analyzer = new HotelsAnalyzer(availableHotels, dateFormat);
    }

    @Test
    public void formattingDateShouldReturnDateObject() throws ParseException {
        Date actualDate = analyzer.parseDate("17Mar2009(tues)", "ddMMMyyyy(E)");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date expectedDate = format.parse("2009-03-17");
        assertEquals(expectedDate, actualDate);
    }

    //edge case
    @Test
    public void calculatePriceShouldReturnTotal() throws Exception {
        List<String> unFormattedDates = Arrays.asList("26Mar2009(thur)", "27Mar2009(fri)", "28Mar2009(sat)");
        CreateHotel createHotel = new CreateHotel();
        Hotel ridgewood = createHotel.getHotel("Ridgewood", "Rewards");
        double total = analyzer.calculatePrice(ridgewood, unFormattedDates);
        assertEquals(240.00, total, 0.0);

        List<String> queryTwo = new LinkedList<>(Arrays.asList("26Mar2009(thur)",
                "27Mar2009(fri)", "28Mar2009(sat)", "29Mar2009(sun)", "30Mar2009(mon)"));
        total = analyzer.calculatePrice(ridgewood, queryTwo);
        assertEquals(380.00, total, 0.0);
    }

    @Test
    public void givenListRatingsShouldReturnMaxRatingHotel() throws Exception {
        CreateHotel createHotel = new CreateHotel();
        Hotel lakewood = createHotel.getHotel("lakewood");
        Hotel bridgewood = createHotel.getHotel("Bridgewood");
        Hotel ridgewood = createHotel.getHotel("ridgewood");

        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(lakewood);
        hotelList.add(bridgewood);
        hotelList.add(ridgewood);

        Hotel result = analyzer.getHotelMaxRating(hotelList);

        assertEquals(5, result.getRating());
        assertEquals("ridgewood", result.toString());
    }

    @Test
    public void givenDatesShouldReturnHotelsMinTotalRate() throws Exception {
        List<String> query = new LinkedList<>(Arrays.asList("Rewards", "26Mar2009(thur)",
                "27Mar2009(fri)", "28Mar2009(sat)"));

        String[] availableHotelsArray = {"Lakewood", "Bridgewood", "Ridgewood"};
        Map<Hotel, Double> result = analyzer.getCheapestHotels(query, availableHotelsArray);
        assertEquals(2, result.size()); //two hotels with equal minimum rates
        assertTrue(result.toString().contains("Lakewood"));
        assertTrue(result.toString().contains("Ridgewood"));
    }

    //edge case
    @Test
    public void givenEmptyListOfDatesShouldReturnNothing() throws Exception {
        List<String> query = new ArrayList<>();
        String[] availableHotelsArray = {"Lakewood", "Bridgewood", "Ridgewood"};
        Map<Hotel, Double> result = analyzer.getCheapestHotels(query, availableHotelsArray);
        assertEquals("{}", result.toString());
    }

    //edge case
    @Test
    public void givenEmptyListOfAvailableHotelsShouldReturnNothing() throws Exception {
        String[] availableHotelsArray = new String[1];
        String[] availableHotelsArrayTwo = new String[0];
        String[] availableHotelsArrayThree = {""};

        List<String> query = new LinkedList<>(Arrays.asList("Rewards", "26Mar2009(thur)",
                "27Mar2009(fri)", "28Mar2009(sat)"));
        Map<Hotel, Double> result = analyzer.getCheapestHotels(query, availableHotelsArray);
        assertEquals("{}", result.toString());

        result = analyzer.getCheapestHotels(query, availableHotelsArrayTwo);
        assertEquals("{}", result.toString());

        result = analyzer.getCheapestHotels(query, availableHotelsArrayThree);
        assertEquals("{}", result.toString());
    }

    @Test
    public void givenListShouldPrintMinRateMaxRatingHotel() throws Exception {
        List<List<String>> testList = new ArrayList<>();
        List<String> query = new LinkedList<>(Arrays.asList("Rewards", "26Mar2009(thur)",
                "27Mar2009(fri)", "28Mar2009(sat)"));
        testList.add(query);
        List<String> testResults = analyzer.getCheapestMaxRatingHotel(testList);
        assertTrue(testResults.toString().contains("Ridgewood"));

        testList.clear();
        List<String> queryTwo = new LinkedList<>(Arrays.asList("Regular", "16Mar2009(mon)",
                "17Mar2009(tues)", "18Mar2009(wed)"));
        testList.add(queryTwo);
        testResults = analyzer.getCheapestMaxRatingHotel(testList);
        assertTrue(testResults.toString().contains("Lakewood"));

        testList.clear();
        List<String> queryThree = new LinkedList<>(Arrays.asList("Regular", "20Mar2009(fri)",
                "21Mar2009(sat)", "22Mar2009(sun)"));
        testList.add(queryThree);
        testResults = analyzer.getCheapestMaxRatingHotel(testList);
        assertTrue(testResults.toString().contains("Bridgewood"));
    }

    //edge case
    @Test
    public void givenLongListShouldPrintMinRateMaxRatingHotel() throws Exception {
        List<List<String>> testList = new ArrayList<>();
        List<String> query = new LinkedList<>(Arrays.asList("Rewards", "26Mar2009(thur)",
                "27Mar2009(fri)", "28Mar2009(sat)", "29Mar2009(sun)", "30Mar2009(mon)"));
        testList.add(query);
        List<String> testResults = analyzer.getCheapestMaxRatingHotel(testList);
        assertTrue(testResults.toString().contains("Ridgewood"));
    }
}



