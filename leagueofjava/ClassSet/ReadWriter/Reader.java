package ClassSet.ReadWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import Interface.IFReader;

public class Reader implements IFReader{
    File file;
    
    private String ContentList = "";

    public Reader(File file) {
        ContentList = "";
    }
    
    @Override
    public void loadContent(File file){
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                ContentList += scan.nextLine();
                ContentList += "\n";
            }
            scan.close();
        } catch (FileNotFoundException e){
            System.out.println("Check file!");
        }
    }
    
    @Override
    public String getContent() {
        return ContentList;
    }


}