package ClassSet.Content;

import java.io.File;
import ClassSet.ReadWriter.RW;
import java.util.*;

public class UserContent extends RW {
    private String UserList = "";
    private HashMap <String, String> userdata = new HashMap <String, String>();
    
    private String tmpStr = "";
    private int dataIndex = 0;
    
    private String s1 = "";

    File file;
    public UserContent(File file) {
        super(file);
        this.file = file;
        UserList = "";
        tmpStr = "";
        dataIndex = 0;
    }

    public void loadUserList() {
        loadContent(file);
        UserList = getContent();
    }

    public String getUserList() {
        return UserList;
    }

    public void loadDB() {
        for(int i=0; i<UserList.length(); i++){
            if(UserList.charAt(i) == ','){
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
            tmpStr += UserList.charAt(i);
        }
    }

    public String getUserData(String key){
        if(key == "id") s1 = userdata.get("id").toString();
        if(key == "pw") s1 = userdata.get("pw").toString();
        if(key == "exp") s1 = userdata.get("exp").toString();
        if(key == "gold") s1 = userdata.get("gold").toString();
        if(key == "cards") s1 = userdata.get("cards").toString();
        return s1;
    }
}