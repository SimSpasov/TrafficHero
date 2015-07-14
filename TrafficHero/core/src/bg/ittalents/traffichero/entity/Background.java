package bg.ittalents.traffichero.entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextureManager;

public class Background extends Actor {

    private Texture texture;
    private int speed = Constants.BACKGROUND_SPEED;


    public Background(float yPos) {
        texture = TextureManager.BACKGROUND;
        setY(yPos);
    }


    //Background movement
    public void update() {
        setY(getY() - Constants.BACKGROUND_SPEED);
        if (getY() <= -texture.getHeight() / Constants.BACKGROUND_OVERLAP_DIVIDER) {
                setY(texture.getHeight() / Constants.BACKGROUND_OVERLAP_DIVIDER);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
            batch.draw(texture, this.getX(), getY(), Gdx.graphics.getWidth(), texture.getHeight());
            update();

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
