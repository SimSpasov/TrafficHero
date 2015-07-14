package bg.ittalents.traffichero.entity;

import com.badlogic.gdx.math.MathUtils;

import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextureManager;


public class Coin extends Entity {


    public Coin() {
        super(TextureManager.COIN);
        setWidth(Constants.SCREEN_WIDTH / Constants.COIN_SIZE_DIVIDER);
        setHeight(Constants.SCREEN_WIDTH / Constants.COIN_SIZE_DIVIDER);
        setPosition(MathUtils.random(Constants.COIN_SPAWN_BORDER_LEFT, Constants.COIN_SPAWN_BORDER_RIGHT), Constants.SCREEN_HEIGHT);
    }

    @Override
    public void update() {
        setY(getY() - Constants.BACKGROUND_SPEED);
    }
}
