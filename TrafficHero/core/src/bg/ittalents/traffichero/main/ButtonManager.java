package bg.ittalents.traffichero.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import bg.ittalents.traffichero.screen.*;

public class ButtonManager {
    public final static Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
    bg.ittalents.traffichero.main.TextFieldManager fieldManager = new bg.ittalents.traffichero.main.TextFieldManager();
    private static boolean isBackgroundMusicOn = true;
    private static boolean isButtonSoundOn ;

    public ButtonManager() {

    }

    public static boolean isBackgroundMusicOn() {
        return isBackgroundMusicOn;
    }

    public static void setIsBackgroundMusicOn(boolean isBackgroundMusicOn) {
        ButtonManager.isBackgroundMusicOn = isBackgroundMusicOn;
    }

    public static boolean isButtonSoundOn() {
        return isButtonSoundOn;
    }

    public static void setIsButtonSoundOn(boolean isButtonSoundOn) {
        bg.ittalents.traffichero.main.ButtonManager.isButtonSoundOn = isButtonSoundOn;
    }

    public TextButton loginButtons(String buttonName) {

        if (buttonName.equals("Login")) {
        TextButton loginButton = new TextButton("Login",skin);
            loginButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if(bg.ittalents.traffichero.main.TextFieldManager.getUsernameLogin().getText().length() < UpdateUserPassword.MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGHT){
                        fieldManager.setUsernameInvalidInput();

                    if(TextFieldManager.getPasswordLogin().getText().length() < UpdateUserPassword.MIN_PASSWORD_USERNAME_AND_NICKNAME_LENGHT) {
                        fieldManager.setPasswordInvalidInput();
                    }}else{
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuAfterLogin());
                    }
                }
            });
            return loginButton;
        } else if (buttonName.equals("Registration")){
            TextButton registrationButton = new TextButton("Registration",skin);
            registrationButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new RegistrationScreen());
                }
            });
        return registrationButton;
    }else if (buttonName.equals("Register")) {
            TextButton regButton = new TextButton("Register",skin);
                regButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuAfterLogin());
                    }
                });
                return regButton;
            }
            return null;
        }
    public TextButton afterLoginScreenButtons(String buttonName) {
        if (buttonName.equals("Play")) {
            TextButton playButton = new TextButton("Play",skin);
            playButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    ((Game) Gdx.app.getApplicationListener()).setScreen(new ChooseLevelScreen());
                }
            });
            return playButton;
        } else if (buttonName.equals("Change Profile")) {
            TextButton changeProfileButton= new TextButton("Change Profile",skin);
            changeProfileButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new SettingsScreen());
                }
            });
            return changeProfileButton;
        } else if (buttonName.equals("HeightScore")) {
            TextButton heightScoreButton= new TextButton("HeightScore",skin);
            heightScoreButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new HeightScoreScreen());
                }
            });
            return heightScoreButton;
        } else if (buttonName.equals("Logout")) {
            TextButton logoutButton= new TextButton("Logout",skin);
            logoutButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new LoginScreen());
                }
            });
            return logoutButton;
        }
        return null;
    }

    public TextButton levelButtons(String buttonName) {
        if (buttonName.equals("Level 1")) {
            TextButton levelOneButton = new TextButton("Level 1",skin);
            levelOneButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
                }
            });
            return levelOneButton;
        } else if (buttonName.equals("Level 2")) {
            TextButton levelTwoButton = new TextButton("Login",skin);
            levelTwoButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new LoginScreen());
                }
            });
            return levelTwoButton;
        } else if (buttonName.equals("Level 3")) {
            TextButton levelThreeButton = new TextButton("Login",skin);
            levelThreeButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new LoginScreen());
                }
            });
            return levelThreeButton;
        }
        return null;
    }

    public TextButton settingsScreenButtons(String buttonName) {
        if (buttonName.equals("Change Nickname")) {
            TextButton changeNicknameButton = new TextButton(" Change\nNickname", skin);
            changeNicknameButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    ((Game) Gdx.app.getApplicationListener()).setScreen(new bg.ittalents.traffichero.screen.ChangeNicknameScreen());
                }
            });
            return changeNicknameButton;
        }
        {
            TextButton changePassButton = new TextButton(" Change\nPassword", skin);
            changePassButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new UpdateUserPassword());
                }
            });
            return changePassButton;
        }
    }



    public CheckBox settingsScreenCheckBoxes(String checkBoxName){
        if(checkBoxName.equals("Send email if somebody beat your HeightScore?")){
            final CheckBox emailCheckBox = new CheckBox("Send email if somebody\n beat your HeightScore?", skin);

            emailCheckBox.setChecked(true);
            emailCheckBox.setColor(Color.BLUE);

            emailCheckBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if(emailCheckBox.isChecked()){

                    }
                }
            });
            return emailCheckBox;
        }
        if(checkBoxName.equals("Sounds Effect")){
            final CheckBox soundEffectCheckBox= new CheckBox("Sounds Effect", skin);
            soundEffectCheckBox.setChecked(true);
            soundEffectCheckBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if(soundEffectCheckBox.isChecked()){
                        setIsButtonSoundOn(true);
                    }else{
                        setIsButtonSoundOn(false);
                    }
                }
            });
            return soundEffectCheckBox;
        }if(checkBoxName.equals("Background music")){
            final CheckBox backgroundMusicCheckBox= new CheckBox("Background Music", skin);
            backgroundMusicCheckBox.setChecked(true);
            backgroundMusicCheckBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if(backgroundMusicCheckBox.isChecked()){
                        setIsBackgroundMusicOn(true);
                    }else{
                        setIsBackgroundMusicOn(false);
                    }
                }
            });
            return backgroundMusicCheckBox;
        }
        return null;
    }


}