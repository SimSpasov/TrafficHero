package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import bg.ittalents.traffichero.main.ButtonManager;
import bg.ittalents.traffichero.main.TextFieldManager;
import bg.ittalents.traffichero.main.UpdateUserPassword;


public class ChangeNicknameScreen implements Screen {
    private Stage stage;

    @Override
    public void show() {
        TextFieldManager fieldManager = new TextFieldManager();
        stage = new Stage();
        Label field = new Label("", ButtonManager.skin);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.add(fieldManager.changeNicknameScreenTextFields("New Nickname")).pad(UpdateUserPassword.DEFAULT_PADDING)
                .size(UpdateUserPassword.DEFAULT_TEXT_FIELD_WIDTH, UpdateUserPassword.DEFAULT_TEXT_FIELD_HEIGHT).row();
        table.add(fieldManager.changeNicknameScreenTextFields("repeat Nickname")).pad(UpdateUserPassword.DEFAULT_PADDING)
                .size(UpdateUserPassword.DEFAULT_TEXT_FIELD_WIDTH, UpdateUserPassword.DEFAULT_TEXT_FIELD_HEIGHT).row();
        table.add(field);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
