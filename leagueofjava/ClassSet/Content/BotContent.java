package ClassSet.Content;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import ClassSet.Content.PlayerContent;
import ClassSet.ReadWriter.Reader;

public class BotContent extends PlayerContent {
    
    private HashMap <Integer, ArrayList<String>> botDeckClassfic = new HashMap <Integer, ArrayList<String>>();

    private File botFile;
    private String botList;
    public BotContent(File playerFile, File botFile){
        super(playerFile);
        botList = "";
        this.botFile = botFile;
        loadContent(playerFile); //playerFile is sharing file. So, used extends class.
        loadBot(); //loading bot list file
        pushBotDeck(); //push hashmap with classfication
    }

    Reader botReader = new Reader(botFile);

    private void loadBot() {
        botReader.loadContent(botFile);
        botList = botReader.getContent();
    }

    private String[] dataIndexFormating(String listStr, String regex){
        String[] dataIndex = listStr.split(regex);
        for(int i=0; i<dataIndex.length; i++){
            if (dataIndex[i].length() > 0 && dataIndex[i].charAt(dataIndex[i].length()-1)=='\n') {
                dataIndex[i] = dataIndex[i].substring(0, dataIndex[i].length()-1);
            } //deck.gdata in delete '\n'. This working, for praseInt method.
        }
        return dataIndex;
    } 
    ArrayList<String> list = new ArrayList<String>(); // init list
    private void pushBotDeck() { // push hashmap with classfied data
        String[] botListIndex = dataIndexFormating(botList, ",");
        for (int i = 0; i < botListIndex.length; i++) {
            String tmpStr = getPlayerdata(Integer.parseInt(botListIndex[i]));
            list = new ArrayList<String>(); // init list
            String[] splitbotPlayer = dataIndexFormating(tmpStr, "/");
            for (int j = 0; j < 7; j++) { // save splited bot player data, index 0 ~ 6
                list.add(splitbotPlayer[j]);
            }
            botDeckClassfic.put(i,list);
        }
    }
    public String getBotContentAverage(int averageTarget) {
        int sum = 0;
        for (int i = 0; i < botDeckClassfic.size(); i++) {
            list = botDeckClassfic.get(i); // select botPlayer index
            sum += Integer.parseInt(list.get(averageTarget));
        }
        return Integer.toString(sum/botDeckClassfic.size()); // String return about averageTarget
    }
    public String getBotContent(int i) {
        try {
            list = botDeckClassfic.get(i); // repeat input list index of i to current list
            return getPlayerInformation(list) + "\n";
        } catch (Exception e) {
            System.out.println(list);
            return "현재 봇 리스트가 존재하지 않습니다.";
        }
    }
}