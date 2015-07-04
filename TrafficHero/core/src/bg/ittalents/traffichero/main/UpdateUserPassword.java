package bg.ittalents.traffichero.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import bg.ittalents.traffichero.screen.SettingsScreen;

public class UpdateUserPassword implements Screen {
    public static final int DEFAULT_PADDING = 20;
    public static final int DEFAULT_BUTTON_HEIGHT = 60;
    public static final int DEFAULT_TEXT_FIELD_HEIGHT = 70;
    public static final float DEFAULT_BUTTON_WIDTH = Gdx.graphics.getWidth() / 3f;
    public static final float DEFAULT_TEXT_FIELD_WIDTH = Gdx.graphics.getWidth() / 2f;
    public static final int MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGHT = 2;
    //    public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
//    public static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    Stage stage;
    Table table = new Table();
    bg.ittalents.traffichero.main.TextFieldManager fieldManager = new bg.ittalents.traffichero.main.TextFieldManager();
    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        TextButton changePass = new TextButton("Request change password", bg.ittalents.traffichero.main.ButtonManager.skin);
        changePass.addListener(new ChangeListener() {
//            String oldPass = TextFieldManager.getCreatePasswordTextField().getText();
//            String loginPass = TextFieldManager.getPasswordLogin().getText();

            @Override
            public void changed(ChangeEvent event, Actor actor) {

//                TextFieldManager.getOldPasswordTextField().setColor(Color.GREEN);
                bg.ittalents.traffichero.main.TextFieldManager.getNewPasswordTextField().setColor(Color.GREEN);
                bg.ittalents.traffichero.main.TextFieldManager.getRepeatNewPassword().setColor(Color.GREEN);
//                if (!(TextFieldManager.getOldPasswordTextField().getText().equals(oldPass) || TextFieldManager.getOldPasswordTextField().getText().equals(loginPass))||
//                        (TextFieldManager.getOldPasswordTextField().getText().length() <= MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGHT)) {
//                    fieldManager.setOldPasswordInvalidInput();
//                }
                if (!bg.ittalents.traffichero.main.TextFieldManager.getNewPasswordTextField().getText().equals(bg.ittalents.traffichero.main.TextFieldManager.getRepeatNewPassword().getText()) ||
                        bg.ittalents.traffichero.main.TextFieldManager.getNewPasswordTextField().getText().length() <= MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGHT) {
                        fieldManager.setNewPassWordInvalidInput();
                        fieldManager.setRepeatNewPasswordInvalidInput();
                    }
                else{
                    fieldManager.deleteTextFieldsText();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new SettingsScreen());
//
                }

            }
        });
        table.addAction(Actions.alpha(30));
        table.add(fieldManager.updatePasswordScreenTextFields("Old Password")).size(DEFAULT_TEXT_FIELD_WIDTH, DEFAULT_BUTTON_HEIGHT).pad(DEFAULT_PADDING).row();
        table.add(fieldManager.updatePasswordScreenTextFields("New Password")).size(DEFAULT_TEXT_FIELD_WIDTH, DEFAULT_BUTTON_HEIGHT).pad(DEFAULT_PADDING).row();
        table.add(fieldManager.updatePasswordScreenTextFields("Repeat Password")).size(DEFAULT_TEXT_FIELD_WIDTH, DEFAULT_BUTTON_HEIGHT).pad(DEFAULT_PADDING).row();
        table.add(changePass).size(changePass.getWidth(), DEFAULT_BUTTON_HEIGHT).row();
        table.setFillParent(true);
        stage.addActor(table);

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
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