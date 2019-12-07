import Interface.IFRW;
import ClassSet.ReadWriter.RW;

import java.io.File;
import java.util.Random;

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
            int cmd=0;
            frame.menu();
            switch (cmd) {
                case 1:
                    frame.mypage();
                    break;
                case 2:
                    frame.shop();
                    break;
                case 3:
                    frame.game();
                    break;
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