package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import bg.ittalents.traffichero.main.ButtonManager;
import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextFieldManager;
import bg.ittalents.traffichero.network.User;

public class MainScreen implements Screen {
    private Stage stage;
    private Table table;
    private TextButton logout;
    private SpriteBatch batch;
    private static BitmapFont black = new BitmapFont(Gdx.files.internal("fonts/black.fnt"));
    private static Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(Gdx.files.internal("fonts/black.fnt")), Color.BLACK);
    private static Label statusLabel = new Label("",style);
    public static Label greetingLabel = new Label("",Constants.skin);

    @Override
    public void show() {
        final TextFieldManager fieldManager = new TextFieldManager();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        ButtonManager buttonManager = new ButtonManager();
        greetingLabel.setPosition(Constants.SCREEN_WIDTH/2 - greetingLabel.getWidth(),
                Constants.SCREEN_HEIGHT - Constants.DEFAULT_ALPHA);

        logout= new TextButton("LOG OUT", Constants.skin);
        logout.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
        logout.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                fieldManager.deleteTextFieldsText();
                greetingLabel.setText("");
                User.getSingletonUser().logout();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LoginScreen());
            }
        });

        Table table1 = new Table();
        Table table2 = new Table();
        table = new Table(Constants.skin);
        table.setFillParent(true);
        table.add(statusLabel).row();
        table1.add(buttonManager.afterLoginScreenButtons("Play")).size(Constants.DEFAULT_BUTTON_WIDTH,Constants.CUSTOM_BUTTON_HEIGHT).fill(true, false).pad(Constants.DEFAULT_PADDING);
        table1.add(buttonManager.afterLoginScreenButtons("Change Profile")).size(Constants.DEFAULT_BUTTON_WIDTH,Constants.CUSTOM_BUTTON_HEIGHT).fill(true, false).pad(Constants.DEFAULT_PADDING);

        table.add(table1).row();
        table2.add(buttonManager.afterLoginScreenButtons("HeightScore")).size(Constants.DEFAULT_BUTTON_WIDTH, Constants.CUSTOM_BUTTON_HEIGHT).fill(true, false).pad(Constants.DEFAULT_PADDING);
        table2.add(logout).size(Constants.DEFAULT_BUTTON_WIDTH,Constants.CUSTOM_BUTTON_HEIGHT).pad(Constants.DEFAULT_PADDING);
        table.add(table2);
        stage.addActor(table);
        stage.addActor(greetingLabel);
        batch = new SpriteBatch();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(Constants.backgroundTexture,0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        batch.end();
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new LoginScreen());
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
        batch.dispose();
        stage.dispose();
    }


    public static Label getGreetingLabel() {
        return greetingLabel;
    }

    public static void setGreetingLabel(Label greetingLabel) {
        MainScreen.greetingLabel = greetingLabel;
    }
}
