package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import bg.ittalents.traffichero.main.Constants;

public class SplashScreen implements Screen {
    private Stage stage = new Stage();
    private Image img;
    private Label heading;
    private BitmapFont black;
    private Texture splashTexture;


    // Sound sound ;
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {


        black = new BitmapFont(Gdx.files.internal("fonts/black.fnt"), false);

        Label.LabelStyle headingStyle = new Label.LabelStyle(black, Color.GREEN);


        heading = new Label("ANDROID", headingStyle);
        heading.setPosition(Gdx.graphics.getWidth() / 2 - heading.getWidth() / 2, 20);
        splashTexture = new Texture("android-splash.jpg");
        img = new Image(splashTexture);
        img.setPosition(0, 0);
        img.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        img.setOrigin(img.getWidth()/ 2, img.getWidth() / 2);
        img.addAction(Actions.sequence(Actions.rotateBy(-5f, 0.05f), Actions.rotateBy(10f, 0.10f), Actions.rotateBy(-5f, 0.05f),
                Actions.rotateBy(-5f, 0.05f), Actions.rotateBy(10f, 0.10f), Actions.rotateBy(-5f, 0.05f), Actions.fadeOut(1.0f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new LoginScreen());
                    }
                })));

        stage.addActor(heading);

        stage.addActor(img);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }

}