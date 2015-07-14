package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import bg.ittalents.traffichero.main.ButtonManager;
import bg.ittalents.traffichero.main.Constants;


public class ChangeProfileScreen implements Screen {
    private Stage stage;
    private ButtonManager buttonManager = new ButtonManager();
    private Table table = new Table();
    private SpriteBatch batch;
    public static Label statusLabel;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        batch = new SpriteBatch();

        statusLabel = new Label("", Constants.skin);
        Table newTable = new Table();
        newTable.add(statusLabel).row();
        newTable.add(buttonManager.settingsScreenButtons("Change Password")).size(Constants.DEFAULT_BUTTON_WIDTH / 1.5f).pad(Constants.DEFAULT_PADDING);
        newTable.add(buttonManager.settingsScreenButtons("Change Nickname")).size(Constants.DEFAULT_BUTTON_WIDTH / 1.5f).pad(Constants.DEFAULT_PADDING).row();
        table.add(newTable).row();
        table.add(buttonManager.settingsScreenCheckBoxes("emailCheckbox")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH,
                Constants.DEFAULT_TEXT_FIELD_HEIGHT).pad(Constants.DEFAULT_PADDING).row();

        table.add(buttonManager.settingsScreenCheckBoxes("backgroundCheckbox")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH,
                Constants.DEFAULT_TEXT_FIELD_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.setFillParent(true);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(Constants.backgroundTexture, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
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