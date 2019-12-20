package ClassSet.Content;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Array;

import ClassSet.Content.UserContent;
import ClassSet.ReadWriter.Reader;
import ClassSet.Content.ShopContent;
import ClassSet.Content.PlayerContent;

public class GameContent extends PlayerContent {

    private File botdataFile = new File("../leagueofjava/content/data/botdata.gdata");
    private File userdataFile = new File("../leagueofjava/content/data/userdata.gdata");
    private File playerdataFile = new File("../leagueofjava/content/data/userdata.gdata");
    private File deckdataFile = new File("../leagueofjava/content/data/userdata.gdata");

    private HashMap<Integer, ArrayList<String>> botDeckClassfic = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> userDeckClassfic = new HashMap<Integer, ArrayList<String>>();
    private String botList;

    public GameContent(File playerdataFile) throws IOException {
        super(playerdataFile);
        botList = "";
        loadBot();
        pushBotDeck();
        loadContent(playerdataFile);
        loadUserPlayer();
    }

    private void loadBot() throws IOException {
        Reader botReader =  new Reader(botdataFile);
        botReader.loadContent(botdataFile);
        botList = botReader.getContent();
    }

    private void loadUserPlayer() throws IOException {
        UserContent userContent = new UserContent(userdataFile, deckdataFile, playerdataFile);
        userDeckClassfic = userContent.getHashMapUserPlayer();
    }

    private String[] dataIndexFormating(String listStr, String regex){
        String[] dataIndex = listStr.split(regex);
        return dataIndex;
    } 
    ArrayList<String> list = new ArrayList<String>(); // init list
    private void pushBotDeck() { // push hashmap with classfied data
        String[] botListIndex = dataIndexFormating(botList, ",");
        System.out.println(botList);
        for (int i = 0; i < botListIndex.length; i++) {
            String tmpStr = getPlayerdata(Integer.parseInt(botListIndex[i]));
            list = new ArrayList<String>(); // init list
            String[] splitbotPlayer = dataIndexFormating(tmpStr, "/");
            for (int j = 0; j < 7; j++) { // save splited bot player data, index 0 ~ 6
                list.add(splitbotPlayer[j]);
            }
        }
    }
    // key: 0 = top, 1 = mid, 2 = dealer, 3 = supporter, 4 = jungler
    // statInex: 1 = name, 2 = nickname, 3 = team, 4 = skillShot, 5 = roaming, 6 = cs, 7 = jungleTry
    public String getBotPlayerStat(int key, int statIndex) {
        list = botDeckClassfic.get(key);
        System.out.println(list);
        return list.get(statIndex);
    }
    public String getUserPlayerStat(int key, int statIndex) {
        list = userDeckClassfic.get(key);
        return list.get(statIndex);
    }

    
    // TODO: 게임에 참가하는 선수들 리스트를 보여주고 거기에 따른 팀의 평균치를 게임 시작 전에 보여줘야함
}