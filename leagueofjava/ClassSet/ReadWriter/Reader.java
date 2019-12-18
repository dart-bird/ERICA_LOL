package ClassSet.ReadWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import Interface.IFReader;

public class Reader implements IFReader{
    File file;
    FileWriter wfile;
    private String ContentList = "";

    public Reader(File file2) {
        this.file = file2;
        ContentList = "";
    }
    
    @Override
    public void loadContent(File file2){
        try {
            Scanner scan = new Scanner(file2);
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