package ClassSet.Content;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
    // User && Bot player random by Stat
    public boolean getRandByUserPlayerStat (int key, int stat){
        boolean statResult = false;
        double statrnd = Math.random();
        double tmp = Double.parseDouble(getUserTeamStat(key, stat));
        //System.out.println(tmp);
        if(statrnd < tmp*0.01) statResult = true;
        //System.out.println("현재 랜덤 값: " + statrnd + "현재 선수 값:" + (tmp*0.1));
        return statResult;
    }
    public boolean getRandByBotPlayerStat (int key, int stat){
        boolean statResult = false;
        double statrnd = Math.random();
        double tmp = Double.parseDouble(getBotTeamStat(key, stat));
        if(statrnd > tmp*0.01) statResult = true; 
        return statResult;
    }
    public int getRandByDrackKind () {
        Random randVal = new Random();
        int drackRandom = randVal.nextInt(4)+1;
        return drackRandom;
    }
    public int getRandByStatJ() {
        Random randVal = new Random();
        int StatRandom = randVal.nextInt(4)+3;
        return StatRandom;
    }
    public int getRandByStatL() {
        Random randVal = new Random();
        int StatRandom = randVal.nextInt(2)+3;
        return StatRandom;
    }
    public boolean getRandByEndgameStart() {
        boolean randEndGame = false;
        Random randVal = new Random();
        int rand = randVal.nextInt(3);
        if (rand == 1 || rand == 2) randEndGame = true;
        return randEndGame;
    }
    public int getRandByPlayerLine() {
        Random randVal = new Random();
        int LineRandom = randVal.nextInt(6)+1;
        return LineRandom;
    }
}