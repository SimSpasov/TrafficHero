package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import bg.ittalents.traffichero.network.LoginRequest;

public class MainMenu implements Screen,Input.TextInputListener{

    private Stage stage;
    private Table table, regTable;
    private TextButton buttonLogin, buttonReg, back, backButton, play, profile, heightScore, levelOne, levelTwo, levelThree;
    private BitmapFont white,black;
    private Label heading;
    private SpriteBatch batch;
    private Sprite background;
    private float time = 0.0f;
    private TextField userNameTextField,passwordTextField,createUsernameTextField, createNicknameTextField, emailTextField, createpasswordTextField, repeatPassTextField;
    //private Sound clickSound;
    private boolean isTrue = true;
    //private Music backgroundMusic;
    private Skin uiSkin;

    public MainMenu() {
        uiSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        white = new BitmapFont(Gdx.files.internal("fonts/white.fnt"));
        black = new BitmapFont(Gdx.files.internal("fonts/black.fnt"));
        heading = new Label("SPEED RACER", uiSkin);
        table = new Window("asdas", uiSkin);
        table.setFillParent(true);
    }

    @Override
    public void show() {

        buttonLogin = new TextButton("Login", uiSkin);
        buttonLogin.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
              // afterLoginTable();
                new LoginRequest("purko", "000000");
            }
        });

        buttonReg = new TextButton("Reg", uiSkin);
        buttonReg.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //regTable();
            }
        });
        backButton = new TextButton("<----",uiSkin);

        back = new TextButton(" <-----",uiSkin);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                afterLoginTable();
            }
        });
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainTable();
            }
        });

        play = new TextButton("   Play   ", uiSkin);
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                chooseLevelTable();
            }
        });
        profile = new TextButton(" Profile ", uiSkin);
        profile.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               // ((Game) Gdx.app.getApplicationListener()).setScreen(new ProfileScreen());
            }
        });
        heightScore = new TextButton("HeightScore", uiSkin);
        heightScore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //((Game) Gdx.app.getApplicationListener()).setScreen(new HeightScoreScreen()
                //);
            }
        });

        levelOne = new TextButton("Level 1", uiSkin);
        levelOne.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //ot tuk puskame klasa za lvl edno
            }
        });
        levelTwo = new TextButton("Level 2", uiSkin);
        levelTwo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //ot tuk puskame klasa za lvl dve
            }
        });
        levelThree = new TextButton("Level 3", uiSkin);
        levelThree.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //ot tuk puskame klasa za lvl tri
            }
        });

        //Creating users textFields
        userNameTextField = new TextField("",uiSkin);
        userNameTextField.setMessageText("Username");
        passwordTextField = new TextField("",uiSkin);
        passwordTextField.setPasswordCharacter('*');
        passwordTextField.setMessageText("Password");
        passwordTextField.isPasswordMode();
        passwordTextField.setPasswordMode(true);

        createUsernameTextField = new TextField("",uiSkin);
        createUsernameTextField.setMessageText("Username");
        createNicknameTextField = new TextField("",uiSkin);
        createNicknameTextField.setMessageText("Nickname");

        emailTextField = new TextField("",uiSkin);
        emailTextField.setMessageText("Email");

        createpasswordTextField= new TextField("",uiSkin);
        createpasswordTextField.setPasswordCharacter('*');
        createpasswordTextField.setMessageText("Password");
        createpasswordTextField.isPasswordMode();
        createpasswordTextField.setPasswordMode(true);

        repeatPassTextField = new TextField("",uiSkin);
        repeatPassTextField.setPasswordCharacter('*');
        repeatPassTextField.setMessageText("Repeat Password");
        repeatPassTextField.isPasswordMode();
        repeatPassTextField.setPasswordMode(true);

        stage.addActor(table);
        if(isTrue){
            isTrue = false;
            mainTable();
        }

        //Creating moving background
        batch = new SpriteBatch();
        Texture texture = new Texture("bg-road.png");
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        background = new Sprite(texture);
        background.setAlpha(100);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    private void backgroundMusic() {
//        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav.wav"));
//        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/backgroundMusic.mp3"));
//        backgroundMusic.setLooping(true);
//        backgroundMusic.play();
    }

//    public void regTable(){
//        stage = new Stage();
//        Gdx.input.setInputProcessor(stage);
//        regTable = new Window("sadas",uiSkin);
//        //regTable.add(Buttons.button).fill(true).row();
//        regTable.add(heading).pad(25).row();
//        regTable.add(createUsernameTextField).fill(true, false).colspan(2).pad(20).row();
//        regTable.add(createNicknameTextField).fill(true, false).colspan(2).pad(20).row();
//        regTable.add(emailTextField).fill(true, false).colspan(2).pad(20).row();
//        regTable.add(createpasswordTextField).fill(true, false).colspan(2).pad(20).row();
//        regTable.add(repeatPassTextField).fill(true, false).colspan(2).pad(20).row();
//        regTable.setFillParent(true);
//        regTable.add(backButton).row();
//        stage.addActor(regTable);
//    }
    public void mainTable(){
        //stage = new Stage();
        //Gdx.input.setInputProcessor(stage);
        Table newTable = new Table(uiSkin);
        newTable.add(heading).pad(25).row();
        newTable.add(userNameTextField).fill(true, false).colspan(2).pad(20).row();
        newTable.add(passwordTextField).fill(true, false).colspan(2).pad(20).row();
        newTable.add(buttonLogin).pad(20).space(buttonLogin.getHeight());
        newTable.add(buttonReg).fill(true, false).padRight(20).space(buttonLogin.getHeight());
        newTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        newTable.debug();
        newTable.setFillParent(true);
        table.addActor(newTable);
        table.debug();
    }

    public void afterLoginTable(){
//        stage = new Stage();
//        Gdx.input.setInputProcessor(stage);
        Table newTable = new Table(uiSkin);
        newTable.add(play).fill(true, false).pad(20).row();
        newTable.add(profile).fill(true, false).pad(20).row();
        newTable.add(heightScore).fill(true, false).pad(20).row();
        newTable.add(backButton).size(100,50).pad(20).row();
        newTable.setFillParent(true);
        table.addActor(newTable);
    }
    public void chooseLevelTable(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Table newTable = new Table(uiSkin);
        newTable.add(levelOne).pad(20).fill(true, false).row();
        newTable.add(levelTwo).pad(20).fill(true, false).row();
        newTable.add(levelThree).pad(20).fill(true, false).row();
        newTable.add(back).pad(20).size(190,70).row();
        newTable.setFillParent(true);
        stage.addActor(newTable);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        this.time +=Gdx.graphics.getDeltaTime();

        if(time>1f)
            time = 0.0f;
        background.setV2(time);
        background.setV(time + 2.4f);
        batch.begin();
        background.draw(batch);
        batch.end();
        //heading.addAction(Actions.addAction(Actions.color(Color.ORANGE, 30)));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        uiSkin.dispose();
        white.dispose();
        black.dispose();
    }
    @Override
    public void input(String text) {}
    @Override
    public void canceled() {}
}
