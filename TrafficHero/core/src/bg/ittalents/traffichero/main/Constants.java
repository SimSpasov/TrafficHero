package bg.ittalents.traffichero.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class Constants {

    public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    public static final int PLAYER_STEERING_RATE = SCREEN_WIDTH / 70;
    public static final int ENEMY_MIN_MOVE_RATE = SCREEN_HEIGHT / 90;
    public static final int ENEMY_MAX_MOVE_RATE = SCREEN_HEIGHT / 70;
    public static final float NEAR_MISS_DISTANCE = SCREEN_WIDTH / 12;
    public static final int BACKGROUND_SPEED = Gdx.graphics.getHeight() / 130;
    public static final float[] ENEMY_SPAWN_POINT = {SCREEN_WIDTH / 16.0f, SCREEN_WIDTH / 3.33f, SCREEN_WIDTH / 1.88f, SCREEN_WIDTH / 1.28f};
    public static final float BACKGROUND_COPY_POSITION = 2048 / 1.15f;
    public static final float ENEMY_SPAWN_RATE = 1.2f;
    public static final float PAUSE_WINDOW_WIDTH = Gdx.graphics.getWidth() / 3;
    public static final float PAUSE_WINDOW_HEIGHT = Gdx.graphics.getWidth() / 5;
    public static final float PAUSE_BUTTON_WIDTH = Gdx.graphics.getWidth() / 5;
    public static final float PAUSE_BUTTON_HEIGHT = Gdx.graphics.getWidth() / 10;
    public static final int PAUSE_WINDOW_WIDTH_DIVIDER = 2;
    public static final float END_GAME_WINDOW_WIDTH_DIVIER = 1.2f;
    public static final float END_GAME_WINDOW_HEIGHT_DIVIER = 3;
    public static final int MAX_ARMOR = 8;
    public static final int CAR_CRASH_ROTATE_ANGLE = 25;
    public static final int COIN_PICKUP_BONUS = 100;
    public static final int ENEMY_MISS_BONUS = 50;
    public static final int END_GAME_ARMOR_BONUS_MULTIPLIER = 200;
    public static final int BIG_SCREEN_FONT_CRITERIA = 1000;
    public static final int MEDIUM_SCREEN_FONT_CRITERIA = 1000;
    public static final float RDY_TEXTURE_WIDTH = Gdx.graphics.getWidth() / 1.5f;
    public static final float RDY_TEXTURE_HEIGHT = Gdx.graphics.getWidth() / 1.5f / 1.35f;
    public static final float PLAYER_TEXTURE_WIDTH_DIVIDER = 7;
    public static final float PLAYER_TEXTURE_HEIGHT_DIVIDER = 3.5f;
    public static final float PLAYER_TEXTURE_Y_POSITION_DIVIDER = 20;
    public static final float PLAYER_TEXTURE_X_POSITION_DIVIER = 2;
    public static final float PLAYER_X_MOVE_BORDER = Gdx.graphics.getWidth() / 40;
    public static final int SCREEN_HALF = Gdx.graphics.getWidth() / 2;
    public static final float BACKGROUND_OVERLAP_DIVIDER = 1.15f;
    public static final float COIN_SIZE_DIVIDER = 13;
    public static final float COIN_SPAWN_BORDER_LEFT = Gdx.graphics.getWidth() / 20;
    public static final float COIN_SPAWN_BORDER_RIGHT = Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 6;

    public static final float DEFAULT_PADDING = SCREEN_WIDTH/40;
    public static final float DEFAULT_BUTTON_HEIGHT = SCREEN_HEIGHT/13;
    public static final float CUSTOM_BUTTON_HEIGHT = SCREEN_HEIGHT/8;
    public static final float DEFAULT_TEXT_FIELD_HEIGHT = SCREEN_HEIGHT/11;
    public static final float DEFAULT_BUTTON_WIDTH = SCREEN_WIDTH / 2.27f;
    public static final float DEFAULT_TEXT_FIELD_WIDTH = SCREEN_WIDTH / 1.5f;
    public static final int MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGTH = 2;
    public static final Skin skin = new Skin(Gdx.files.internal("data/uiskin50.json"));
    public static final int MAX_PASSWORD_USERNAME_AND_NICKNAME_LENGTH = 10;
    public static final int DEFAULT_ALPHA = 55;
    public static final int CUSTOM_ALPHA = 80;
    public static final Texture backgroundTexture = new Texture(Gdx.files.internal("backgroundRoad.jpg"));


}
