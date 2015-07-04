package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import bg.ittalents.traffichero.main.ButtonManager;
import bg.ittalents.traffichero.main.TextFieldManager;

public class MenuAfterLogin implements Screen {
    private Stage stage;
    private Table table;
    private TextButton logout;
    private SpriteBatch batch;
    private Sprite background;
    private float time = 0.0f;
    @Override
    public void show() {
        final TextFieldManager fieldManager = new TextFieldManager();
        stage = new Stage(new StretchViewport(480f,800f));
        Gdx.input.setInputProcessor(stage);

        Gdx.input.setCatchBackKey(true);

        table = new Table(ButtonManager.skin);
        table.setFillParent(true);


       logout= new TextButton("Logout", ButtonManager.skin);
        logout.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                fieldManager.deleteTextFieldsText();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LoginScreen());
            }
        });

        ButtonManager buttonManager = new ButtonManager();
        table.add(buttonManager.afterLoginScreenButtons("Play")).size(Gdx.graphics.getWidth() / 2.5f, 65).fill(true, false).pad(10).row();
        table.add(buttonManager.afterLoginScreenButtons("Change Profile")).size(Gdx.graphics.getWidth() / 2.5f, 65).fill(true, false).pad(10).row();
        table.add(buttonManager.afterLoginScreenButtons("HeightScore")).size(Gdx.graphics.getWidth() / 2.5f, 65).fill(true, false).pad(10).row();
        table.add(logout).size(Gdx.graphics.getWidth() / 2.5f, 65).pad(10).row();
        stage.addActor(table);
        batch = new SpriteBatch();
        Texture texture = new Texture("bg-road.png");
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        background = new Sprite(texture);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.time +=Gdx.graphics.getDeltaTime();
       batch.begin();
        if(time>1f)
            time= 0.0f;
        background.setV2(time);
        background.setV(time + 2.4f);
        background.draw(batch);
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
//        dispose();
    }

    @Override
    public void dispose() {
//        stage.dispose();
    }
}
