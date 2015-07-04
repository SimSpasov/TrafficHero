package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import bg.ittalents.traffichero.main.ButtonManager;
import bg.ittalents.traffichero.main.TextFieldManager;

public class RegistrationScreen implements Screen {
    ButtonManager buttonManager = new ButtonManager();
    static TextFieldManager fieldManager = new TextFieldManager();
    private Stage stage;
    private Skin skin = ButtonManager.skin;
    private Table table;
    private TextButton register = new TextButton("Register", buttonManager.skin);
    private int isfieldValidate;
    @Override
    public void show() {
        final boolean[] isAllValidate = new boolean[1];
        table = new Table(skin);
        stage = new Stage(new StretchViewport(480f, 800f));
        Gdx.input.setInputProcessor(stage);
        final String pattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z{2,4}\0-9-.]+$";
        register.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                TextFieldManager.getCreateUsernameTextField().setColor(Color.BLUE);
                TextFieldManager.getCreateNicknameTextField().setColor(Color.BLUE);
                TextFieldManager.getEmailTextField().setColor(Color.MAGENTA);
                TextFieldManager.getCreatePasswordTextField().setColor(Color.ORANGE);
                TextFieldManager.getRepeatPasswordTextField().setColor(Color.ORANGE);
                isfieldValidate = 0;
                if (!(TextFieldManager.getCreateUsernameTextField().getText().length() >= 5)) {
                    fieldManager.setCreateUsernameInvalidInput();
                    isfieldValidate++;
                }
                if (!(TextFieldManager.getCreateNicknameTextField().getText().length() >= 5)) {
                    fieldManager.setCreateNicknameInvalidInput();
                    isfieldValidate++;
                }
                if (!TextFieldManager.getEmailTextField().getText().matches(pattern)) {
                    fieldManager.setEmalInvalidInput();
                    isfieldValidate++;
                }
                if (!(TextFieldManager.getCreatePasswordTextField().getText().length() >= 4)) {
                    fieldManager.setCreatePasswordInvalidInput();
                    isfieldValidate++;
                }
                if (!(TextFieldManager.getRepeatPasswordTextField().getText().length() >= 4)) {
                    fieldManager.setRepeatPasswordInvalidInput();
                    isfieldValidate++;
                } else if (!TextFieldManager.getCreatePasswordTextField().getText().equals(TextFieldManager.getRepeatPasswordTextField().getText())) {
                    fieldManager.setRepeatPasswordInvalidInput();
                    fieldManager.setCreatePasswordInvalidInput();
                    isfieldValidate++;
                } else if (isfieldValidate == 0) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuAfterLogin());
                }
            }
        });
        table.add(fieldManager.registrationScreenTextFields("createUsernameTextField")).size(Gdx.graphics.getWidth() / 2.5f, 60).pad(20).row();
        table.add(fieldManager.registrationScreenTextFields("createNicknameTextField")).size(Gdx.graphics.getWidth() / 2.5f, 60).pad(20).row();
        table.add(fieldManager.registrationScreenTextFields("emailTextField")).pad(20).size(Gdx.graphics.getWidth() / 2.5f, 60).row();
        table.add(fieldManager.registrationScreenTextFields("createPasswordTextField")).size(Gdx.graphics.getWidth() / 2.5f, 60).pad(20).row();
        table.add(fieldManager.registrationScreenTextFields("Repeat Password")).pad(20).size(Gdx.graphics.getWidth() / 2.5f, 60).row();
        table.add(register).pad(20).size(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 17).row();
        table.setFillParent(true);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
//        dispose();
    }

    @Override
    public void dispose() {
//        stage.dispose();
//        skin.dispose();
    }
}