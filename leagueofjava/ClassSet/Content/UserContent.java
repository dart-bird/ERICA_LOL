package ClassSet.Content;

import java.io.File;
import ClassSet.ReadWriter.RW;
//import ClassSet.Content.PlayerContent;
import java.util.*;

public class UserContent extends PlayerContent {
    private String UserData = "";
    private String UserDeck = "";
    private HashMap <String, String> userdata = new HashMap <String, String>();
    private HashMap <String, String> userdeck = new HashMap <String, String>();
    
    private String tmpStr = "";
    private int dataIndex = 0;
    private String dataOfkey = "";

    public UserContent(File file, File file2, File file3) {
        super(file);
        UserData = ""; UserDeck = "";
        tmpStr = "";
        dataIndex = 0;
        loadContent(file3); //in PlayerContent
        loadUserData(file);
        pushUserData();
        loadUserDeck(file2);
        pushUserDeck();
    }
    

    private void loadUserData(File file) {
        RW rwUserData = new RW(file);
        rwUserData.loadContent(file);
        UserData = rwUserData.getContent();
    }

    private void loadUserDeck(File file) {
        RW rwUserDeck = new RW(file);
        rwUserDeck.loadContent(file);
        UserDeck = rwUserDeck.getContent();
        
    }
    private void pushUserData() {
        for(int i=0; i<UserData.length(); i++){
            if(UserData.charAt(i) == ','){
                switch(dataIndex){
                    case 0: // to recognize id
                        userdata.put("id",tmpStr);
                        dataIndex++; //다음 pw를 인식 시키기 위해 1증가.
                        tmpStr = "";
                        break;
                    case 1: // to recognize pw
                        tmpStr = tmpStr.replace(",", ""); // erase ',' char
                        userdata.put("pw",tmpStr);
                        dataIndex++; //다음 pw를 인식 시키기 위해 1증가.
                        tmpStr = "";
                        break;
                    case 2: // to recognize exp
                        tmpStr = tmpStr.replace(",", ""); // erase ',' char
                        userdata.put("exp",tmpStr);
                        dataIndex++; //다음 exp를 인식 시키기 위해 1증가.
                        tmpStr = "";
                        break;
                    case 3: // to recognize gold
                        tmpStr = tmpStr.replace(",", ""); // erase ',' char
                        userdata.put("gold",tmpStr);
                        dataIndex++; //다음 gold를 인식 시키기 위해 1증가.
                        tmpStr = "";
                        break;
                    case 4: // to recognize team
                        tmpStr = tmpStr.replace(",", ""); // erase ',' char
                        userdata.put("team",tmpStr);
                        dataIndex++; //다음 team을 인식 시키기 위해 1증가.
                        tmpStr = "";
                        break;
                    case 5: // to recognize deck
                        tmpStr = tmpStr.replace(",", ""); // erase ',' char
                        userdata.put("deck",tmpStr);
                        dataIndex++; //다음 deck을 인식 시키기 위해 1증가.
                        tmpStr = "";
                        break;
                }
            }
            tmpStr += UserData.charAt(i);
        }
    }
    public void pushUserDeck() {
        userdeck.put("INGAME",UserDeck.substring(0, UserDeck.indexOf('/'))); //'/'를 기준으로 선발 후보를 나뉨.
        userdeck.put("OUTGAME",UserDeck.substring(UserDeck.indexOf('/')+1));
        //System.out.println(userdeck.get("OUTGAME"));
    }

    public String getUserDataList() { //review parsing result
        return UserData;
    }

    public String getUserDeckList() { //review parsing result
        return UserDeck;
    }

    public String getUserData(String key){
        return userdata.get(key).toString();
    }
    
    public String getUserDeck(String key){
        //여기 테스트코드 먹여도 작동안함 ㅋㅋ
        dataOfkey ="";
        tmpStr = userdeck.get(key);
        String data[] = tmpStr.split(",");
        
        for(int i=0; i<data.length; i++){
            if (data[i].length() > 0 && data[i].charAt(data[i].length()-1)=='\n') {
                data[i] = data[i].substring(0, data[i].length()-1);
            } //값이 안들어온 이유는 data_deck.gdata 뒤에 \n 이 붙어서 그럼 그걸 삭제 해줌.
            dataOfkey += getPlayerdata(Integer.parseInt(data[i])) + "\n"; // 선수 목록 편하게 보기 위에 뉴라인 먹임
        }
        return dataOfkey;
    }//아니지 내가 좀 잘못한듯
}