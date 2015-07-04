package bg.ittalents.traffichero.main;

import com.badlogic.gdx.Gdx;

public abstract class Constants {

    public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    public static final int PLAYER_STEERING_RATE = SCREEN_WIDTH / 70;
    public static final int ENEMY_MIN_MOVE_RATE = SCREEN_HEIGHT / 110;
    public static final int ENEMY_MAX_MOVE_RATE = SCREEN_HEIGHT / 90;
    public static final int NEAR_MISS_DISTANCE = SCREEN_WIDTH / 9;
    public static final int BACKGROUND_SPEED = Gdx.graphics.getHeight() / 150;
    public static final float[] ENEMY_SPAWN_POINT = {SCREEN_WIDTH / 16.0f, SCREEN_WIDTH / 3.33f, SCREEN_WIDTH / 1.88f, SCREEN_WIDTH / 1.28f};

}
