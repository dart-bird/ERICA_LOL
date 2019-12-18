package Interface;

import java.io.File;

public interface IFReader {
    abstract String getContent();//상점, 마이페이지, 인게임 정보 가져오는데 활용
    abstract public void loadContent(File file);
}