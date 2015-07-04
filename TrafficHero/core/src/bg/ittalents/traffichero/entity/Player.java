package bg.ittalents.traffichero.entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextureManager;


public class Player extends Entity {

    Sprite sprite;



    public Player() {
        super(TextureManager.PLAYER);
        setWidth(Constants.SCREEN_WIDTH / 6);
        setHeight(Constants.SCREEN_WIDTH / 3);
        setPosition(Constants.SCREEN_WIDTH / 2 - getWidth() / 2, 0);
    }


    public void update() {

        if (Gdx.input.isTouched()) {

            int x = Gdx.input.getX();

            if (x < Constants.SCREEN_WIDTH / 2 && getX() > Constants.SCREEN_WIDTH/40) {
                setX(getX() - Constants.PLAYER_STEERING_RATE);
            } else if (x > Constants.SCREEN_WIDTH / 2 && getX() < Constants.SCREEN_WIDTH - getWidth() - Constants.SCREEN_WIDTH/40) {
                setX(getX() + Constants.PLAYER_STEERING_RATE);
            }
        }
    }


}
