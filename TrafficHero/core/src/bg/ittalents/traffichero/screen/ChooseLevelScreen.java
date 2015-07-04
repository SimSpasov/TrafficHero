package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import bg.ittalents.traffichero.main.ButtonManager;

public class ChooseLevelScreen implements Screen {

    private Stage stage;
    @Override
    public void show() {

        stage = new Stage(new StretchViewport(480f,800f));
        Gdx.input.setInputProcessor(stage);

        Table table;
        BitmapFont white;
        Label heading;
        //Creating bitmapFonts and add the fnt files from assets to them..
        white = new BitmapFont(Gdx.files.internal("fonts/white.fnt"),false);

        Label.LabelStyle headingStyle = new Label.LabelStyle(white, Color.ORANGE);
        heading = new Label("TITLE",headingStyle);



        table = new Table();
        table.setFillParent(true);

        ButtonManager buttonManager = new ButtonManager();
        table.setFillParent(true);
        table.add(heading).pad(30).row();
        table.add(buttonManager.levelButtons("Level 1")).size(Gdx.graphics.getWidth() / 2.5f, 65).pad(10).fill(true, false).row();
        table.add(buttonManager.levelButtons("Level 2")).size(Gdx.graphics.getWidth() / 2.5f, 65).pad(10).fill(true, false).row();
        table.add(buttonManager.levelButtons("Level 3")).size(Gdx.graphics.getWidth() / 2.5f, 65).pad(10).fill(true, false).row();

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