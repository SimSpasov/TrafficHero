package bg.ittalents.traffichero.network;



public class User{

    private static User uniqueInstance = null;

    private int id;
    private String nickname;
    private int highScore;
    private int money;
    private int maxlevel;


    private User(int id, String nickname, int highScore, int money, int maxlevel) {
        this.id = id;
        this.nickname = nickname;
        this.highScore = highScore;
        this.money = money;
        this.maxlevel = maxlevel;
    }

    public static User getSingletonUser() {
        if (uniqueInstance != null) {
            return uniqueInstance;
        } else {
            return null;
        }
    }

    public static void makeSingletonUser(int id, String nickname, int highScore, int money, int maxlevel) {
        if (uniqueInstance == null) {
            uniqueInstance = new User(id, nickname, highScore, money, maxlevel);
        }
    }


    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getMoney() {
        return money;
    }

    public int getMaxlevel() {
        return maxlevel;
    }

    public void logout() {
        uniqueInstance = null;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}
