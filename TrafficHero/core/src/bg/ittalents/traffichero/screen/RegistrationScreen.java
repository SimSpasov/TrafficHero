package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextFieldManager;
import bg.ittalents.traffichero.network.RegisterRequest;

public class RegistrationScreen implements Screen {
    private static TextFieldManager fieldManager = new TextFieldManager();
    private Stage stage;
    private Skin skin = Constants.skin;
    private TextButton register = new TextButton("Register", Constants.skin);
    private int isFieldValidate;
    public static Label statusLabel = new Label("",Constants.skin);

    private SpriteBatch batch;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        batch = new SpriteBatch(Constants.SCREEN_HEIGHT);
        final String pattern = "^[a-zA-Z0-9_.+-]+@[a-z]+.[a-z{2,4}]+$";
        Table table;
        table = new Table(skin);


        register.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                TextFieldManager.getCreateUsernameTextField().setColor(Color.GREEN);
                TextFieldManager.getCreateUsernameTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
                TextFieldManager.getCreateNicknameTextField().setColor(Color.GREEN);
                TextFieldManager.getCreateNicknameTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
                TextFieldManager.getEmailTextField().setColor(Color.GREEN);
                TextFieldManager.getEmailTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
                TextFieldManager.getCreatePasswordTextField().setColor(Color.GREEN);
                TextFieldManager.getCreatePasswordTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
                TextFieldManager.getRepeatPasswordTextField().setColor(Color.GREEN);
                TextFieldManager.getRepeatPasswordTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
                isFieldValidate = 0;
                if (!(TextFieldManager.getCreateUsernameTextField().getText().length() >= Constants.MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGTH)) {
                    fieldManager.setCreateUsernameInvalidInput();
                    isFieldValidate++;
                }
                if (!(TextFieldManager.getCreateNicknameTextField().getText().length() >= Constants.MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGTH) &&
                        TextFieldManager.getCreateNicknameTextField().getText().length() <= Constants.MAX_PASSWORD_USERNAME_AND_NICKNAME_LENGTH) {
                    fieldManager.setCreateNicknameInvalidInput();
                    isFieldValidate++;
                }
                if (!TextFieldManager.getEmailTextField().getText().matches(pattern)) {
                    fieldManager.setEmalInvalidInput();
                    isFieldValidate++;
                }
                if (!(TextFieldManager.getCreatePasswordTextField().getText().length() >= Constants.MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGTH)) {
                    fieldManager.setCreatePasswordInvalidInput();
                    isFieldValidate++;
                }
                if (!(TextFieldManager.getRepeatPasswordTextField().getText().length() >= Constants.MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGTH)) {
                    fieldManager.setRepeatPasswordInvalidInput();
                    isFieldValidate++;
                } else if (!TextFieldManager.getCreatePasswordTextField().getText().equals(TextFieldManager.getRepeatPasswordTextField().getText())) {
                    fieldManager.setRepeatPasswordInvalidInput();
                    fieldManager.setCreatePasswordInvalidInput();
                    isFieldValidate++;
                } else if (isFieldValidate == 0) {
                    //((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());
                    new RegisterRequest(TextFieldManager.getCreateUsernameTextField().getText(),TextFieldManager.getCreatePasswordTextField().getText(),
                            TextFieldManager.getCreateNicknameTextField().getText(),TextFieldManager.getEmailTextField().getText());
                }
            }
        });
        table.add(statusLabel).row();
        table.add(fieldManager.registrationScreenTextFields("createUsernameTextField")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH, Constants.DEFAULT_TEXT_FIELD_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.add(fieldManager.registrationScreenTextFields("createNicknameTextField")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH, Constants.DEFAULT_TEXT_FIELD_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.add(fieldManager.registrationScreenTextFields("emailTextField")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH, Constants.DEFAULT_TEXT_FIELD_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.add(fieldManager.registrationScreenTextFields("createPasswordTextField")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH, Constants.DEFAULT_TEXT_FIELD_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.add(fieldManager.registrationScreenTextFields("Repeat Password")).size(Constants.DEFAULT_TEXT_FIELD_WIDTH, Constants.DEFAULT_TEXT_FIELD_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.add(register).pad(Constants.DEFAULT_PADDING).size(Constants.DEFAULT_BUTTON_WIDTH, Constants.DEFAULT_BUTTON_HEIGHT).row();
        table.setFillParent(true);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new LoginScreen());
        }

        batch.begin();
        batch.draw(Constants.backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
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
    public void dispose()
    {
        stage.dispose();
    }
}