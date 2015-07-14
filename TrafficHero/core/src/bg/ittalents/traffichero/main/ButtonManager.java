package bg.ittalents.traffichero.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import bg.ittalents.traffichero.network.LoginRequest;
import bg.ittalents.traffichero.screen.*;

public class ButtonManager {


    private TextFieldManager fieldManager = new TextFieldManager();
    private static boolean receivesEmailNotifications;
    public ButtonManager() {

    }

    public static boolean isReceivesEmailNotifications() {
        return receivesEmailNotifications;
    }

    public static void setReceivesEmailNotifications(boolean receivesEmailNotifications) {
        ButtonManager.receivesEmailNotifications = receivesEmailNotifications;
    }


    //Creating Buttons For LoginScreen
    public TextButton loginButtons(String buttonName) {
        if (buttonName.equals("loginButton")) {
            TextButton loginButton = new TextButton("Login",Constants.skin);
            loginButton.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            loginButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    new LoginRequest(TextFieldManager.getUsernameLogin().getText(), TextFieldManager.getPasswordLogin().getText());
                    boolean  validation = false;
                    MainScreen.getGreetingLabel().setText(TextFieldManager.getUsernameLogin().getText());
                    if(TextFieldManager.getUsernameLogin().getText().length() < Constants.MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGTH) {
                        fieldManager.setUsernameInvalidInput();
                        validation = false;
                    }if(TextFieldManager.getPasswordLogin().getText().length() < Constants.MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGTH) {
                        fieldManager.setPasswordInvalidInput();
                    }
                    else  if(validation) {
                        MainScreen.getGreetingLabel().setText("Welcome " + TextFieldManager.getUsernameLogin().getText());
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());
                    }
                }
            });
            return loginButton;
        } else if (buttonName.equals("registrationButton")){
            TextButton registrationButton = new TextButton("Registration",Constants.skin);
            registrationButton.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            registrationButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new RegistrationScreen());
                }
            });
            return registrationButton;
        }else if (buttonName.equals("registerButton")) {
            TextButton regButton = new TextButton("Register",Constants.skin);
            regButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());
                }
            });
            return regButton;
        }else if(buttonName.equals("playOffline")){
            TextButton playOfflineButton = new TextButton("Play Offline",Constants.skin);
            playOfflineButton.addAction(Actions.alpha(Constants.CUSTOM_ALPHA));
            playOfflineButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if(LoginScreen.getBackgroundMusic()!= null) {
                        LoginScreen.getBackgroundMusic().pause();
                    }
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(50));
                }
            });
            return playOfflineButton;
        }
        return null;
    }
    public TextButton afterLoginScreenButtons(String buttonName) {
        if (buttonName.equals("Play")) {
            TextButton playButton = new TextButton("PLAY",Constants.skin);

            playButton.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            playButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    ((Game) Gdx.app.getApplicationListener()).setScreen(new ChooseLevelScreen());
                }
            });
            return playButton;
        } else if (buttonName.equals("Change Profile")) {
            TextButton changeProfileButton= new TextButton("CHANGE PROFILE",Constants.skin);
            changeProfileButton.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            changeProfileButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new ChangeProfileScreen());
                }
            });
            return changeProfileButton;
        } else if (buttonName.equals("HeightScore")) {
            TextButton heightScoreButton= new TextButton("HIGH SCORE",Constants.skin);
            heightScoreButton.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            heightScoreButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new HighScoreScreen());
                }
            });
            return heightScoreButton;
        }
        return null;
    }

    public TextButton levelButtons(String buttonName) {
        if (buttonName.equals("Level 1")) {
            TextButton levelOneButton = new TextButton("Level 1",Constants.skin);
            levelOneButton.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            levelOneButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if(LoginScreen.getBackgroundMusic()!= null) {
                        LoginScreen.getBackgroundMusic().pause();
                    }
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(50));
                }
            });
            return levelOneButton;
        } else if (buttonName.equals("Level 2")) {
            TextButton levelTwoButton = new TextButton("Level 2",Constants.skin);
            levelTwoButton.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            levelTwoButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    LoginScreen.getBackgroundMusic().pause();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(65));
                }
            });
            return levelTwoButton;
        } else if (buttonName.equals("Level 3")) {
            TextButton levelThreeButton = new TextButton("Level 3",Constants.skin);
            levelThreeButton.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            levelThreeButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    LoginScreen.getBackgroundMusic().pause();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(75));
                }
            });
            return levelThreeButton;
        }
        return null;
    }

    public TextButton settingsScreenButtons(String buttonName) {
        if (buttonName.equals("Change Nickname")) {
            TextButton changeNicknameButton = new TextButton(" Change\nNickname", Constants.skin);
            changeNicknameButton.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            changeNicknameButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    ((Game) Gdx.app.getApplicationListener()).setScreen(new bg.ittalents.traffichero.screen.ChangeNicknameScreen());
                }
            });
            return changeNicknameButton;
        }
        {
            TextButton changePassButton = new TextButton(" Change\nPassword", Constants.skin);
            changePassButton.addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            changePassButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new ChangeUserPassword());
                }
            });
            return changePassButton;
        }
    }



    public CheckBox settingsScreenCheckBoxes(final String checkBoxName){
        if(checkBoxName.equals("emailCheckbox")){
            final CheckBox emailCheckBox = new CheckBox("Send email if somebody\n beat your HeightScore?", Constants.skin);

            emailCheckBox.setChecked(true);
            emailCheckBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (emailCheckBox.isChecked()){
                        setReceivesEmailNotifications(true);
                    }else{
                        setReceivesEmailNotifications(false);
                    }
                }
            });
            return emailCheckBox;
        }
        if(checkBoxName.equals("backgroundCheckbox")){
            final CheckBox backgroundMusicCheckBox= new CheckBox("Background Music", Constants.skin);
            backgroundMusicCheckBox.setChecked(true);
            backgroundMusicCheckBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (backgroundMusicCheckBox.isChecked()) {
                        LoginScreen.getBackgroundMusic().play();

                    } else {
                        LoginScreen.getBackgroundMusic().pause();

                    }
                }
            });
            return backgroundMusicCheckBox;
        }
        return null;
    }
}