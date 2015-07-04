package bg.ittalents.traffichero.network;



public class User{

    private int id;
    private String nickname;
    private int highScore;
    private int money;
    private int maxlevel;


    public User(int id, String nickname, int highScore, int money, int maxlevel) {
        this.id = id;
        this.nickname = nickname;
        this.highScore = highScore;
        this.money = money;
        this.maxlevel = maxlevel;
    }


}
