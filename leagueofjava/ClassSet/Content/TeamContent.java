package ClassSet.Content;

import ClassSet.ReadWrite.RW;

public class TeamContent extends RW{
    private String TeamList = "";
    File file;

    public TeamContent(File file) {
        super(file);
        this.file = file;
        TeamList = "";
    }

    public void loadUserList() {
        loadContent(file);
        TeamList = getContent();
    }

    public String getTeamList() {
        return TeamList;
    }
}