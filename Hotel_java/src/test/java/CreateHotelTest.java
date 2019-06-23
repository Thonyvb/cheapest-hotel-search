import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateHotelTest {
    private CreateHotel createHotel;

    @Before
    public void setUp() {
        createHotel = new CreateHotel();
    }

    @Test
    public void rewardCustomerTypeShouldReturnRewardRates () throws Exception {
        Hotel lakewood = createHotel.getHotel( "lakewood", "Rewards");
        assertEquals((double)80, lakewood.getWeekRate(), 0.0);
        assertEquals((double)80, lakewood.getWeekendRate(), 0.0);

        Hotel Bridgewood = createHotel.getHotel( "Bridgewood", "Rewards");
        assertEquals((double)110, Bridgewood.getWeekRate(), 0.0);
        assertEquals((double)50, Bridgewood.getWeekendRate(), 0.0);

        Hotel Ridgewood = createHotel.getHotel( "Ridgewood", "Rewards");
        assertEquals((double)100, Ridgewood.getWeekRate(), 0.0);
        assertEquals((double)40, Ridgewood.getWeekendRate(), 0.0);
    }

    @Test
    public void regularCustomerTypeShouldReturnRegularRates () throws Exception {
        Hotel lakewood = createHotel.getHotel( "lakewood", "regular");
        assertEquals((double)110, lakewood.getWeekRate(), 0.0);
        assertEquals((double)90, lakewood.getWeekendRate(), 0.0);

        Hotel Bridgewood = createHotel.getHotel( "Bridgewood", "regular");
        assertEquals((double)160, Bridgewood.getWeekRate(), 0.0);
        assertEquals((double)60, Bridgewood.getWeekendRate(), 0.0);

        Hotel Ridgewood = createHotel.getHotel( "Ridgewood", "regular");
        assertEquals((double)220, Ridgewood.getWeekRate(), 0.0);
        assertEquals((double)150, Ridgewood.getWeekendRate(), 0.0);
    }

    //edge case
    @Test
    public void noCustomerTypeShouldReturnRegularRates () throws Exception {
        Hotel lakewood = createHotel.getHotel( "lakewood");
        assertEquals((double)110, lakewood.getWeekRate(), 0.0);
        assertEquals((double)90, lakewood.getWeekendRate(), 0.0);
    }
}