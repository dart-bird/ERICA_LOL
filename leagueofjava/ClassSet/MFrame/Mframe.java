package ClassSet.MFrame;

import java.util.Scanner;

public class Mframe {
    public int cmd;
    Scanner scanner = new Scanner(System.in);
    public Mframe() {
        cmd = 0;
    }
    public void menu(){
        cmd = 0;
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("********************************************");
        System.out.println("1. 내 선수단 관리 2. 이적시장 3. 게임 4. 종료");
        System.out.println("********************************************");
        System.out.print("숫자를 입력하세요:");
    }
    public void mypage(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("********************************************");
        System.out.println("내 선수단 관리");
        System.out.println("********************************************");
        System.out.print("ID:"); //get id
        System.out.print(" GOLD:"); //get gold
        System.out.println(" EXP:"); //get exp
        System.out.println("1. 메뉴");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
    }
    public void shop(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("********************************************");
        System.out.println("이적시장");
        System.out.println("********************************************");
        System.out.print("Player"); //get id
        System.out.println("");
        System.out.println("1. 메뉴");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
    }
    public void game(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("********************************************");
        System.out.println("게임 메뉴");
        System.out.println("********************************************");
        System.out.print("ID:"); //get id
        System.out.print(" GOLD:"); //get gold
        System.out.println(" EXP:"); //get exp
        System.out.print("1. 메뉴 ");
        System.out.print("2. 게임 시작\n");
        System.out.print("숫자를 입력하세요:");
        cmd = scanner.nextInt();
    }
    public void ingame(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("********************************************");
        System.out.println("소환사의 협곡에 오신 것을 환영합니다.");
        System.out.println("********************************************");
        System.out.print("ID:"); //get id
        System.out.print(" GOLD:"); //get gold
        System.out.println(" EXP:"); //get exp
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