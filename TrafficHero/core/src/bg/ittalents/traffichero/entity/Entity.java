package bg.ittalents.traffichero.entity;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor implements IEntity {

    protected Rectangle bounds;
    protected Texture texture;

    Entity(Texture texture) {
        this.texture = texture;
        bounds = new Rectangle();
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.texture, this.getX(), this.getY(), this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight(), this.getScaleX(), this.getScaleY(), getRotation(), 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        update();
    }
}
