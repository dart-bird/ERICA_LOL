package ClassSet.Content;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ClassSet.ReadWriter.Writer;

public class ShopContent extends PlayerContent {
    ArrayList<String> list = new ArrayList<String>(); //정보 정리에 필요한 리스트
    private HashMap <Integer, ArrayList<String>> shopdata = new HashMap <Integer, ArrayList<String>>(); //선수 정보 정리 사전
    private String shopdataStr;
    private int chooseRandomVal;
    File file2;
    public ShopContent(File playerdataFile, File wdeckFile) {
        super(playerdataFile);
        loadContent(playerdataFile);
        this.file2 = wdeckFile;
        shopdataStr = "";
        loadShopData();
        chooseRandomVal = 0;
        // TODO Auto-generated constructor stub
    }
    private void loadShopData() {
        for (int i = 0; i<getPlayerdataCnt(); i++) {
            list = new ArrayList<String>();
            String[] splitData = getPlayerdata(i).split("/");
            for (int j = 0; j < 7; j++) { // 선수 하나의 정보 7개를 리스트로 저장
                list.add(splitData[j]);
            }
            shopdata.put(i,list);
            //System.out.println("dd"+list);
        }
        
    }
    public String playerInformation(){
        return "[이름] " + list.get(0).toString()+
        " [닉네임] " + list.get(1).toString()+ 
        " [팀] " + list.get(2).toString()+ 
        " [스킬샷] " + list.get(3).toString()+
        " [로밍능력] " + list.get(4).toString()+
        " [CS] " + list.get(5).toString()+
        " [정글몹] " + list.get(6).toString();
    }
    public String getShopData() {
        
        Random random = new Random();
        chooseRandomVal = random.nextInt(shopdata.size());
        list = shopdata.get(chooseRandomVal);
        shopdataStr = playerInformation() + "\n";
        writeGetShopData(file2, Integer.toString(chooseRandomVal), true);
        return shopdataStr;
    }
    private void writeGetShopData(File file, String inContentString, boolean append) { // write은 민감하기 때문에 숨김
        Writer wUserData = new Writer(file);
        wUserData.writeContent(file, "," + inContentString, append); // 형식에 맞춰서 파일작성함.
    }
}