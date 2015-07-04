package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import bg.ittalents.traffichero.main.ButtonManager;
import bg.ittalents.traffichero.main.UpdateUserPassword;


public class SettingsScreen implements Screen {
    Stage stage;

    ButtonManager buttonManager = new ButtonManager();
    Table table = new Table();
    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table.add(buttonManager.settingsScreenButtons("Change Password")).size(UpdateUserPassword.DEFAULT_BUTTON_WIDTH / 2).pad(UpdateUserPassword.DEFAULT_PADDING);
        table.add(buttonManager.settingsScreenButtons("Change Nickname")).size(UpdateUserPassword.DEFAULT_BUTTON_WIDTH / 2).pad(UpdateUserPassword.DEFAULT_PADDING).row();
        table.add(buttonManager.settingsScreenCheckBoxes("Send email if somebody beat your HeightScore?")).pad(UpdateUserPassword.DEFAULT_PADDING).row();
        table.add(buttonManager.settingsScreenCheckBoxes("Sounds Effect")).size(200,50).pad(UpdateUserPassword.DEFAULT_PADDING).row();
        table.add(buttonManager.settingsScreenCheckBoxes("Background music")).size(200, 50).
        pad(UpdateUserPassword.DEFAULT_PADDING).row();
        table.setFillParent(true);
        table.debug();


        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

    }

    @Override
    public void dispose() {

    }
}
