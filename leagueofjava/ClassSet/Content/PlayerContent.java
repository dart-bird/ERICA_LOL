package ClassSet.Content;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Interface.IFReader;
public class PlayerContent implements IFReader{
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
        try {
            int cnt =0;
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                playerdata.put(cnt, scan.nextLine());
                cnt++;
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Check file!");
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
    
    public String getPlayerInformation(ArrayList<String> list){
        return "[이름] " + list.get(0).toString()+
        " [닉네임] " + list.get(1).toString()+ 
        " [팀] " + list.get(2).toString()+ 
        " [스킬샷] " + list.get(3).toString()+
        " [로밍능력] " + list.get(4).toString()+
        " [CS] " + list.get(5).toString()+
        " [정글몹] " + list.get(6).toString();
    }

    @Override
    public String getContent() {
        // TODO Auto-generated method stub
        return null;
    }
}