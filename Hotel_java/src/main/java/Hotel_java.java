import java.io.File;
import java.util.*;

public class Hotel_java {
    public static void main(String[] args) throws Exception {
        String[] availableHotels = {"Lakewood", "Bridgewood", "Ridgewood"};
        String dateFormat = "ddMMMyyyy(E)";
        String expectedInputFormat = "<customer_type>: <date1>, <date2>, <date3>, ...";

        String filename;
        Scanner inputReader = new Scanner(System.in);
        File inputFile;

        do {
            System.out.println("Please input a valid file name or 'exit' to close / " +
                    "Ingresa el nombre correcto del archivo o 'exit' para salir");

            filename = inputReader.nextLine();

            if( filename.equalsIgnoreCase("exit")) {
                System.exit(0);
            }

            inputFile = new File(filename);
        } while (!inputFile.isFile());

        HotelsReader reader = new HotelsReader(expectedInputFormat);
        HotelsAnalyzer analyzer = new HotelsAnalyzer(availableHotels, dateFormat);

        List<List<String>> inputList = reader.readInput(inputFile);

        if (inputList.isEmpty()){
            System.out.println("Empty file / Archivo vacio");
            System.exit(0);
        }

        List<String> messagesPrint = analyzer.getCheapestMaxRatingHotel(inputList);

        for(String message : messagesPrint) {
            System.out.println(message);
        }
    }
}
