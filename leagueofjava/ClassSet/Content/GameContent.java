package ClassSet.Content;

import java.io.File;

import ClassSet.Content.UserContent;
import ClassSet.Content.ShopContent;
import ClassSet.Content.PlayerContent;
import ClassSet.Content.BotContent;

public class GameContent {

    private File botFile; 
    private File userFile; private File playerFile; private File deckFile;

    public GameContent(File botFile, File userFile, File playerFile, File deckFile) {

        this.botFile = botFile;
        this.userFile = userFile;
        this.playerFile = playerFile;
        this.deckFile = deckFile;

    }

    private BotContent botContent = new BotContent(playerFile, botFile);
    private UserContent userContent = new UserContent(userFile, playerFile, deckFile);
    
    // TODO: 게임에 참가하는 선수들 리스트를 보여주고 거기에 따른 팀의 평균치를 게임 시작 전에 보여줘야함
}