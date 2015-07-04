package bg.ittalents.traffichero.entity;

import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextureManager;


public class Coin extends Entity {


    public Coin() {
        super(TextureManager.COIN);
        setWidth(Constants.SCREEN_WIDTH / 15);
        setHeight(Constants.SCREEN_WIDTH / 15);
        setPosition(Constants.SCREEN_WIDTH /2, Constants.SCREEN_HEIGHT);
    }

    @Override
    public void update() {
        setY(getY() - Constants.BACKGROUND_SPEED);
    }
}
