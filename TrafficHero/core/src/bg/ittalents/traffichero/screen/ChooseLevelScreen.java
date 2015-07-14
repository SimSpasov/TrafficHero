package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import bg.ittalents.traffichero.main.ButtonManager;
import bg.ittalents.traffichero.main.Constants;

public class ChooseLevelScreen implements Screen {

    private Stage stage;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    @Override
    public void show() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        batch = new SpriteBatch(Constants.SCREEN_HEIGHT);
        backgroundTexture = new Texture(Gdx.files.internal("backgroundLamborgini.jpg"));

        Label statusLabel = new Label("", Constants.skin);

        Table table;
        BitmapFont white;
        Label heading;
        //Creating bitmapFonts and add the fnt files from assets to them..
        white = new BitmapFont(Gdx.files.internal("data/50white.fnt"),false);

        Label.LabelStyle headingStyle = new Label.LabelStyle(white, Color.ORANGE);
        heading = new Label("TRAFFIC HERO 2D",headingStyle);


        table = new Table();
        table.setFillParent(true);

        ButtonManager buttonManager = new ButtonManager();
        table.setFillParent(true);
        table.add(statusLabel).row();
        table.add(heading).pad(30).row();
        table.add(buttonManager.levelButtons("Level 1")).size(Constants.DEFAULT_BUTTON_WIDTH,
                Constants.DEFAULT_BUTTON_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.add(buttonManager.levelButtons("Level 2")).size(Constants.DEFAULT_BUTTON_WIDTH,
                Constants.DEFAULT_BUTTON_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.add(buttonManager.levelButtons("Level 3")).size(Constants.DEFAULT_BUTTON_WIDTH,
                Constants.DEFAULT_BUTTON_HEIGHT).pad(Constants.DEFAULT_PADDING).row();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();


        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new MainScreen());
        }
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}