package bg.ittalents.traffichero.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
import bg.ittalents.traffichero.network.User;


public class GameScreen implements Screen {

    private enum GameState {READY, RUNNING, PAUSED}
    private GameState currentState;

    private Stage stage;
    private Player player;
    private Background background;
    private Background backgroundCopy;
    private Array<Enemy> enemies;
    private float lastEnemySpawnTime;
    private float lastupdateframe;
    private int currentScore;
    private int score;
    private Label scoreLabel;
    private Label armorLabel;
    private Array<Coin> coins;
    private Window pauseWindow;
    private Window endGameWindow;
    private Skin uiSkin = new Skin(getSkinFontSize());
    private TextButton pauseButton;
    private TextButton resumeButton;
    private TextButton exitButton;
    private TextButton retryButton;
    private Label nearMissLabel;
    private ReadyActor readyActor;
    private float lastCoinSpawnTime;
    private Sound coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin1.wav"));
    private Sound readyGoSound = Gdx.audio.newSound(Gdx.files.internal("sounds/readygo.wav"));
    //private Music bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/bg-music.mp3"));
    private Sound crashSound = Gdx.audio.newSound(Gdx.files.internal("sounds/crash3.mp3"));
    private int armor = Constants.MAX_ARMOR;



    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Bold.ttf"));
    BitmapFont font20 = generator.generateFont(getCollectiblesFontSize());
    Label.LabelStyle style20 = new Label.LabelStyle(font20, Color.WHITE);



    public GameScreen(int enemyCount) {
        initGameActors(enemyCount);
    }

    //Spawn enemy on screen
    private void spawnEnemy(float timeSinceLast) {
        this.lastEnemySpawnTime += timeSinceLast;
        if (this.lastEnemySpawnTime > Constants.ENEMY_SPAWN_RATE) {
            this.lastEnemySpawnTime = 0.0f;
            if (enemies.size != 0) {
                stage.addActor(enemies.pop());
            }
        }
    }

    //Spawn coins on screen
    private void spawnCoin(float timeSinceLast) {
        this.lastCoinSpawnTime += timeSinceLast;
        if (this.lastCoinSpawnTime > 1.2 && enemies.size > 0) {
            coins.add(new Coin());
            stage.addActor(coins.pop());
            this.lastCoinSpawnTime = 0.0f;
        }
    }

