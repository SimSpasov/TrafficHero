package bg.ittalents.traffichero.entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextureManager;

public class Background extends Actor {

    Texture texture;


    public Background(float yPos) {
        texture = TextureManager.BACKGROUND;
        setY(yPos);
    }


    public void update() {
        setY(getY() - Constants.BACKGROUND_SPEED);
        if (getY() <= -texture.getHeight() / 1.15f) {
                setY(texture.getHeight() / 1.15f);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
            batch.draw(texture, this.getX(), getY(), Gdx.graphics.getWidth(), texture.getHeight());
            update();

    }
}
