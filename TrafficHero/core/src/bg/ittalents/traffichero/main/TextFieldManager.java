package bg.ittalents.traffichero.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public  class TextFieldManager {
    private static Label nickname = new Label("", Constants.skin);


    //TextField for LoginScreen
    private static TextField usernameLogin = new TextField("", Constants.skin);
    private static TextField passwordLogin = new TextField("", Constants.skin);


    //TextFields for RegistrationScreen
    private static TextField createUsernameTextField = new TextField("", Constants.skin);
    private static TextField createNicknameTextField = new TextField("", Constants.skin);
    private static TextField emailTextField = new TextField("", Constants.skin);
    private static TextField createPasswordTextField = new TextField("", Constants.skin);
    private static TextField repeatPasswordTextField = new TextField("", Constants.skin);

    //TextFields for UpdatePasswordScreen
    private static TextField oldPasswordTextField = new TextField("", Constants.skin);
    private static TextField newPasswordTextField = new TextField("", Constants.skin);

    private static TextField repeatNewPassword = new TextField("", Constants.skin);


    //TextFields for ChangeNicknameScreen
    private static TextField newNicknameTextField = new TextField("", Constants.skin);
    private static TextField repeatNewNicknameTextField = new TextField("", Constants.skin);



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

    public static TextField getNewNicknameTextField() {
        return newNicknameTextField;
    }

    public static void setNewNicknameTextField(TextField newNicknameTextField) {
        TextFieldManager.newNicknameTextField = newNicknameTextField;
    }

    public static TextField getRepeatNewNicknameTextField() {
        return repeatNewNicknameTextField;
    }

    public static void setRepeatNewNicknameTextField(TextField repeatNewNicknameTextField) {
        TextFieldManager.repeatNewNicknameTextField = repeatNewNicknameTextField;
    }

    //Initialized TextFields for LoginScreen
    //
    public TextField loginScreenTextFields(String textFieldName) {
        if (textFieldName.equals("usernameLogin")) {
            getUsernameLogin().setMessageText("Username");
            getUsernameLogin().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            return getUsernameLogin();
        }
        getPasswordLogin().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
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
//           getCreateUsernameTextField().setColor(Color.BLUE);
            getCreateUsernameTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            return getCreateUsernameTextField();
        }if (textFieldName.equals("createNicknameTextField")){
            getCreateNicknameTextField().setMessageText("Nickname");
//            getCreateNicknameTextField().setColor(Color.BLUE);
            getCreateNicknameTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            return getCreateNicknameTextField();
        }if(textFieldName.equals("createPasswordTextField")){
//            getCreatePasswordTextField().setColor(Color.ORANGE);
            getCreatePasswordTextField().setPasswordCharacter('*');
            getCreatePasswordTextField().setMessageText("Password");
            getCreatePasswordTextField().isPasswordMode();
            getCreatePasswordTextField().setPasswordMode(true);
            getCreatePasswordTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            return getCreatePasswordTextField();
        }if (textFieldName.equals("emailTextField")){
//            getEmailTextField().setColor(Color.MAGENTA);
            getEmailTextField().setMessageText("Email");
            getEmailTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            return getEmailTextField();
        }
//        getRepeatPasswordTextField().setColor(Color.ORANGE);
        getRepeatPasswordTextField().setPasswordCharacter('*');
        getRepeatPasswordTextField().setMessageText("Repeat Password");
        getRepeatPasswordTextField().isPasswordMode();
        getRepeatPasswordTextField().setPasswordMode(true);
        getRepeatPasswordTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));

        return getRepeatPasswordTextField();
    }

    //Initialized TextField for UpdatePasswordScreen
    //
    public TextField updatePasswordScreenTextFields(String textFieldName) {
        if (textFieldName.equalsIgnoreCase("oldPasswordTextField")) {
            getOldPasswordTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            getOldPasswordTextField().setPasswordCharacter('*');
            getOldPasswordTextField().setMessageText("Old Password");
            getOldPasswordTextField().isPasswordMode();
            getOldPasswordTextField().setPasswordMode(true);
            return getOldPasswordTextField();
        }
        if (textFieldName.equals("newPasswordTextField")) {
            getNewPasswordTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            getNewPasswordTextField().setPasswordCharacter('*');
            getNewPasswordTextField().setMessageText("New Password");
//            getNewPasswordTextField().isPasswordMode();
            getNewPasswordTextField().setPasswordMode(true);
            return getNewPasswordTextField();
        }
        getRepeatNewPassword().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
        getRepeatNewPassword().setPasswordCharacter('*');
        getRepeatNewPassword().setMessageText("Repeat Password");
//        getRepeatPasswordTextField().isPasswordMode();
        getRepeatNewPassword().setPasswordMode(true);
        return getRepeatNewPassword();
    }

    //Initialized TextField for ChangeNicknameScreen

    public TextField changeNicknameScreenTextFields(String textFieldName){
        if (textFieldName.equalsIgnoreCase("New Nickname")) {
            getNewNicknameTextField().setMessageText("new Nickname");
            getNewNicknameTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
            return getNewNicknameTextField();
        }
        if(textFieldName.equals("repeat Nickname"))
            getRepeatNewNicknameTextField().setMessageText("repeat Nickname");
        getRepeatNewNicknameTextField().addAction(Actions.alpha(Constants.DEFAULT_ALPHA));
        return getRepeatNewNicknameTextField();
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
        getEmailTextField().setMessageText("must contain > 4 letters && '@'");
    }
    public void setCreatePasswordInvalidInput(){
        getCreatePasswordTextField().setColor(Color.RED);
        getCreatePasswordTextField().setText("");
        getCreatePasswordTextField().setMessageText("must contain > 4 letters or\n passwords doesn't match");
    }
    public void setRepeatPasswordInvalidInput(){
        getRepeatPasswordTextField().setColor(Color.RED);
        getRepeatPasswordTextField().setText("");
        getRepeatPasswordTextField().setMessageText("must contain > 4 letters or\n passwords doesn't match");
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