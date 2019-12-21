package ClassSet.MFrame;

import ClassSet.Content.UserContent;
import ClassSet.Content.ShopContent;
import ClassSet.Content.GameContent;
import ClassSet.Content.PlayerContent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Mframe {
    public int cmd; //input int
    File userdataFile = new File("../leagueofjava/content/data/userdata.gdata"); //userdata dir
    File deckdataFile = new File("../leagueofjava/content/data/data_deck.gdata"); //userdeck dir
    File playerdataFile = new File("../leagueofjava/content/data/playerdata.gdata"); //playerdata dir
    File botdataFile = new File("../leagueofjava/content/data/botdata.gdata"); //botdata dir
    
    Scanner scanner = new Scanner(System.in); //to input int
    public Mframe() {
        cmd = 0;
        //System.out.println(user.getUserData("gold"));
    }
    private UserContent reloadData() throws IOException {
        UserContent user = new UserContent(userdataFile, deckdataFile, playerdataFile); //init usercontent
        return user;
    }
    private void showImformation() throws IOException {
        UserContent user = reloadData();
        System.out.print("ID:" + user.getUserData("id")); //get id 
        System.out.print(" EXP:" + user.getUserData("exp")); //get gold 
        System.out.println(" GOLD:" + user.getUserData("gold") + "\n"); //get exp
       
    }
    private void showMyplayerList() throws IOException {
        UserContent user = reloadData();
        System.out.println(">>>>선발<<<<");
        System.out.println("\n<< 탑   >> \n" + user.getUserDeck("INGAME","top"));
        System.out.println("\n<< 미드 >> \n" + user.getUserDeck("INGAME","mid"));
        System.out.println("\n<< 원딜 >> \n" + user.getUserDeck("INGAME","dealer"));
        System.out.println("\n<< 서폿 >> \n" + user.getUserDeck("INGAME","supporter"));
        System.out.println("\n<< 정글 >> \n" + user.getUserDeck("INGAME","jungle") + "\n");
        System.out.println(">>>>후보<<<<");
        System.out.println(user.getUserDeck("OUTGAME","other")); 
    }
    public void menu() throws IOException {
        cmd = 0;
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("리그오브레전드 매니저 게임");
        System.out.println("*********************************************");
        showImformation();
        System.out.println("1. 내 선수단 관리 2. 이적시장 3. 게임 4. 종료");
        System.out.print("숫자를 입력하세요:");
    }
    public void mypage() throws IOException {
        //System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("내 선수단 관리");
        System.out.println("*********************************************");
        showImformation();
        showMyplayerList();
        System.out.println("1. 메뉴 2. 선수 교체하기");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
    } public void mypage_2() throws IOException {
        int input = 0;
        UserContent user = reloadData();
        //System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("내 선수단 관리");
        System.out.println("*********************************************");
        showImformation();
        showMyplayerList();
        System.out.println("1. 탑 2. 미드 3. 원딜 4. 서폿 5. 정글");
        System.out.print("숫자를 입력하세요:");
        input = scanner.nextInt();
        String userChStr = "";
        switch (input) {
            case 1:
                userChStr = "top";
                break;
            case 2:
                userChStr = "mid";
                break;
            case 3:
                userChStr = "dealer";
                break;
            case 4:
                userChStr = "supporter";
                break;
            case 5:
                userChStr = "jungle";
                break;
        }
        String userChStr2 = "";
        System.out.println(userChStr +" selected. 선수이름. <바꾸기> (원하는 선수의 후보 이름 입력 후 엔터를 눌러주세요)");
        System.out.print("이름을 입력하세요:");
        userChStr2 = scanner.next();
        if(user.chkUserDeckName(userChStr2)){
            System.out.println("일치하는 후보 선수를 찾았습니다.");
            int ingameData = user.getUserDeckIndex("INGAME", userChStr); //선발 -> 후보 될 어떤 라인에 있는 선수, playerdata상의 index 가져옴
            int outgameData = user.getUserDeckIndex("OUTGAME", userChStr2); //후보 -> 선발 될 선수, playerdata상의 index 가져옴
            user.setUserDeckByName(ingameData, outgameData);
            user.writeUserContent(deckdataFile, user.getUserDeckList(), false);
        } else {
            System.out.println("일치하는 후보 선수가 없습니다.");
        }

        
    }
    public void shop() throws IOException {
        
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("이적시장 (영입이라 읽고 뽑기라고 말한다 ^^77)");
        System.out.println("*********************************************");
        showImformation();
        System.out.println("1. 메뉴 2. 선수영입");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
        
    }  public void shop_result() throws InterruptedException, IOException {
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        File wdeckdataFile = new File("../leagueofjava/content/data/data_deck.gdata");
        ShopContent shop = new ShopContent(playerdataFile, wdeckdataFile, userdataFile);
        UserContent user = reloadData();
        boolean result = shop.purchaseProcessing(500);
        user = reloadData();
        System.out.println("*********************************************");
        System.out.println("이적시장 (영입이라 읽고 뽑기라고 말한다 ^^77)");
        System.out.println("*********************************************");
        showImformation();
        if(result == true){
            System.out.println("*********************************************");
            for(int i=0; i<8; i++){ Thread.sleep(50); System.out.print("두"); Thread.sleep(50); System.out.print("군");}
            System.out.println("\n*********************************************");
            System.out.println(shop.getShopData());
        }else System.out.println("골드가 부족합니다.\n");
        System.out.println("1. 메뉴 2. 선수영입");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
    }
    public void showUserTeam () throws IOException {
        GameContent gameContent = new GameContent(playerdataFile);
        System.out.println(" 스킬샷 평균: " + gameContent.getAverageUserTeamStat(3)
                         + " 로밍 평균: " + gameContent.getAverageUserTeamStat(4)
                         + " CS: " + gameContent.getAverageUserTeamStat(5)
                         + " 정글시도 평균: " + gameContent.getAverageUserTeamStat(6));
        System.out.println("<< 탑 >>");
        System.out.println(gameContent.getUserTeam(0) + "\n");
        System.out.println("<< 미드 >>");
        System.out.println(gameContent.getUserTeam(1) + "\n");
        System.out.println("<< 원딜 >>");
        System.out.println(gameContent.getUserTeam(2) + "\n");
        System.out.println("<< 서폿 >>");
        System.out.println(gameContent.getUserTeam(3) + "\n");
        System.out.println("<< 정글 >>");
        System.out.println(gameContent.getUserTeam(4) + "\n");
    }
    public void showBotTeam () throws IOException {
        GameContent gameContent = new GameContent(playerdataFile);
        System.out.println(" 스킬샷 평균: " + gameContent.getAverageBotTeamStat(3)
                         + " 로밍 평균: " + gameContent.getAverageBotTeamStat(4)
                         + " CS: " + gameContent.getAverageBotTeamStat(5)
                         + " 정글시도 평균: " + gameContent.getAverageBotTeamStat(6));
        System.out.println("<< 탑 >>");
        System.out.println(gameContent.getBotTeam(0) + "\n");
        System.out.println("<< 미드 >>");
        System.out.println(gameContent.getBotTeam(1) + "\n");
        System.out.println("<< 원딜 >>");
        System.out.println(gameContent.getBotTeam(2) + "\n");
        System.out.println("<< 서폿 >>");
        System.out.println(gameContent.getBotTeam(3) + "\n");
        System.out.println("<< 정글 >>");
        System.out.println(gameContent.getBotTeam(4) + "\n");
    }
    public void game() throws IOException {
        //System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("게임을 시작하시겠습니까?");
        System.out.println("*********************************************");
        showImformation();
        System.out.println("감독님 팀\n"); showUserTeam();
        System.out.println("상대 팀\n"); showBotTeam();
        System.out.print("1. 메뉴 ");
        System.out.print("2. 게임 시작\n");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
        
    }
    public void narationPlayer (int playerLine) throws IOException {
        GameContent gameContent = new GameContent(playerdataFile);
        switch (playerLine) {
            case 0:
                System.out.print("탑 라인인 " + gameContent.getUserTeamStat(0, 0) + "선수가 ");
                break;
            case 1:
                System.out.print("미드 라인인 " + gameContent.getUserTeamStat(1, 0) + "선수가 ");
                break;
            case 2:
                System.out.print("원딜 라인인 " + gameContent.getUserTeamStat(2, 0) + "선수가 ");
                break;
            case 3:
                System.out.print("서폿 라인인 " + gameContent.getUserTeamStat(3, 0) + "선수가 ");
                break;
            case 4:
                System.out.print("정글 라인인 " + gameContent.getUserTeamStat(4, 0) + "선수가 ");
                break;
            
            default:
                break;
        }
    }
    public void ingame() throws IOException, InterruptedException {
        GameContent gameContent = new GameContent(playerdataFile);
        //System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("소환사의 협곡에 오신 것을 환영합니다.");
        System.out.println("*********************************************");
        showImformation();
        int line = 0; int jungleTime = 0; int jungleMob = 0;
        HashMap<Integer, ArrayList<Double>> userStat = new HashMap<Integer, ArrayList<Double>>();
        HashMap<Integer, ArrayList<Double>> botStat = new HashMap<Integer, ArrayList<Double>>();
        // this hashmap change while ingame did not effect playerdata.
        ArrayList<Double> doubleList = new ArrayList<>();
        while (true) {
            Thread.sleep(3000);
            System.out.println("현재라인:" + line);
            if (line == 5) line = 0; 
            boolean userPlayerResult = gameContent.getRandByUserPlayerStat(line, 3);
            boolean botPlayerResult = gameContent.getRandByBotPlayerStat(line, 3);
            if (userPlayerResult && !botPlayerResult) {
                System.out.println("감독팀 공격 이벤트!");
                switch(line) {
                    case 0:
                        System.out.println("탑에서 감독님의 팀 ");
                        break;
                    case 1:
                        System.out.println("미드에서 감독님의 팀 ");
                        break;
                    case 2:
                        System.out.println("봇에서 감독님의 팀 ");
                        break;
                    case 3:
                        System.out.println("봇에서 감독님의 팀 ");
                        break;
                    case 4:
                        System.out.println("정글에서 감독님의 팀 ");
                        line = 0;
                        break;
                }
            } else if (!userPlayerResult && botPlayerResult) {
                System.out.println("상대팀 공격 이벤트!");
                switch(line) {
                    case 0:
                        System.out.println("탑에서 감독님의 팀 ");
                        break;
                    case 1:
                        System.out.println("미드에서 감독님의 팀 ");
                        break;
                    case 2:
                        System.out.println("봇에서 감독님의 팀 ");
                        break;
                    case 3:
                        System.out.println("봇에서 감독님의 팀 ");
                        break;
                    case 4:
                        System.out.println("정글에서 감독님의 팀 ");
                        line = 0;
                        break;
                }
            } else {
                System.out.println("아무 일도 일어나지 않음. 대치!");
            }
            line++;

        }
        // TODO 현재 나레이션 구성하고 break해야함
        // System.out.println("1. 메뉴로 ");
        // System.out.print("숫자를 입력하세요:");
        // cmd = scanner.nextInt();
        
    }
    public int getCmd(){
        return cmd;
    }
    public void setCmd(int cmd){
        this.cmd = cmd;
    }
}