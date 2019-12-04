package ClassSet.Content;

import ClassSet.ReadWrite.RW;

public class TeamContent extends RW{
    private String TeamList = "";
    File file;

    public TeamContent(File file) {
        super(file);
        this.file = file;
        TeamList = "";
    }

    public void loadUserList() {
        loadContent(file);
        TeamList = getContent();
    }

    public String getTeamList() {
        return TeamList;
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