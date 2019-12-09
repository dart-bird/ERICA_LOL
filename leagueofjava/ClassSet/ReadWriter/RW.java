package ClassSet.ReadWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import Interface.IFRW;

public class RW implements IFRW{
    File file;
    private String ContentList = "";

    public RW(File file) {
        this.file = file;
        ContentList = "";
    }
    
    @Override
    public void loadContent(File file){
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                ContentList += scan.nextLine();
                ContentList += "\n";
            }
            scan.close();

        } catch (FileNotFoundException e ) {
            // TODO: handle exception
        }
    }
    
    @Override
    public String getContent() {
        return ContentList;
    }

    @Override
    public void writeContent(File file, String inContentString) {
        // TODO Auto-generated method stub
        
    }
}