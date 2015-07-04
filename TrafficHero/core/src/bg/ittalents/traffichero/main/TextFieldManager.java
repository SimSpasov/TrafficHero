package bg.ittalents.traffichero.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class TextFieldManager {
    private static Label nickname = new Label("", bg.ittalents.traffichero.main.ButtonManager.skin);


    //TextField for LoginScreen
    private static TextField usernameLogin = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);
    private static TextField passwordLogin = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);


    //TextFields for RegistrationScreen
    private static TextField createUsernameTextField = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);
    private static TextField createNicknameTextField = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);
    private static TextField emailTextField = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);
    private static TextField createPasswordTextField = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);
    private static TextField repeatPasswordTextField = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);

    //TextFields for UpdatePasswordScreen
    private static TextField oldPasswordTextField = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);
    private static TextField newPasswordTextField = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);

    private static TextField repeatNewPassword = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);


    //TextFields for ChangeNicknameScreen
    private static TextField newNicknameTextField = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);
    private static TextField repeatNewNicknameTextField = new TextField("", bg.ittalents.traffichero.main.ButtonManager.skin);



    public TextFieldManager() {

    }


    public static TextField getUsernameLogin() {
        return usernameLogin;
    }

    public static TextField getPasswordLogin() {
        return passwordLogin;
    }

    public static TextField getCreateUsernameTextField() {
        return createUsernameTextField;
    }

    public static TextField getCreateNicknameTextField() {
        return createNicknameTextField;
    }

    public static TextField getEmailTextField() {
        return emailTextField;
    }

    public static TextField getCreatePasswordTextField() {
        return createPasswordTextField;
    }

    public static TextField getRepeatPasswordTextField() {
        return repeatPasswordTextField;
    }

    public static TextField getOldPasswordTextField() {
        return oldPasswordTextField;
    }

    public static TextField getNewPasswordTextField() {
        return newPasswordTextField;
    }
    public static TextField getRepeatNewPassword() {
        return repeatNewPassword;
    }

    //Initialized TextFields for LoginScreen
    //
    public TextField loginScreenTextFields(String textFieldName) {
        if (textFieldName.equals("usernameLogin")) {
            getUsernameLogin().setMessageText("Username");
            return getUsernameLogin();
        }
            getPasswordLogin().setPasswordCharacter('*');
            getPasswordLogin().setMessageText("Password");
            getPasswordLogin().isPasswordMode();
            getPasswordLogin().setPasswordMode(true);
            return getPasswordLogin();
    }

    //Initialized TextField for RegistrationScreen
    //
    public TextField registrationScreenTextFields(String textFieldName) {
       if(textFieldName.equals("createUsernameTextField")){
           getCreateUsernameTextField().setMessageText("Username");
           getCreateUsernameTextField().setColor(Color.BLUE);
           return getCreateUsernameTextField();
       }if (textFieldName.equals("createNicknameTextField")){
            getCreateNicknameTextField().setMessageText("Nickname");
            getCreateNicknameTextField().setColor(Color.BLUE);
            return getCreateNicknameTextField();
        }if(textFieldName.equals("createPasswordTextField")){
            getCreatePasswordTextField().setColor(Color.ORANGE);
            getCreatePasswordTextField().setPasswordCharacter('*');
            getCreatePasswordTextField().setMessageText("Password");
            getCreatePasswordTextField().isPasswordMode();
            getCreatePasswordTextField().setPasswordMode(true);
            return getCreatePasswordTextField();
        }if (textFieldName.equals("emailTextField")){
            getEmailTextField().setColor(Color.MAGENTA);
            getEmailTextField().setMessageText("Email");
            return getEmailTextField();
        }
        getRepeatPasswordTextField().setColor(Color.ORANGE);
        getRepeatPasswordTextField().setPasswordCharacter('*');
        getRepeatPasswordTextField().setMessageText("Repeat Password");
        getRepeatPasswordTextField().isPasswordMode();
        getRepeatPasswordTextField().setPasswordMode(true);

        return getRepeatPasswordTextField();
    }

    //Initialized TextField for UpdatePasswordScreen
    //
    public TextField updatePasswordScreenTextFields(String textFieldName) {
        if (textFieldName.equalsIgnoreCase("old password")) {
            getOldPasswordTextField().setColor(Color.ORANGE);
            getOldPasswordTextField().setPasswordCharacter('*');
            getOldPasswordTextField().setMessageText("Password");
            getOldPasswordTextField().isPasswordMode();
            getOldPasswordTextField().setPasswordMode(true);
            return getOldPasswordTextField();
        }
        if (textFieldName.equals("New Password")) {
            getNewPasswordTextField().setColor(Color.GRAY);
            getNewPasswordTextField().setPasswordCharacter('*');
            getNewPasswordTextField().setMessageText("New Password");
//            getNewPasswordTextField().isPasswordMode();
            getNewPasswordTextField().setPasswordMode(true);
            return getNewPasswordTextField();
        }
        getRepeatNewPassword().setColor(Color.GRAY);
        getRepeatNewPassword().setPasswordCharacter('*');
        getRepeatNewPassword().setMessageText("Repeat Password");
//        getRepeatPasswordTextField().isPasswordMode();
        getRepeatNewPassword().setPasswordMode(true);
        return getRepeatNewPassword();
    }

    //Initialized TextField for ChangeNicknameScreen
    //
    public TextField changeNicknameScreenTextFields(String textFieldName){
        if (textFieldName.equalsIgnoreCase("New Nickname")) {
            newNicknameTextField.setMessageText("new Nickname");
            return newNicknameTextField;
        }
        if(textFieldName.equals("repeat Nickname"))

            repeatNewNicknameTextField.setMessageText("repeat Nickname");
            return repeatNewNicknameTextField;
        }

    public void setUsernameInvalidInput(){
        getUsernameLogin().setText("");
        getUsernameLogin().setMessageText("Invalid Username");
    }
    public void setPasswordInvalidInput(){
        getPasswordLogin().setText("");
        getPasswordLogin().setMessageText("Invalid Password");
    }
    public void setCreateUsernameInvalidInput(){
        getCreateUsernameTextField().setColor(Color.RED);
        getCreateUsernameTextField().setText("");
        getCreateUsernameTextField().setMessageText("must contain > 4 letters");
    }

    public void setCreateNicknameInvalidInput(){
        getCreateNicknameTextField().setColor(Color.RED);
        getCreateNicknameTextField().setText("");
        getCreateNicknameTextField().setMessageText("must contain > 4 letters");
    }
    public void setEmalInvalidInput(){
        getEmailTextField().setColor(Color.RED);
        getEmailTextField().setText("");
        getEmailTextField().setMessageText("must contain > 4 letters\n && '@'");
    }
    public void setCreatePasswordInvalidInput(){
        getCreatePasswordTextField().setColor(Color.RED);
        getCreatePasswordTextField().setText("");
        getCreatePasswordTextField().setMessageText("must contain > 4 letters\nor passwords doesn't match");
    }
    public void setRepeatPasswordInvalidInput(){
        getRepeatPasswordTextField().setColor(Color.RED);
        getRepeatPasswordTextField().setText("");
        getRepeatPasswordTextField().setMessageText("must contain > 4 letters\nor passwords doesn't match");
    }
    public void setOldPasswordInvalidInput(){
       getOldPasswordTextField().setColor(Color.RED);
        getOldPasswordTextField().setText("");
        getOldPasswordTextField().setMessageText("Passwords doesn't match");
    }


    public void setNewPassWordInvalidInput(){
        getNewPasswordTextField().setColor(Color.RED);
        getNewPasswordTextField().setText("");
        getNewPasswordTextField().setMessageText("must contain > 4 letters\nor passwords doesn't match");
    }
    public void setRepeatNewPasswordInvalidInput() {
        getRepeatNewPassword().setColor(Color.RED);
        getRepeatNewPassword().setText("");
        getRepeatNewPassword().setMessageText("must contain > 4 letters\nor passwords doesn't match");
    }

    public void deleteTextFieldsText(){
        getCreateUsernameTextField().setText("");
        getCreateNicknameTextField().setText("");
        getEmailTextField().setText("");
        getCreatePasswordTextField().setText("");
        getRepeatPasswordTextField().setText("");
        getOldPasswordTextField().setText("");
        getNewPasswordTextField().setText("");
        getRepeatNewPassword().setText("");
        getUsernameLogin().setText("");
        getPasswordLogin().setText("");
    }
}