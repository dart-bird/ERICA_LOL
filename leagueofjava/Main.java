import Interface.IFRW;
import ClassSet.ReadWriter.RW;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

import ClassSet.Content.UserContent;
import ClassSet.MFrame.Mframe;
public class Main{
    public static void main(String[] args){

        File userFile = new File("../leagueofjava/content/data/userdata.gdata");
        UserContent user1 = new UserContent(userFile);
        user1.loadUserList();
        System.out.println(user1.getUserList());
        user1.loadDB();
        System.out.println(user1.getUserData("gold"));
        Mframe frame = new Mframe();
        while(true){
            int cmd;
            frame.menu();
            Scanner scanner = new Scanner(System.in);
            cmd = scanner.nextInt();
            switch (cmd) {
                case 1:
                    while(frame.getCmd()!=1) frame.mypage();
                    break;
                case 2:
                    while(frame.getCmd()!=1) frame.shop();
                    break;
                case 3:
                    while(frame.getCmd()!=1) {
                        frame.game();
                        if(frame.getCmd() == 2) {
                            while(frame.getCmd()!=1) frame.ingame();
                        }

                    }
                    break;
                case 4:
                    System.out.println("안녕히가십쇼. 감독님.");
                    System.exit(0);
            }
        }
        /* for(int i =0; i<85; i++){
            System.out.println("");
            double randomValue = Math.random();
            int intValue = (int)(randomValue * 50) + 30;
            double randomValue2 = Math.random();
            int intValue2 = (int)(randomValue2 * 50) + 30;
            double randomValue3 = Math.random();
            int intValue3 = (int)(randomValue3 * 50) + 30;
            double randomValue4 = Math.random();
            int intValue4 = (int)(randomValue4 * 50) + 30;
            System.out.print("/"+intValue+"/"+intValue2+"/"+intValue3+"/"+intValue4);
        } */ //data random
    }
}