package ClassSet.Content;

import java.io.File;
import ClassSet.ReadWriter.RW;

public class UserContent extends RW {
    private String UserList = "";
    File file;

    public UserContent(File file) {
        super(file);
        this.file = file;
        UserList = "";

    }

    public void loadUserList() {
        loadContent(file);
        UserList = getContent();
    }

    public String getUserList() {
        return UserList;
    }
}