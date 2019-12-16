package ClassSet.ReadWriter;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import Interface.IFRW;

public class RW implements IFRW{
    File file;
    FileWriter wfile;
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
            System.out.println("Check if right dir: " + file);
        }
    }
    
    @Override
    public String getContent() {
        return ContentList;
    }

    @Override
    public void writeContent(File file, String inContentString) {
        // TODO 파일 쓰기 목표 잡아야함
        try {
            //파일에 문자열을 쓴다
            //하지만 파일이 존재한다면 덮어쓰는게 아니라 .
            //그 뒤에 문자열을 이어서 작성한다.
            wfile.write(inContentString);
            wfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}