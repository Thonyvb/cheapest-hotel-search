import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HotelsReaderTest {
    private HotelsReader reader;

    @Before
    public void setUp() {
        String expectedInputFormat = "<customer_type>: <date1>, <date2>, <date3>, ...";
        reader = new HotelsReader(expectedInputFormat);
    }

    @Test
    public void GivenFormattedInputShouldReturnTrue() {
        String correctFormat = "Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)";
        assertTrue(reader.isValidInput(correctFormat));
    }

    //edge case
    @Test
    public void GivenWrongFormattedInputShouldReturnFalse() {
        String incorrectFormat = "26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)";
        assertFalse(reader.isValidInput(incorrectFormat));
        incorrectFormat = " ";
        assertFalse(reader.isValidInput(incorrectFormat));
        incorrectFormat = "thisIsWrong";
        assertFalse(reader.isValidInput(incorrectFormat));
        incorrectFormat = "Rewards26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)";
        assertFalse(reader.isValidInput(incorrectFormat));
        incorrectFormat = ":26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)";
        assertFalse(reader.isValidInput(incorrectFormat));
    }
}