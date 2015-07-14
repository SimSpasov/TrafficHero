package bg.ittalents.traffichero.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import bg.ittalents.traffichero.screen.ChangeProfileScreen;

public class ChangeUserPassword implements Screen {

    //    public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
//    public static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private Stage stage;
    private Table table = new Table();
    private TextFieldManager fieldManager = new TextFieldManager();
    private TextButton changePass = new TextButton("Request change password",Constants.skin);
    private SpriteBatch batch;
    public static Label statusLabel;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        batch = new SpriteBatch();
        statusLabel = new Label("", Constants.skin);

        changePass.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
        changePass.addListener(new ChangeListener() {
//            String oldPass = TextFieldManager.getCreatePasswordTextField().getText();
//            String loginPass = TextFieldManager.getPasswordLogin().getText();

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                TextFieldManager.getOldPasswordTextField().setColor(Color.GREEN);
                TextFieldManager.getOldPasswordTextField().addAction(Actions.alpha(Constants.CUSTOM_ALPHA));
                TextFieldManager.getNewPasswordTextField().setColor(Color.GREEN);
                TextFieldManager.getNewPasswordTextField().addAction(Actions.alpha(Constants.CUSTOM_ALPHA));
                TextFieldManager.getRepeatNewPassword().setColor(Color.GREEN);
                TextFieldManager.getRepeatNewPassword().addAction(Actions.alpha(Constants.CUSTOM_ALPHA));
                statusLabel.setText("");
                String oldPass = TextFieldManager.getPasswordLogin().getText();
                boolean validation = true;
                if (!(TextFieldManager.getOldPasswordTextField().getText().equals(oldPass)) ||
                        (TextFieldManager.getOldPasswordTextField().getText().length() <= Constants.MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGTH)) {
                    fieldManager.setOldPasswordInvalidInput();
                    validation = false;
                }
                if (!TextFieldManager.getNewPasswordTextField().getText().equals(TextFieldManager.getRepeatNewPassword().getText()) ||
                        TextFieldManager.getNewPasswordTextField().getText().length() <= Constants.MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGTH) {
                    fieldManager.setNewPassWordInvalidInput();
                    fieldManager.setRepeatNewPasswordInvalidInput();
                    validation = false;
                }
                if (validation) {
                    fieldManager.deleteTextFieldsText();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new ChangeProfileScreen());
//
                }

            }
        });
        table.add(statusLabel).row();
        table.add(fieldManager.updatePasswordScreenTextFields("oldPasswordTextField")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH,
                Constants.DEFAULT_BUTTON_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.add(fieldManager.updatePasswordScreenTextFields("newPasswordTextField")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH,
                Constants.DEFAULT_BUTTON_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.add(fieldManager.updatePasswordScreenTextFields("repeatNewPasswordTextField")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH,
                Constants.DEFAULT_BUTTON_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.add(changePass).size(changePass.getWidth(), Constants.DEFAULT_BUTTON_HEIGHT).row();
        table.setFillParent(true);
        stage.addActor(table);

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(Constants.backgroundTexture, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new ChangeProfileScreen());
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
