package Interface;

import java.io.File;

public interface IFWriter {
    abstract void writeContent(File file, String inContentString, boolean continueWrite);
}