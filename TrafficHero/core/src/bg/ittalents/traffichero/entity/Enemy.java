package bg.ittalents.traffichero.entity;

import com.badlogic.gdx.math.MathUtils;
import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextureManager;

public class Enemy extends Entity {

    private int spawnPosition;
    private int lastSpawnPosition;
    private boolean nearMissed = false;

    public Enemy() {
        super(TextureManager.ENEMY[MathUtils.random(0, TextureManager.ENEMY.length - 1)]);
        setWidth(Constants.SCREEN_WIDTH / Constants.PLAYER_TEXTURE_WIDTH_DIVIDER);
        setHeight(Constants.SCREEN_WIDTH / Constants.PLAYER_TEXTURE_HEIGHT_DIVIDER);
        spawnPosition = MathUtils.random(0, Constants.ENEMY_SPAWN_POINT.length - 1);
        if (spawnPosition == lastSpawnPosition) {
            spawnPosition = MathUtils.random(0, Constants.ENEMY_SPAWN_POINT.length - 1);
        }
        lastSpawnPosition = spawnPosition;
        setPosition(Constants.ENEMY_SPAWN_POINT[spawnPosition], Constants.SCREEN_HEIGHT);
    }

    public void update() {
        setY(getY() - MathUtils.random(Constants.ENEMY_MIN_MOVE_RATE, Constants.ENEMY_MAX_MOVE_RATE));
    }

    public void setNearMissed() {
        nearMissed = true;
    }

    public boolean getNearMissed() {
        return nearMissed;
    }
}
