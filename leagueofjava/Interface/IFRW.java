package Interface;
import java.io.File;

public interface IFRW {
    abstract String getContent();//상점, 마이페이지, 인게임 정보 가져오는데 활용
    abstract void writeContent(File file, String inContentString);
    abstract public void loadContent(File file);
}