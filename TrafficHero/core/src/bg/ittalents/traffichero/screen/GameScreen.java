package bg.ittalents.traffichero.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import bg.ittalents.traffichero.entity.Background;
import bg.ittalents.traffichero.entity.Coin;
import bg.ittalents.traffichero.entity.Enemy;
import bg.ittalents.traffichero.entity.Player;
import bg.ittalents.traffichero.main.Constants;
import bg.ittalents.traffichero.main.TextureManager;


public class GameScreen implements Screen {

    private enum GameState {READY, RUNNING, PAUSED}
    private GameState currentState;

    private Stage stage;
    private Player player;
    private Background background;
    private Background backgroundCopy;
    private Array<Enemy> enemies;
    private int enemyCount = 25;
    private float lastEnemySpawnTime;
    private int currentScore;
    private int score;
    private Label heading;
    private Coin coin;
    private Window pauseWindow;
    private Window endGameWindow;
    private Skin uiSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
    private TextButton pauseButton;
    private TextButton resumeButton;
    private TextButton exitButton;
    private TextButton retryButton;
    private Label nearMissLabel;

    public GameScreen() {
        currentState = GameState.RUNNING;
        stage = new Stage();
        player = new Player();

        background = new Background(0);
        backgroundCopy = new Background(TextureManager.BACKGROUND.getHeight() / 1.15f);
        enemies = new Array<Enemy>();
        for (int i = 0; i < enemyCount; i++) {
            enemies.add(new Enemy());
        }
        currentScore = 0;
        heading = new Label("SCORE: " + currentScore, uiSkin);
        heading.setX(Constants.SCREEN_WIDTH - heading.getWidth() * 1.5f);
        heading.setY(Constants.SCREEN_HEIGHT - heading.getHeight());
        stage.addActor(background);
        stage.addActor(backgroundCopy);
        stage.addActor(player);
        stage.addActor(heading);
        resumeButton = new TextButton("RESUME", uiSkin);
        exitButton = new TextButton("EXIT", uiSkin);
        retryButton = new TextButton("RETRY", uiSkin);
        pauseWindow = new Window("PAUSE", uiSkin);
        pauseWindow.setWidth(Constants.SCREEN_WIDTH / 3);
        pauseWindow.setHeight(Constants.SCREEN_WIDTH / 5);
        pauseWindow.setX(Constants.SCREEN_WIDTH / 2 - pauseWindow.getWidth() / 2);
        pauseWindow.setY(Constants.SCREEN_HEIGHT / 2 - pauseWindow.getHeight() / 2);
        pauseWindow.add(resumeButton).size(Gdx.graphics.getWidth(), resumeButton.getHeight()).row();
        pauseWindow.add(exitButton).size(Gdx.graphics.getWidth(), exitButton.getHeight()).row();
        endGameWindow = new Window("GAME OVER", uiSkin);
        endGameWindow.setWidth(Constants.SCREEN_WIDTH / 1.2f);
        endGameWindow.setHeight(Constants.SCREEN_WIDTH / 3);
        endGameWindow.setX(Constants.SCREEN_WIDTH / 2 - endGameWindow.getWidth() / 2);
        endGameWindow.setY(Constants.SCREEN_HEIGHT / 2 - endGameWindow.getHeight() / 2);
        pauseButton = new TextButton("PAUSE", uiSkin);
        pauseButton.setHeight(Constants.SCREEN_WIDTH / 10);
        pauseButton.setWidth(Constants.SCREEN_WIDTH / 5);
        pauseButton.setY(Constants.SCREEN_HEIGHT - pauseButton.getHeight());
        Gdx.input.setInputProcessor(stage);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(pauseWindow);
                currentState = GameState.PAUSED;
            }
        });
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentState = GameState.RUNNING;
                pauseWindow.remove();
                Gdx.graphics.setContinuousRendering(true);
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
            }
        });
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });
        stage.addActor(pauseButton);
    }

    private void spawnEnemy(float timeSinceLast) {
        this.lastEnemySpawnTime += timeSinceLast;
        if (this.lastEnemySpawnTime > 1.5) {
            this.lastEnemySpawnTime = 0.0f;
            if (enemies.size != 0) {
                stage.addActor(enemies.pop());
            }
        }
    }

    private void checkForCollisions() {
        if (gameWon()) {
            stage.addActor(endGameWindow);
        }
        for (Actor a : stage.getActors()) {
            if (a instanceof Enemy) {
                if (((Enemy)a).getBounds().overlaps(player.getBounds())) {
                    a.addAction(Actions.rotateBy(50, 1.0f));
                    a.addAction(Actions.moveBy(a.getX(), a.getY() + a.getHeight(), 4.0f));
                    player.addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(50, 0.5f),
                            Actions.moveTo(player.getWidth() / 2, player.getY(), 0.5f)), Actions.rotateBy(-50, 0.5f)));
                }
            }
            if (a instanceof Coin) {
                if (((Coin)a).getBounds().overlaps(player.getBounds())) {
                    //Coin hit logic
                    currentScore += 100;
                    a.remove();
                }
            }
            if (!(a instanceof  Background) && a.getY() < - a.getHeight()) {
                a.remove();
            }
            if ((a.getRight() - player.getX() < Constants.NEAR_MISS_DISTANCE && a.getY() - player.getY() >= -2 && a.getY() - player.getY() <= 2) ||
                    (player.getRight() - a.getX() < Constants.NEAR_MISS_DISTANCE && a.getY() - player.getY() >= -2 && a.getY() - player.getY() <= 2)) {
                currentScore += 50;
                nearMissLabel = new Label("Near Miss!", uiSkin);
                nearMissLabel.setPosition(player.getX(), player.getTop());
                stage.addActor(nearMissLabel);
                nearMissLabel.addAction(Actions.fadeOut(0.8f));
            }
        }
    }

    private boolean gameWon() {
        if (enemies.size == 0 && stage.getActors().size <= 5) {
            score = currentScore;
            endGameWindow.add(new Label("SCORE: " + score, uiSkin)).row();
            endGameWindow.add(retryButton).row();
            endGameWindow.add(exitButton).row();
            player.addAction(Actions.sequence(Actions.moveTo(Gdx.graphics.getWidth() / 2 - player.getWidth() / 2, Gdx.graphics.getHeight() / 2, 4.0f),
                    Actions.moveTo(Gdx.graphics.getWidth() / 2 - player.getWidth() / 2, Gdx.graphics.getHeight(), 1.0f)));
            return true;
        }
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (currentState == GameState.RUNNING) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act(delta);
            stage.draw();
            spawnEnemy(Gdx.graphics.getDeltaTime());
            checkForCollisions();
            heading.setText("SCORE: " + currentScore);
        }
        if (currentState == GameState.PAUSED) {
            stage.draw();

        }
    }

//    private class ReadyActor extends Actor {
//        Texture texture;
//
//        ReadyActor(Texture texture) {
//            this.texture = texture;
//        }
//
//        @Override
//        public void draw(Batch batch, float parentAlpha) {
//            batch.draw(texture, Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 1.75f, Gdx.graphics.getWidth() / 1.5f, texture.getWidth() / 2.5f);
//        }
//    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        stage.addActor(pauseWindow);
        currentState = GameState.PAUSED;
        Gdx.graphics.setContinuousRendering(false);
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        uiSkin.dispose();
    }
}
