package ClassSet.Content;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import Interface.IFRW;
public class PlayerContent implements IFRW{
    private String PlayerList = "";
    private HashMap <Integer, String> playerdata = new HashMap <Integer, String>();
    File file;
    public PlayerContent(File file) {
        this.file = file;
        PlayerList = "";
        loadContent(file);
    }
    @Override
    public void loadContent(File file){ //load player data in HashMap.
        int cnt =0;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                playerdata.put(cnt,scan.nextLine());
                cnt++;
            }
            scan.close();

        } catch (FileNotFoundException e ) {
            // TODO: handle exception
            System.out.println("Check if right dir: " + file);
        }
    }
    public String getPlayerList() { //review parsing result
        return PlayerList;
    }

    public String getPlayerdata(int key) {
        return playerdata.get(key);
    }

    public Integer getPlayerdataCnt() {
        return playerdata.size();
    }

    @Override
    public String getContent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void writeContent(File file, String inContentString) {
        // TODO Auto-generated method stub

    }
}