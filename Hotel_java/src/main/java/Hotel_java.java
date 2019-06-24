import javax.swing.*;
import java.io.File;
import java.util.*;

public class Hotel_java {
    public static void main(String[] args) throws Exception {
        //this data can be modified in the Objects
        String[] availableHotels = {"Lakewood", "Bridgewood", "Ridgewood"};
        String dateFormat = "ddMMMyyyy(E)";
        String expectedInputFormat = "<customer_type>: <date1>, <date2>, <date3>, ...";

        String filename;
        File inputFile;

        do {
            filename = JOptionPane.showInputDialog(null,
                    "Please input a valid file name or 'exit' to close / " +
                            "Ingresa el nombre correcto del archivo o 'exit' para salir");


            if( filename.equalsIgnoreCase("exit")) {
                System.exit(0);
            }

            inputFile = new File(filename);
        } while (!inputFile.isFile());

        HotelsReader reader = new HotelsReader(expectedInputFormat);
        HotelsAnalyzer analyzer = new HotelsAnalyzer(availableHotels, dateFormat);

        List<List<String>> inputList = reader.readInput(inputFile);

        if (inputList.isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Empty file / Archivo vacio");
            System.exit(0);
        }

        List<String> messagesPrint = analyzer.getCheapestMaxRatingHotel(inputList);
        StringBuilder showMessage = new StringBuilder("Cheapest Hotel: \n");

        for(String message : messagesPrint) {
            showMessage.append("\n " + message +" \n");
        }
        showMessage.append("\n");
        JOptionPane.showMessageDialog(null, showMessage);
    }
}
