package ClassSet.MFrame;
public class Mframe {
    public Mframe() {
    }
    public void menu(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*******************************");
        System.out.println("1. My Page 2. Shop Page 3. Game");
        System.out.print("Enter Number:");
    }
    public void mypage(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*******************************");
        System.out.println("My Page");
        System.out.println("*******************************");
        System.out.print("ID:"); //get id
        System.out.print(" GOLD:"); //get gold
        System.out.println(" EXP:"); //get exp
        System.out.println("1. Menu");
        System.out.print("Enter Number:");
    }
    public void shop(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*******************************");
        System.out.println("Welcome to LOL shop!");
        System.out.println("*******************************");
        System.out.print("Player"); //get id
        System.out.println("1. Menu");
        System.out.print("Enter Number:");
    }
    public void game(){
        System.out.print("\033[H\033[2J"); //clear console / ubuntu
        System.out.println("*******************************");
        System.out.println("Game Menu");
        System.out.println("*******************************");
        System.out.print("ID:"); //get id
        System.out.print(" GOLD:"); //get gold
        System.out.println(" EXP:"); //get exp
        System.out.print("1. Menu ");
        System.out.print("2. Start Game\n");
        System.out.print("Enter Number:");
    }
}