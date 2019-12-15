package ClassSet.Content;

import java.io.File;
import ClassSet.ReadWriter.RW;
//import ClassSet.Content.PlayerContent;
import java.util.*;

public class UserContent extends PlayerContent {
    private String UserData = "";
    private String UserDeck = "";
    ArrayList<String> list = new ArrayList<String>(); //정보 정리에 필요한 리스트
    private HashMap <String, String> userdata = new HashMap <String, String>(); //사용자 정보 정리 사전
    private HashMap <String, String> userdeck = new HashMap <String, String>(); //사용자 덱 선발, 후보 정리 사전
    private HashMap <Integer, ArrayList<String>> userdeckClassfic_ingame = new HashMap <Integer, ArrayList<String>>(); //선발된 선수 정보 정리 사전
    private HashMap <Integer, ArrayList<String>> userdeckClassfic_outgame = new HashMap <Integer, ArrayList<String>>(); //후보선수 정보 정리 사전
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
    private String playerInformation(){
        return "[이름] " + list.get(0).toString()+
        " [닉네임] " + list.get(1).toString()+ 
        " [팀] " + list.get(2).toString()+ 
        " [스킬샷] " + list.get(3).toString()+
        " [로밍능력] " + list.get(4).toString()+
        " [CS] " + list.get(5).toString()+
        " [정글몹] " + list.get(6).toString();
    }
    public String getUserDeck(String key, String position){
        
        dataOfkey ="";
        tmpStr = userdeck.get(key);
        String[] data = tmpStr.split(",");
        for(int i=0; i<data.length; i++){
            list = new ArrayList<String>(); //list를 매번마다 초기화 시켜줌.
            if (data[i].length() > 0 && data[i].charAt(data[i].length()-1)=='\n') {
                data[i] = data[i].substring(0, data[i].length()-1);
            } //data_deck.gdata 뒤에 \n을 삭제 해줌. 그래야 정상적으로 parse가 됨
            //dataOfkey += getPlayerdata(Integer.parseInt(data[i]))+ "\n"; // 선수 목록 편하게 보기 위에 뉴라인 먹임 dataOfkey의 문자열을 other에 적용
            String[] splitData = getPlayerdata(Integer.parseInt(data[i])).split("/");
            
            for (int j = 0; j < 7; j++) { // 선수 하나의 정보 7개를 리스트로 저장
                list.add(splitData[j]);
            }
            
            if(key == "INGAME") userdeckClassfic_ingame.put(i,list); // 각 선발선수 정보 정리
            if(key == "OUTGAME") userdeckClassfic_outgame.put(i,list); // 각 후발선수 정보 정리
        }
         //name에 nick, team, skillshot, roaming, cs, object 키를 담았으면좋겠음
         switch(position){
             case "top":
                 list = userdeckClassfic_ingame.get(0);
                 return playerInformation();
             case "mid":
                 list = userdeckClassfic_ingame.get(1);
                 return playerInformation();
             case "dealer":
                 list = userdeckClassfic_ingame.get(2);
                 return playerInformation();
             case "supporter":
                 list = userdeckClassfic_ingame.get(3);
                 return playerInformation();
             case "jungle":
                 list = userdeckClassfic_ingame.get(4);
                 return playerInformation();
             case "other":
                 for(int i=0; i<userdeckClassfic_outgame.size(); i++){
                     list = userdeckClassfic_outgame.get(i);
                     dataOfkey += playerInformation() + "\n\n";
                 }
                 return dataOfkey;
         }
        return "NO CARDS!";
    }

}