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
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
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
    // key : 0 = top, 1 = mid, 2 = dealer, 3 = supporter, 4 = jungler
    // stat : 0 = name, 1 = nickname, 2 = teamname, 3 = skiilShot, 4 = roaming, 5 = cs, 6 = jungleTry
    public void narationPlayer (int playerLine) throws IOException {
        GameContent gameContent = new GameContent(playerdataFile);
        switch (playerLine) {
            case 0:
                System.out.print("탑 라인인 " + gameContent.getUserTeamStat(0, 1) + "선수가 ");
                break;
            case 1:
                System.out.print("미드 라인인 " + gameContent.getUserTeamStat(1, 1) + "선수가 ");
                break;
            case 2:
                System.out.print("원딜 라인인 " + gameContent.getUserTeamStat(2, 1) + "선수가 ");
                break;
            case 3:
                System.out.print("서폿 라인인 " + gameContent.getUserTeamStat(3, 1) + "선수가 ");
                break;
            case 4:
                System.out.print("정글 라인인 " + gameContent.getUserTeamStat(4, 1) + "선수가 ");
                break;
            
            default:
                break;
        }
    }
    public void narationDrackShow (int drackKind) {
        switch (drackKind) {
            case 1:
                System.out.println("<<바람용 출현>>");
                break;
            case 2:
                System.out.println("<<대지용 출현>>");
                break;
            case 3:
                System.out.println("<<화염용 출현>>");
                break;
            case 4:
                System.out.println("<<물용 출현>>");
                break;
            case 5:
                System.out.println("<<장로 출현>>");
            }
    }
    public int narationDrackTry (int drackKind, int line) throws IOException {
        GameContent gameContent = new GameContent(playerdataFile);
        boolean userPlayerResult = gameContent.getRandByUserPlayerStat(line, 6);
        boolean botPlayerResult = gameContent.getRandByBotPlayerStat(line, 6);
        switch (drackKind) {
            case 1:
                if (userPlayerResult && !botPlayerResult) {
                    System.out.println("바람용을 먹었습니다!\n");
                    return 6;
                } else if (!userPlayerResult && botPlayerResult) {
                    System.out.println("바람용을 먹을려 했지만 빼앗겼습니다!\n");
                    return -6;
                }
                break;
            case 2:
                if (userPlayerResult && !botPlayerResult) {
                    System.out.println("대지용을 먹었습니다!\n");
                    return 6;
                } else if (!userPlayerResult && botPlayerResult) {
                    System.out.println("대지용을 먹을려 했지만 빼앗겼습니다!\n");
                    return -6;
                }
                break;
            case 3:
                if (userPlayerResult && !botPlayerResult) {
                    System.out.println("화염용을 먹었습니다!\n");
                    return 6;
                } else if (!userPlayerResult && botPlayerResult) {
                    System.out.println("화염용을 먹을려 했지만 빼앗겼습니다!\n");
                    return -6;
                }
                break;
            case 4:
                if (userPlayerResult && !botPlayerResult) {
                    System.out.println("물용을 먹었습니다!\n");
                    return 6;
                } else if (!userPlayerResult && botPlayerResult) {
                    System.out.println("물용을 먹을려 했지만 빼앗겼습니다!\n");
                    return -6;
                }
                break;
            case 5:
            if (userPlayerResult && !botPlayerResult) {
                System.out.println("장로를 먹었습니다!\n");
                return 7;
            } else if (!userPlayerResult && botPlayerResult) {
                System.out.println("장로를 먹을려 했지만 빼앗겼습니다!\n");
                return -7;
            }
            break;
        }
        return 0;
    }
    public int narationCounterJungle(int line) throws IOException {
        GameContent gameContent = new GameContent(playerdataFile);
        boolean userPlayerResult = gameContent.getRandByUserPlayerStat(line, 3);
        boolean botPlayerResult = gameContent.getRandByBotPlayerStat(line, 3);
        if (userPlayerResult && !botPlayerResult) {
            System.out.println("카정을 하고 있습니다. 상대 정글이 주시를 해야할 것 같습니다.\n");
            return 2;
        } else if (!userPlayerResult && botPlayerResult) {
            System.out.println("아 카정을 하다가 죽어버렸습니다. 타격이 큰데요...\n");
            return -4;
        } else return 0;
    }
    public int narationRoamingJungle(int line) throws IOException {
        GameContent gameContent = new GameContent(playerdataFile);
        boolean userPlayerResult = gameContent.getRandByUserPlayerStat(line, 4);
        boolean botPlayerResult = gameContent.getRandByBotPlayerStat(line, 4);
        if (userPlayerResult && !botPlayerResult) {
            System.out.println("갱을 가서 성공적으로 상대를 죽였습니다! 정글 아주 좋습니다.\n");
            return 5;
        } else if (!userPlayerResult && botPlayerResult) {
            System.out.println("갱을 가서 상대 라이너에게 죽었네요. 정글에 큰 타격이 있겠는데요...\n");
            return -3;
        } else {
            System.out.println("갱을 가서 미미한 효과를 보였네요.\n");
            return -2;
        }
    }
    public int narationRoaming(int line) throws IOException {
        GameContent gameContent = new GameContent(playerdataFile);
        boolean userPlayerResult = gameContent.getRandByUserPlayerStat(line, 4);
        boolean botPlayerResult = gameContent.getRandByBotPlayerStat(line, 4);
        if (userPlayerResult && !botPlayerResult) {
            System.out.println("로밍가서 성공했습니다! 아주 좋습니다.\n");
            return 4;
        } else if (!userPlayerResult && botPlayerResult) {
            System.out.println("로밍가서 상대 라이너에게 죽었네요. 라인에 큰 타격이 있겠는데요...\n");
            return -5;
        } else {
            System.out.println("로밍가서 미미한 효과를 보였네요.\n");
            return -4;
        }
    }
    public int narationLine(int line) throws IOException {
        GameContent gameContent = new GameContent(playerdataFile);
        boolean userPlayerResult = gameContent.getRandByUserPlayerStat(line, 3);
        boolean botPlayerResult = gameContent.getRandByBotPlayerStat(line, 3);
        if (userPlayerResult && !botPlayerResult) {
            System.out.println("라인전에서 이겼습니다!\n");
            return 2;
        } else if (!userPlayerResult && botPlayerResult) {
            System.out.println("라인전에서 졌습니다.\n");
            return -3;
        } else {
            System.out.println("서로 둘이 팽팽합니다.\n");
            return 0;
        }
    }
    public void ingame() throws IOException, InterruptedException {
        GameContent gameContent = new GameContent(playerdataFile);
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("소환사의 협곡에 오신 것을 환영합니다.");
        System.out.println("*********************************************");
        showImformation();
        // drackKind: 1 = 바람용, 2 = 대지용, 3 = 화염용, 4 = 물용, 5 = 장로 
        int line = 0;  int time = 0; int drackKind = 0; int baron = 0; int drackTime = 0;
        int userWin = 0; int botWin = 0;
        // Mob ArrayList index: 0 = 바론, 1 = 바람용, 2 = 대지용, 3 = 화염용, 4 = 물용, 5 = 장로
        while (true) {
            if (userWin < 0) {
                userWin = 0;
            }
            if (botWin < 0) {
                userWin = 0;
            }
            if (userWin >= 40 && botWin < 40) {
                System.out.println("승리!");
                break;
            } 
            if (userWin < 40 && botWin >= 40) {
                System.out.println("패배!");
                break;
            }
            
            int tmpUserWin = userWin;
            int tmpBotWin = botWin;
                if(time != 0) {
                    drackTime = time;
                    if(time >= 5) {
                        drackKind = gameContent.getRandByDrackKind();
                        //narationDrackShow(drackKind);
                    }
                }
                if (line == 5) line = 0;
                boolean userPlayerResult = gameContent.getRandByUserPlayerStat(line, 3);
                boolean botPlayerResult = gameContent.getRandByBotPlayerStat(line, 3);
                int actionLine = gameContent.getRandByStatL();
                int actionJungle = gameContent.getRandByStatJ();
                if (userPlayerResult && !botPlayerResult) {
                    System.out.println("[경기 시간]" + time + "분");
                    switch(line) {
                        case 0:
                            System.out.println("탑에서 감독님의 팀 ");
                            narationPlayer(line);
                            switch(actionLine) {
                                case 3:
                                    userWin += narationLine(line);
                                    break;
                                case 4:
                                    userWin += narationRoaming(line);
                                    break;
                            }
                            break;
                        case 1:
                            System.out.println("미드에서 감독님의 팀 ");
                            narationPlayer(line);
                            switch(actionLine) {
                                case 3:
                                    userWin += narationLine(line);
                                    break;
                                case 4:
                                    userWin += narationRoaming(line);
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("봇에서 감독님의 팀 ");
                            narationPlayer(line);
                            switch(actionLine) {
                                case 3:
                                    userWin += narationLine(line);
                                    break;
                                case 4:
                                    userWin += narationRoaming(line);
                                    break;
                            }
                            break;
                        case 3:
                            System.out.println("봇에서 감독님의 팀 ");
                            narationPlayer(line);
                            switch(actionLine) {
                                case 3:
                                    userWin += narationLine(line);
                                    break;
                                case 4:
                                    userWin += narationRoaming(line);
                                    break;
                            }
                            break;
                        case 4:
                            System.out.println("정글에서 감독님의 팀 ");
                            narationPlayer(line);
                            switch(actionJungle) {
                                case 3:
                                    userWin += narationCounterJungle(line);
                                    break;
                                case 4:
                                    userWin += narationRoamingJungle(line);
                                    break;
                                case 5:
                                    userWin += narationRoamingJungle(line);
                                    break;
                                case 6:
                                    if(time>=20) {
                                        userPlayerResult = gameContent.getRandByUserPlayerStat(line, 6);
                                        botPlayerResult = gameContent.getRandByBotPlayerStat(line, 6);
                                        if (userPlayerResult && !botPlayerResult) {
                                            System.out.print("바론을 먹었습니다. 이기나요?\n");
                                            baron = 0;
                                            userWin += 8;
                                        } else if (!userPlayerResult && botPlayerResult){
                                            System.out.print("바론을 시도하다가 빼앗겼습니다. 타격이 너무 큽니다...\n");
                                            userWin -= 8;
                                            if(userWin<tmpUserWin) botWin += tmpUserWin-userWin;
                                        }
                                        break;
                                    }
                                    userWin += narationDrackTry(drackKind, line);
                                    break;
                            }
                            break;
                    }
                } else if (!userPlayerResult && botPlayerResult) {
                    System.out.println("[경기 시간]" + time + "분");
                    switch(line) {
                        case 0:
                        System.out.println("탑에서 상대 팀 ");
                        narationPlayer(line);
                            switch(actionLine) {
                                case 3:
                                    botWin += narationLine(line);
                                    break;
                                case 4:
                                    botWin += narationRoaming(line);
                                    break;
                            }
                            break;
                        case 1:
                        System.out.println("미드에서 상대 팀 ");
                        narationPlayer(line);
                            switch(actionLine) {
                                case 3:
                                    botWin += narationLine(line);
                                    break;
                                case 4:
                                    botWin += narationRoaming(line);
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("봇에서 상대 팀 ");
                            narationPlayer(line);
                            switch(actionLine) {
                                case 3:
                                    botWin += narationLine(line);
                                    break;
                                case 4:
                                    botWin += narationRoaming(line);
                                    break;
                            }
                            break;
                        case 3:
                            System.out.println("봇에서 상대 팀 ");
                            narationPlayer(line);
                            switch(actionLine) {
                                case 3:
                                    botWin += narationLine(line);
                                    break;
                                case 4:
                                    botWin += narationRoaming(line);
                                    break;
                            }
                            break;
                        case 4:
                            switch(actionJungle) {
                                case 3:
                                    botWin += narationCounterJungle(line);
                                    break;
                                case 4:
                                    botWin += narationRoamingJungle(line);
                                    break;
                                case 6:
                                    if(time>=20) {
                                        userPlayerResult = gameContent.getRandByUserPlayerStat(line, 6);
                                        botPlayerResult = gameContent.getRandByBotPlayerStat(line, 6);
                                        if (userPlayerResult && !botPlayerResult) {
                                            System.out.print("바론을 먹었습니다. 이기나요?\n");
                                            baron = 0;
                                            botWin += 8;
                                        } else if (!userPlayerResult && botPlayerResult){
                                            System.out.print("바론을 시도하다가 빼앗겼습니다. 타격이 너무 큽니다...\n");
                                            botWin -= 8;
                                            if(botWin<tmpBotWin) userWin += tmpBotWin-botWin; // 역전 기회 노림
                                        }
                                        break;
                                    }
                                    botWin += narationDrackTry(drackKind, line);
                                    break;
                            }
                        break;
                    }
                }
            
            line++;
            
            time += 1;
            if(botWin<tmpBotWin) userWin += tmpBotWin-botWin;
            if(userWin<tmpUserWin) botWin += tmpUserWin-userWin;
            if((time-drackTime) == 5 && drackKind == 0) {
                drackKind = gameContent.getRandByDrackKind();
                //narationDrackShow(drackKind);
            }
            if(userWin == tmpUserWin && botWin == tmpBotWin) {
                continue;
            } else {
                Thread.sleep(3000);
                System.out.print("감독님 팀 점수 : " + userWin); System.out.println(" 상대 팀 점수 : " + botWin);
            }
        }
        // TODO 현재 나레이션 구성하고 break해야함
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