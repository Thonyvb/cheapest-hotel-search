import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class HotelsReader {
    private String expectedInputFormat;

    public HotelsReader(String expectedInputFormat) {
        this.expectedInputFormat = expectedInputFormat;
    }

    public List<List<String>> readInput(File filename) throws IOException {
        List<List<String>> tempList = new ArrayList<>();
        try ( BufferedReader textReader  = new BufferedReader(
                new FileReader(filename))){

            String inputLine;
            while ((inputLine = textReader.readLine()) != null) {
                if (!isValidInput(inputLine)){
                    System.out.println("No valid input provided" +
                            " / El formato de datos esta incorrecto. " +
                            "Porfavor, use: " + expectedInputFormat);
                    System.exit(0);
                }

                String[] parsedArray = inputLine.split(":|,");

                List<String> listWrapper =  new LinkedList<>(
                        Arrays.asList(parsedArray));

                tempList.add(listWrapper);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    public boolean isValidInput(String input) {
        Pattern pattern = Pattern.compile("^[A-Za-z ]++:.*");
        return pattern.matcher(input).matches();
    }
}