    private void checkForEnemyCollisions() {
        //Check for enemy collisions with player and set an animation action if true
        for (Actor a : stage.getActors()) {
            if (a instanceof Enemy) {
                if (((Enemy)a).getBounds().overlaps(player.getBounds())) {
                    float movedistance;
                    if (player.getX() > a.getX()) {
                        movedistance = a.getWidth() / 2;
                    } else {
                        movedistance = -a.getWidth() / 2;
                    }
                    if (a.getActions().size == 0) {
                        a.addAction(Actions.sequence(Actions.rotateBy(Constants.CAR_CRASH_ROTATE_ANGLE, 0.2f), Actions.rotateBy(-Constants.CAR_CRASH_ROTATE_ANGLE, 0.2f)));
                        a.addAction(Actions.moveTo(a.getX() - movedistance * 10, a.getY() + a.getHeight() / 2, 1.0f));
                    }
                    if (player.getActions().size == 0) {
                        player.addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(-Constants.CAR_CRASH_ROTATE_ANGLE, 0.2f),
                                Actions.moveTo(player.getX() - movedistance / 2, player.getY(), 0.2f)), Actions.rotateBy(Constants.CAR_CRASH_ROTATE_ANGLE, 0.2f)));
                        crashSound.play();
                        armor -= 1;
                        armorLabel.addAction(Actions.sequence(Actions.color(Color.RED, 0.3f),Actions.color(Color.WHITE)));
                    }
                }
            }
            //If any actor except background is below screen - remove him
            if (!(a instanceof  Background) && a.getY() < - a.getHeight()) {
                a.remove();
            }
        }
    }

    //Check for near misses with enemy cars and if true add bonus points to score
    private void checkForNearMiss() {
        for (Actor a : stage.getActors()) {
            if (a instanceof Enemy) {
                boolean yDifference = a.getY() - player.getY() <= -player.getHeight() / 2 && a.getY() - player.getY() >= -player.getHeight();
                boolean xDifference1 = player.getX() - a.getRight() < Constants.NEAR_MISS_DISTANCE && player.getX() - a.getRight() > 0;
                boolean xDifference2 = a.getX() - player.getRight() < Constants.NEAR_MISS_DISTANCE && a.getX() - player.getRight() > 0;
                if (!((Enemy) a).getNearMissed() && yDifference && (xDifference1 || xDifference2)) {
                    ((Enemy) a).setNearMissed();
                    currentScore += Constants.ENEMY_MISS_BONUS;
                    nearMissLabel = new Label("Near Miss!", style20);
                    nearMissLabel.setColor(Color.GREEN);
                    nearMissLabel.setPosition(player.getX(), player.getTop());
                    stage.addActor(nearMissLabel);
                    scoreLabel.addAction(Actions.sequence(Actions.color(Color.GREEN, 0.3f), Actions.color(Color.WHITE)));
                    nearMissLabel.addAction(Actions.sequence(Actions.fadeOut(0.6f), Actions.removeActor()));
                }
            }
        }
    }

    //Check if player collides with collectibles and give him bonus points if so
    private void checkForCollectibleCollision() {
        for (Actor a : stage.getActors()) {
            if (a instanceof Coin) {
                if (((Coin)a).getBounds().overlaps(player.getBounds())) {
                    coinSound.play();
                    currentScore += Constants.COIN_PICKUP_BONUS;
                    nearMissLabel = new Label("+" +Constants.COIN_PICKUP_BONUS, style20);
                    nearMissLabel.setColor(Color.GREEN);
                    nearMissLabel.setPosition(player.getX(), player.getTop());
                    stage.addActor(nearMissLabel);
                    scoreLabel.addAction(Actions.sequence(Actions.color(Color.GREEN, 0.3f), Actions.color(Color.WHITE)));
                    nearMissLabel.addAction(Actions.sequence(Actions.fadeOut(0.6f), Actions.removeActor()));
                    a.remove();
                    coins.removeValue((Coin) a, false);
                }
            }
        }
    }


    //Check if given enemy count become zero and if so the game is won
    private boolean gameOver() {
        if ((enemies.size == 0 && stage.getActors().size <= 6) || armor <=0) {
            score += armor * Constants.END_GAME_ARMOR_BONUS_MULTIPLIER;
            score = currentScore;
            //endGameWindow.add(new Label("SCORE: " + score, uiSkin)).row();
            endGameWindow.add(scoreLabel).row();
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
            if (!gameOver()) {
                spawnEnemy(Gdx.graphics.getDeltaTime());
                spawnCoin(Gdx.graphics.getDeltaTime());
                checkForEnemyCollisions();
                checkForCollectibleCollision();
                checkForNearMiss();
            } else {
                stage.addActor(endGameWindow);
                gameOver();
            }
            //Update overlay
            scoreLabel.setText("SCORE: " + currentScore);
            armorLabel.setText("ARMOR: " + armor + "   ");
        }
        if (currentState == GameState.PAUSED) {
            lastupdateframe += Gdx.graphics.getDeltaTime();
            if (lastupdateframe < 0.2f) {
                stage.draw();
            }
        }
        if (currentState == GameState.READY) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act(delta);
            stage.draw();

        }
    }

    //This class is used for the starting countdown animation
    private class ReadyActor extends Actor {
        Texture texture;

        ReadyActor() {
            texture = TextureManager.RDY1;
            addAction(Actions.sequence(Actions.fadeOut(0.9f),Actions.run(new Runnable() {
                @Override
                public void run() {
                    texture = TextureManager.RDY2;
                    addAction(Actions.sequence(Actions.fadeOut(0.9f),Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            texture = TextureManager.RDY3;
                            addAction(Actions.sequence(Actions.fadeOut(0.9f),Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    texture = TextureManager.RDY4;
                                    addAction(Actions.sequence(Actions.fadeOut(0.6f),Actions.run(new Runnable() {
                                        @Override
                                        public void run() {
                                            remove();
                                            currentState = GameState.RUNNING;
                                            background.setSpeed(Constants.BACKGROUND_SPEED);
                                            backgroundCopy.setSpeed(Constants.BACKGROUND_SPEED);
                                            Gdx.graphics.setContinuousRendering(true);
//                                                bgMusic.play();
//                                                bgMusic.setLooping(true);
                                        }
                                    })));
                                }
                            })));
                        }
                    })));
                }
            })));

            setX(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 3f);
            setY(Gdx.graphics.getHeight() / 1.75f);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(texture, this.getX(), this.getY(), Constants.RDY_TEXTURE_WIDTH, Constants.RDY_TEXTURE_HEIGHT);

        }
    }

    //Dynamically set font size based on screen dimensions
    private int getCollectiblesFontSize() {
        if (Constants.SCREEN_HEIGHT > Constants.BIG_SCREEN_FONT_CRITERIA) {
            return 50;
        }
        if (Constants.SCREEN_HEIGHT < Constants.BIG_SCREEN_FONT_CRITERIA && Constants.SCREEN_HEIGHT > Constants.MEDIUM_SCREEN_FONT_CRITERIA) {
            return 35;
        } else {
            return 25;
        }
    }

    //Dynamically set font size based on screen dimensions
    private FileHandle getSkinFontSize() {
        if (Constants.SCREEN_HEIGHT > Constants.BIG_SCREEN_FONT_CRITERIA) {
            return Gdx.files.internal("data/uiskin50.json");
        }
        if (Constants.SCREEN_HEIGHT < Constants.BIG_SCREEN_FONT_CRITERIA && Constants.SCREEN_HEIGHT > Constants.MEDIUM_SCREEN_FONT_CRITERIA) {
            return Gdx.files.internal("data/uiskin25.json");
        } else {
            return Gdx.files.internal("data/uiskin35.json");
        }
    }

    //Initialize all game actors
    private void initGameActors(int enemyCount) {
        currentState = GameState.READY;
        stage = new Stage();
        player = new Player();
        coins = new Array<Coin>();
        background = new Background(0);
        backgroundCopy = new Background(Constants.BACKGROUND_COPY_POSITION);
        enemies = new Array<Enemy>();
        for (int i = 0; i < enemyCount; i++) {
            enemies.add(new Enemy());
        }
        currentScore = 0;
        scoreLabel = new Label("SCORE: 10000" + currentScore, uiSkin);
        scoreLabel.setX(Constants.SCREEN_WIDTH - scoreLabel.getWidth());
        scoreLabel.setY(Constants.SCREEN_HEIGHT - scoreLabel.getHeight());
        scoreLabel.setText("SCORE: " + currentScore);
        armorLabel = new Label("ARMOR: " + armor + "   ",uiSkin);
        armorLabel.setX(scoreLabel.getX() - armorLabel.getWidth());
        armorLabel.setY(Constants.SCREEN_HEIGHT - armorLabel.getHeight());

        //Actually add actors to the stage
        stage.addActor(background);
        stage.addActor(backgroundCopy);
        stage.addActor(player);
        stage.addActor(scoreLabel);
        stage.addActor(armorLabel);

        //This is the starting countdown
        readyActor = new ReadyActor();
        background.setSpeed(0);
        backgroundCopy.setSpeed(0);
        stage.addActor(readyActor);

        //Buttons and windows
        resumeButton = new TextButton("RESUME", uiSkin);
        exitButton = new TextButton("EXIT", uiSkin);
        retryButton = new TextButton("RETRY", uiSkin);
        pauseWindow = new Window("PAUSE", uiSkin);
        pauseWindow.setWidth(Constants.PAUSE_WINDOW_WIDTH);
        pauseWindow.setHeight(Constants.PAUSE_WINDOW_HEIGHT);
        pauseWindow.setX(Constants.SCREEN_WIDTH / Constants.PAUSE_WINDOW_WIDTH_DIVIDER - pauseWindow.getWidth() / Constants.PAUSE_WINDOW_WIDTH_DIVIDER);
        pauseWindow.setY(Constants.SCREEN_HEIGHT / Constants.PAUSE_WINDOW_WIDTH_DIVIDER - pauseWindow.getHeight() / Constants.PAUSE_WINDOW_WIDTH_DIVIDER);
        pauseWindow.add(resumeButton).size(Gdx.graphics.getWidth(), resumeButton.getHeight()).row();
        pauseWindow.add(exitButton).size(Gdx.graphics.getWidth(), exitButton.getHeight()).row();
        endGameWindow = new Window("GAME OVER", uiSkin);
        endGameWindow.setWidth(Constants.SCREEN_WIDTH / Constants.END_GAME_WINDOW_WIDTH_DIVIER);
        endGameWindow.setHeight(Constants.SCREEN_WIDTH / Constants.END_GAME_WINDOW_HEIGHT_DIVIER);
        endGameWindow.setX(Constants.SCREEN_WIDTH / Constants.PAUSE_WINDOW_WIDTH_DIVIDER - endGameWindow.getWidth() / Constants.PAUSE_WINDOW_WIDTH_DIVIDER);
        endGameWindow.setY(Constants.SCREEN_HEIGHT / Constants.PAUSE_WINDOW_WIDTH_DIVIDER - endGameWindow.getHeight() / Constants.PAUSE_WINDOW_WIDTH_DIVIDER);
        pauseButton = new TextButton("PAUSE", uiSkin);
        pauseButton.setHeight(Constants.PAUSE_BUTTON_HEIGHT);
        pauseButton.setWidth(Constants.PAUSE_BUTTON_WIDTH);
        pauseButton.setY(Constants.SCREEN_HEIGHT - pauseButton.getHeight());
        Gdx.input.setInputProcessor(stage);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pause();
            }
        });
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resume();
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (User.getSingletonUser() != null) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());
                } else {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new LoginScreen());
                }
                //bgMusic.stop();
                hide();
                dispose();
            }
        });
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(50));
            }
        });
        stage.addActor(pauseButton);
        //readyGoSound.play();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        stage.addActor(pauseWindow);
        currentState = GameState.PAUSED;
        //bgMusic.pause();
        Gdx.graphics.setContinuousRendering(false);
    }

    @Override
    public void resume() {
        currentState = GameState.RUNNING;
        pauseWindow.remove();
        Gdx.graphics.setContinuousRendering(true);
        //bgMusic.play();
        lastupdateframe = 0.0f;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
