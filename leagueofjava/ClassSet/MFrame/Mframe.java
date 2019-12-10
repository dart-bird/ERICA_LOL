package ClassSet.MFrame;

import ClassSet.Content.UserContent;

import java.io.File;
import java.util.Scanner;

public class Mframe {
    public int cmd; //input int
    File userFile = new File("../leagueofjava/content/data/userdata.gdata"); //userdata dir
    UserContent user1 = new UserContent(userFile); //init usercontent
    Scanner scanner = new Scanner(System.in); //to input int
    public Mframe() {
        cmd = 0;
        //System.out.println(user1.getUserData("gold"));
    }
    private void showImformation(){
        System.out.print("ID:" + user1.getUserData("id")); //get id 
        System.out.print(" GOLD:" + user1.getUserData("gold")); //get gold 
        System.out.println(" EXP:" + user1.getUserData("exp") + "\n"); //get exp
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
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("내 선수단 관리");
        System.out.println("*********************************************");
        showImformation();
        System.out.println("1. 메뉴");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
    }
    public void shop(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("이적시장");
        System.out.println("*********************************************");
        showImformation();
        System.out.print("Player"); //get id
        System.out.println("");
        System.out.println("1. 메뉴");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
    }
    public void game(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*********************************************");
        System.out.println("게임 메뉴");
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