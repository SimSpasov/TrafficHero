package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import bg.ittalents.traffichero.main.ButtonManager;
import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextFieldManager;

public class LoginScreen implements Screen,Input.TextInputListener{
    ButtonManager buttonManager = new ButtonManager();
    private Stage stage;
    private Table table;
    private Label heading;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private static Music backgroundMusic;
    private static boolean onlyOnce = true;
    public static Label statusLabel = new Label("",Constants.skin);
    private TextFieldManager tm = new TextFieldManager();

    public LoginScreen() {}

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        heading = new Label("TRAFFIC HERO 2D", new Label.LabelStyle(new BitmapFont(Gdx.files.internal("data/50white.fnt")), Color.WHITE));
        table = new Table(Constants.skin);
        table.setFillParent(true);
        stage.addActor(table);

        mainTable();

        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("backgroundRoad.jpg"));

    }


    //    Creating moving background
    public void backgroundMusic() {
        setBackgroundMusic(Gdx.audio.newMusic(Gdx.files.internal("sounds/backgroundMusic.mp3")));
        getBackgroundMusic().setLooping(true);
        getBackgroundMusic().play();
    }

    public void mainTable(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Table newTable = new Table();
        Table table2 = new Table();
        newTable.add(statusLabel).row();
        newTable.add(heading).pad(25).row();
        newTable.add(tm.loginScreenTextFields("usernameLogin")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH,
                Constants.DEFAULT_TEXT_FIELD_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        newTable.add(tm.loginScreenTextFields("passwordLogin")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH,
                Constants.DEFAULT_TEXT_FIELD_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table2.add(buttonManager.loginButtons("loginButton")).size(Constants.DEFAULT_BUTTON_WIDTH,
                Constants.DEFAULT_BUTTON_HEIGHT).pad(Constants.DEFAULT_PADDING);
        table2.add(buttonManager.loginButtons("registrationButton")).size(Constants.DEFAULT_BUTTON_WIDTH,
                Constants.DEFAULT_BUTTON_HEIGHT).pad(Constants.DEFAULT_PADDING);
        newTable.add(table2).row();
        newTable.add(buttonManager.loginButtons("playOffline")).size(Constants.DEFAULT_BUTTON_WIDTH,
                Constants.DEFAULT_BUTTON_HEIGHT).pad(Constants.DEFAULT_PADDING);
        newTable.setFillParent(true);
        if(onlyOnce) {
            onlyOnce = false;
            backgroundMusic();
        }
        stage.addActor(newTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        batch.begin();
        batch.draw(backgroundTexture,0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}
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
    @Override
    public void input(String text) {}
    @Override
    public void canceled() {}

    public static Music getBackgroundMusic() {
        return backgroundMusic;
    }
    public static void setBackgroundMusic(Music backgroundMusic) {
        LoginScreen.backgroundMusic = backgroundMusic;
    }


}