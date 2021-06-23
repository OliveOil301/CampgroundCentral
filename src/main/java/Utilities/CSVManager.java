package main.java.Utilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVManager {
    private String fileToModify;

    public CSVManager(String fileToModify) {
        this.fileToModify = fileToModify;
    }

    public void updateCSV(String itemToUpdate, String UpdatedItem) throws IOException {
        File inputFile = new File(this.fileToModify);

        // Read existing file
        CSVReader reader = new CSVReader(new FileReader(inputFile));
        List<String[]> csvBody = reader.readAll();
        // get CSV row column and replace with by using row and column
        for(int i=0; i<csvBody.size(); i++){
            String[] strArray = csvBody.get(i);
            for(int j=0; j<strArray.length; j++){
                if(strArray[j].equalsIgnoreCase(itemToUpdate)){ //String to be replaced
                    csvBody.get(i)[j] = UpdatedItem; //Target replacement
                }
            }
        }
        reader.close();

        // Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',', '"', '"', "\n");
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }

    public void AddToEndOfCSV(String[] itemToAdd) throws IOException {
        File inputFile = new File(this.fileToModify);
        CSVReader reader = new CSVReader(new FileReader(inputFile));
        List<String[]> csvBody = reader.readAll();
        reader.close();

        // Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',', '"', '"', "\n");
        writer.writeAll(csvBody);
        writer.writeNext(itemToAdd);
        writer.flush();
        writer.close();
    }
}
