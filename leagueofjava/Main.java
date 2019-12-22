import java.io.IOException;
import java.util.Scanner;

import ClassSet.MFrame.Mframe;

public class Main{
    public static void main(String[] args) throws InterruptedException, IOException{
        Mframe frame = new Mframe();
        String cmd;
        while(true){
                frame.menu();
                Scanner scanner = new Scanner(System.in);
                cmd = scanner.next();
                switch (cmd) {
                    case "1":
                        while(!frame.getCmd().equals("1")) {
                            frame.mypage();
                            if(frame.getCmd().equals("2")) {
                                frame.mypage_2();
                            }
                        }
                        break;
                    case "2":
                        while(!frame.getCmd().equals("1")) {
                            frame.shop();
                            if(frame.getCmd().equals("2")) {
                                while(!frame.getCmd().equals("1")) frame.shop_result();
                            }
                        }
                        break;
                    case "3":
                        while(!frame.getCmd().equals("1")) {
                            frame.game();
                            if(frame.getCmd().equals("2")) {
                                while(!frame.getCmd().equals("1")) frame.ingame();
                            }

                        }
                        break;
                    case "4":
                        System.out.println("안녕히가십쇼. 감독님.");
                        scanner.close();
                        System.exit(0);
                    default:
                        continue;
                        
                }
        }
         
    }
}