package ClassSet.ReadWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Interface.IFReader;

public class Reader implements IFReader {
    File file;

    private String ContentList = "";

    public Reader(File file) {
        ContentList = "";
    }

    @Override
    public void loadContent(File file) throws IOException {
        try {    
            BufferedReader br = new BufferedReader(new FileReader(file));
            ContentList += br.readLine();            
        } catch (FileNotFoundException e){
            System.out.println("Check file!");
        }
    }
    
    @Override
    public String getContent() {
        return ContentList;
    }


}