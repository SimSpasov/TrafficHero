package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextFieldManager;


public class ChangeNicknameScreen implements Screen {
    private Stage stage;
    private TextFieldManager fieldManager = new TextFieldManager();
    private Label statusLabel = new Label("", Constants.skin);
    private SpriteBatch batch;
    private boolean isValidate;
    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        batch = new SpriteBatch();

        Table table = new Table();
        table.setFillParent(true);

        Table table1 = new Table();

        TextButton backButton = new TextButton("Change nickname",Constants.skin);
        backButton.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isValidate = true;
                if (!TextFieldManager.getNewNicknameTextField().getText().equals(TextFieldManager.getRepeatNewNicknameTextField().getText())){
                    statusLabel.setText("Nicknames are not same!");
                    isValidate = false;
                }
                if(TextFieldManager.getNewNicknameTextField().getText().equals(TextFieldManager.getUsernameLogin().getText())){
                    statusLabel.setText("Choose Different Nickname!");
                    isValidate = false;
                }
                if(!(TextFieldManager.getNewNicknameTextField().getText().length() >= Constants.MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGTH &&
                        TextFieldManager.getNewNicknameTextField().getText().length() <= Constants.MAX_PASSWORD_USERNAME_AND_NICKNAME_LENGTH )) {

                    TextFieldManager.getNewNicknameTextField().setText("");
                    TextFieldManager.getRepeatNewNicknameTextField().setText("");
                    TextFieldManager.getRepeatNewNicknameTextField().setMessageText("must be > 4 or < 10 letters");
                    TextFieldManager.getNewNicknameTextField().setMessageText("must be > 4 or < 10 letters");

                }else if(isValidate){

                    ((Game)Gdx.app.getApplicationListener()).setScreen(new ChangeProfileScreen());
                }
            }
        });

        table.add(statusLabel).row();
        table.add(fieldManager.changeNicknameScreenTextFields("New Nickname")).pad(Constants.DEFAULT_PADDING)
                .size(Constants.DEFAULT_TEXT_FIELD_WIDTH, Constants.DEFAULT_TEXT_FIELD_HEIGHT).row();
        table.add(fieldManager.changeNicknameScreenTextFields("repeat Nickname")).pad(Constants.DEFAULT_PADDING)
                .size(Constants.DEFAULT_TEXT_FIELD_WIDTH, Constants.DEFAULT_BUTTON_HEIGHT).row();
        table1.add(backButton).size(Constants.DEFAULT_BUTTON_WIDTH,Constants.DEFAULT_BUTTON_HEIGHT).pad(Constants.DEFAULT_PADDING).row();
        table.add(table1).row();


        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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