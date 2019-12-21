package ClassSet.Content;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Array;

import ClassSet.Content.UserContent;
import ClassSet.ReadWriter.Reader;
import ClassSet.Content.ShopContent;
import ClassSet.Content.PlayerContent;

public class GameContent extends PlayerContent {

    private String ContentList = "";
    private File botdataFile = new File("../leagueofjava/content/data/botdata.gdata");
    private File userdataFile = new File("../leagueofjava/content/data/userdata.gdata");
    private File playerdataFile = new File("../leagueofjava/content/data/playerdata.gdata");
    private File datadeckFile = new File("../leagueofjava/content/data/data_deck.gdata");
    ArrayList<String> list = new ArrayList<String>();
    private HashMap<Integer, ArrayList<String>> botDeckClassfic = new HashMap<Integer, ArrayList<String>>();
    private HashMap<Integer, ArrayList<String>> userDeckClassfic = new HashMap<Integer, ArrayList<String>>();
    private String botList;
    private String userList;
    PlayerContent playerContent = new PlayerContent(playerdataFile);
    public GameContent(File playerdataFile) throws IOException {
        super(playerdataFile);
        botList = "";
        loadBot();
        loadUser();
        loadContent(playerdataFile);
        pushBot();
        pushUser();
    }
    private void loadUser() throws IOException {
      //  UserContent userContent = new UserContent(userdataFile,datadeckFile, playerdataFile);
      //  userDeckClassfic = userContent.getUserPlayerHashMap();
    //this code is not working
        try {
            Reader userReader = new Reader(datadeckFile);
            userReader.loadContent(datadeckFile);
            userList = userReader.getContent();
            userList = userList.substring(0,userList.indexOf('/'));
        } catch (FileNotFoundException e){
            System.out.println("Check file!");
        }
    } 
    private void loadBot() throws IOException {
        try {
            Reader botReader = new Reader(botdataFile);
            botReader.loadContent(botdataFile);
            botList = botReader.getContent();
        } catch (FileNotFoundException e){
            System.out.println("Check file!");
        }
    }
    private void pushBot() {
        String[] botIndex = botList.split(",");
        for (int i = 0; i < botIndex.length; i++) {
            list = new ArrayList<>();
            playerContent.loadContent(playerdataFile);
            String tmpPlayerData = playerContent.getPlayerdata(Integer.parseInt(botIndex[i]));
            String[] botPlayerStatIndex = tmpPlayerData.split("/");
            for (int j = 0; j < botPlayerStatIndex.length; j++) {
                list.add(j,botPlayerStatIndex[j]);
            }
            botDeckClassfic.put(i, list);
        }
    }
    private void pushUser() {
        String[] userIndex = userList.split(",");
        for (int i = 0; i < userIndex.length; i++) {
            list = new ArrayList<>();
            playerContent.loadContent(playerdataFile);
            String tmpPlayerData = playerContent.getPlayerdata(Integer.parseInt(userIndex[i]));
            String[] userPlayerStatIndex = tmpPlayerData.split("/");
            for (int j = 0; j < userPlayerStatIndex.length; j++) {
                list.add(j,userPlayerStatIndex[j]);
            }
            userDeckClassfic.put(i, list);
        }
    }
    // setting get list
    // list = *DeckClassfic
    // list.get(0~6) stat
    // key : 0 = top, 1 = mid, 2 = dealer, 3 = supporter, 4 = jungler
    // stat : 0 = name, 1 = nickname, 2 = teamname, 3 = skiilShot, 4 = roaming, 5 = cs, 6 = jungleTry
    //////////////////////////////////////
    // This only use Display
    public String getUserTeam (int key) {
        list = userDeckClassfic.get(key);
        return getPlayerInformation(list);
    }

    public String getBotTeam (int key) {
        list = botDeckClassfic.get(key);
        return getPlayerInformation(list);
    }
    //////////////////////////////////////
    public String getUserTeamStat (int key, int stat) {
        list = userDeckClassfic.get(key);
        return list.get(stat);
    }

    public String getBotTeamStat (int key, int stat) {
        list = botDeckClassfic.get(key);
        return list.get(stat);
    }

    public int getAverageUserTeamStat (int stat) {
        int tmp = 0;
        for (int i = 0; i < 5; i++) {
            tmp += Integer.parseInt(getUserTeamStat(i, stat));
        }
        return tmp/5;
    }

    public int getAverageBotTeamStat (int stat) {
        int tmp = 0;
        for (int i = 0; i < 5; i++) {
            tmp += Integer.parseInt(getBotTeamStat(i, stat));
        }
        return tmp/5;
    }
    // TODO: 게임에 참가하는 선수들 리스트를 보여주고 거기에 따른 팀의 평균치를 게임 시작 전에 보여줘야함
}