package bg.ittalents.traffichero.entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextureManager;


public class Player extends Entity {


    public Player() {
        super(TextureManager.PLAYER);
        setWidth(Constants.SCREEN_WIDTH / Constants.PLAYER_TEXTURE_WIDTH_DIVIDER);
        setHeight(Constants.SCREEN_WIDTH / Constants.PLAYER_TEXTURE_HEIGHT_DIVIDER);
        setPosition(Constants.SCREEN_WIDTH / Constants.PLAYER_TEXTURE_X_POSITION_DIVIER - getWidth() / Constants.PLAYER_TEXTURE_X_POSITION_DIVIER,
                Constants.SCREEN_HEIGHT / Constants.PLAYER_TEXTURE_Y_POSITION_DIVIDER);
    }


    public void update() {
        //Handle player moving
        if (Gdx.input.isTouched()) {

            int x = Gdx.input.getX();

            if (x < Constants.SCREEN_HALF && getX() > Constants.PLAYER_X_MOVE_BORDER) {
                setX(getX() - Constants.PLAYER_STEERING_RATE);
            } else if (x > Constants.SCREEN_HALF && getX() < Constants.SCREEN_WIDTH - getWidth() - Constants.PLAYER_X_MOVE_BORDER) {
                setX(getX() + Constants.PLAYER_STEERING_RATE);
            }
        }
    }


}
