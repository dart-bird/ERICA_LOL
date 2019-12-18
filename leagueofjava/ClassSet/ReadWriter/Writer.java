package ClassSet.ReadWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Interface.IFWriter;

public class Writer implements IFWriter{
    File file;

    public Writer(File file2) {
        this.file = file2;
    }

    @Override
    public void writeContent(File file, String inContentString, boolean append) {
        // TODO 파일 쓰기 목표 잡아야함
        try {
            //파일에 문자열을 쓴다
            //하지만 파일이 존재한다면 덮어쓰는게 아니라 .
            //그 뒤에 문자열을 이어서 작성한다.
            FileWriter file_ = new FileWriter(file, append);
            file_.write(inContentString);
            file_.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}