package ClassSet.MFrame;
public class Mframe {
    public Mframe() {
    }
    public void menu(){
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
}