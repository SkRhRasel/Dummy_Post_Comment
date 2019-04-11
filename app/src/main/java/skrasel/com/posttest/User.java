package skrasel.com.posttest;

public class User {

    public static final String TABLE_NAME = "notes";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_COMMENTS = "user_comments";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + USER_NAME + " TEXT,"
                    + USER_COMMENTS + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";


    private int id;
    private String name;
    private String proPic;
    private String comment;
    private String timeStamp;


    public String getName() {
        return name;
    }

    public void setName(String appName) {
        this.name = appName;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
