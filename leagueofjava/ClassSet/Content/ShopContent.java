package ClassSet.Content;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ClassSet.Content.UserContent;
import ClassSet.ReadWriter.Writer;

public class ShopContent extends PlayerContent {
    ArrayList<String> list = new ArrayList<String>(); // 정보 정리에 필요한 리스트
    private HashMap<Integer, ArrayList<String>> shopdata = new HashMap<Integer, ArrayList<String>>(); // 선수 정보 정리 사전
    private String shopdataStr;
    private int chooseRandomVal;
    File file;
    File file2;
    File file3;

    public ShopContent(File playerdataFile, File wdeckFile, File userFile) {
        super(playerdataFile);
        loadContent(playerdataFile);
        this.file = userFile;
        this.file2 = wdeckFile;
        this.file3 = playerdataFile;
        shopdataStr = "";
        loadShopData();
        chooseRandomVal = 0;
        // TODO Auto-generated constructor stub
    }

    private void loadShopData() {
        for (int i = 0; i < getPlayerdataCnt(); i++) {
            list = new ArrayList<String>();
            String[] splitData = getPlayerdata(i).split("/");
            for (int j = 0; j < 7; j++) { // 선수 하나의 정보 7개를 리스트로 저장
                list.add(splitData[j]);
            }
            shopdata.put(i, list);
            // System.out.println("dd"+list);
        }

    }

    public String playerInformation() {
        return "[이름] " + list.get(0).toString() + " [닉네임] " + list.get(1).toString() + " [팀] " + list.get(2).toString()
                + " [스킬샷] " + list.get(3).toString() + " [로밍능력] " + list.get(4).toString() + " [CS] "
                + list.get(5).toString() + " [정글몹] " + list.get(6).toString();
    }

    public String getShopData() {
        shopdataStr = playerInformation() + "\n";
        return shopdataStr;
    }

    public boolean purchaseProcessing(int price) throws IOException {
        boolean success = false;
        Random random = new Random();
        chooseRandomVal = random.nextInt(shopdata.size());
        list = shopdata.get(chooseRandomVal);
        UserContent user = new UserContent(file, file2, file3); //데이터 사용자 정보, 선수 데이터, 사용자 덱 로드
        if(Integer.parseInt(user.getUserData("gold"))>=price) { //골드 500으로 구입조건 제한
            StringBuffer tmpBuffer = new StringBuffer();
            tmpBuffer.append(user.getUserData("id"));
            tmpBuffer.append("," + user.getUserData("pw"));
            tmpBuffer.append("," + user.getUserData("exp"));
            tmpBuffer.append("," + (Integer.parseInt(user.getUserData("gold"))-price));
            user.writeUserContent(file, tmpBuffer.toString(), false); // 골드 정보 저장
            writeGetShopData(file2, Integer.toString(chooseRandomVal), true);
            success = true;
        }
        return success;
    }
    private void writeGetShopData(File file, String inContentString, boolean append) { // write은 민감하기 때문에 숨김
        Writer wUserData = new Writer(file);
        wUserData.writeContent(file, "," + inContentString, append); // 카드 정보 저장
    }
}