package bg.ittalents.traffichero.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import bg.ittalents.traffichero.main.Constants;

public class HighScoreScreen implements Screen {

    private String[] nicknames = new String[10];
    private int[] highscores = new int[10];
    private SpriteBatch batch;
    public HighScoreScreen(){

    }
    public HighScoreScreen(String[] nicknameArray, int[] highScoreArray){
//        ,String positionTwo,String positionThree,String positionFour,String positionFive,
//                         String positionSix,String positionSeven,String positionEight,String positionNine,String positionTen){

//    this.positionOne = positionOne;
//    this.positionTwo = positionTwo;
//    this.positionThree = positionThree;
//    this.positionFour = positionFour;
//    this.positionFive = positionFive;
//    this.positionSix = positionSix;
//    this.positionSeven = positionSeven;
//    this.positionEight = positionEight;
//    this.positionNine = positionNine;
//    this.positionTen = positionTen;

        this.nicknames = nicknameArray;
        this.highscores = highScoreArray;
    }


    private Stage stage = new Stage();
    Table scoreTable = new Table(Constants.skin);

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        batch = new SpriteBatch();
        Table table = new Table(Constants.skin);
        table.setFillParent(true);

        for(int index = 1; index < 11; index++){
            String name = nicknames[index-1];
            int highScore = highscores[index-1];


            Label place = new Label(index + ". " + name + " " + highScore,Constants.skin);

            table.add(place).row();


            table.debug();
            stage.addActor(table);
        }

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new ChangeProfileScreen());
        }
        batch.begin();
        batch.draw(Constants.backgroundTexture, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        batch.end();

        stage.act();
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