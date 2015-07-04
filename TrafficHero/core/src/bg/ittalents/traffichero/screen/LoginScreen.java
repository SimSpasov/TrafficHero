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
import bg.ittalents.traffichero.main.TextFieldManager;
import bg.ittalents.traffichero.main.UpdateUserPassword;

public class LoginScreen implements Screen,Input.TextInputListener{
    ButtonManager buttonManager = new ButtonManager();
    private Stage stage;
    private Table table;
    private Label heading;
    private SpriteBatch batch;
    private Sprite background;
    private float time = 0.0f;
    private Sound clickSound;
    private boolean isTrue = true;
    private Music backgroundMusic;
    public LoginScreen() {
    }
    @Override
    public void show() {
         stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);



        heading = new Label("TITLE", new Label.LabelStyle(new BitmapFont(Gdx.files.internal("fonts/white.fnt")), Color.WHITE));
        table = new Table(ButtonManager.skin);
        table.setFillParent(true);
        if(isTrue){
            isTrue = false;
            mainTable();
        }
        stage.addActor(table);
//        Creating moving background
        batch = new SpriteBatch();
        Texture texture = new Texture("bg-road.png");
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        background = new Sprite(texture);
        background.setAlpha(100);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }
    private void backgroundMusic() {
////        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav.wav"));
//       if(buttonManager.isBackgroundMusicOn()) {
//           backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/backgroundMusic.mp3"));
//           backgroundMusic.setLooping(true);
//           backgroundMusic.play();
//       }
    }
TextFieldManager tm = new TextFieldManager();
    public void mainTable(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Table newTable = new Table();
        newTable.add(heading).pad(25).row();
        newTable.add(tm.loginScreenTextFields("usernameLogin")).center().fill(true, false).size(Gdx.graphics.getWidth() / 2f, UpdateUserPassword.DEFAULT_TEXT_FIELD_HEIGHT).pad(UpdateUserPassword.DEFAULT_PADDING).row();
        newTable.add(tm.loginScreenTextFields("passwordLogin")).center().fill(true, false).size(Gdx.graphics.getWidth() / 2f, UpdateUserPassword.DEFAULT_TEXT_FIELD_HEIGHT).pad(UpdateUserPassword.DEFAULT_PADDING).row();
        newTable.add(buttonManager.loginButtons("Login")).size(Gdx.graphics.getWidth() / 3f, UpdateUserPassword.DEFAULT_BUTTON_HEIGHT);
        newTable.add(buttonManager.loginButtons("Registration")).size(Gdx.graphics.getWidth() / 3f, UpdateUserPassword.DEFAULT_BUTTON_HEIGHT);
        newTable.setFillParent(true);
        newTable.debug();
        backgroundMusic();
        stage.addActor(newTable);

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        this.time +=Gdx.graphics.getDeltaTime();

        if(time>1f)
            time = 0.0f;
        background.setV2(time);
        background.setV(time + 2.4f);
        batch.begin();
        background.draw(batch);
        batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {


//        dispose();
    }

    @Override
    public void dispose() {
//        stage.dispose();
//        uiSkin.dispose();

    }
    @Override
    public void input(String text) {}
    @Override
    public void canceled() {}
}