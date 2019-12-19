package ClassSet.MFrame;

import ClassSet.Content.UserContent;
import ClassSet.Content.ShopContent;
import ClassSet.Content.PlayerContent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Mframe {
    public int cmd; //input int
    File userFile = new File("../leagueofjava/content/data/userdata.gdata"); //userdata dir
    File deckFile = new File("../leagueofjava/content/data/data_deck.gdata"); //userdeck dir
    File playerdataFile = new File("../leagueofjava/content/data/playerdata.gdata"); //playerdata dir
    
    
    Scanner scanner = new Scanner(System.in); //to input int
    public Mframe() {
        cmd = 0;
        //System.out.println(user.getUserData("gold"));
    }
    private UserContent reloadData(){
        UserContent user = new UserContent(userFile, deckFile, playerdataFile); //init usercontent
        return user;
    }
    private void showImformation(){
        UserContent user = reloadData();
        System.out.print("ID:" + user.getUserData("id")); //get id 
        System.out.print(" EXP:" + user.getUserData("exp")); //get gold 
        System.out.println(" GOLD:" + user.getUserData("gold") + "\n"); //get exp
       
    }
    private void showMyplayerList(){
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
    public void menu(){
        cmd = 0;
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("리그오브레전드 매니저 게임");
        System.out.println("*********************************************");
        showImformation();
        System.out.println("1. 내 선수단 관리 2. 이적시장 3. 게임 4. 종료");
        System.out.print("숫자를 입력하세요:");
    }
    public void mypage(){
        //System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("내 선수단 관리");
        System.out.println("*********************************************");
        showImformation();
        showMyplayerList();
        System.out.println("1. 메뉴 2. 선수 교체하기");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
    } public void mypage_2(){
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
            user.writeUserContent(deckFile, user.getUserDeckList(), false);
        } else {
            System.out.println("일치하는 후보 선수가 없습니다.");
        }

        
    }
    public void shop(){
        
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
        File wdeckFile = new File("../leagueofjava/content/data/data_deck.gdata");
        ShopContent shop = new ShopContent(playerdataFile, wdeckFile, userFile);
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
    public void game(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("게임을 시작하시겠습니까?");
        System.out.println("*********************************************");
        showImformation();
        System.out.print("1. 메뉴 ");
        System.out.print("2. 게임 시작\n");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
        
    }
    public void ingame(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("소환사의 협곡에 오신 것을 환영합니다.");
        System.out.println("*********************************************");
        showImformation();
        System.out.println("1. 메뉴로 ");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
        
    }
    public int getCmd(){
        return cmd;
    }
    public void setCmd(int cmd){
        this.cmd = cmd;
    }
}