package bg.ittalents.traffichero.network;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpStatus;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import bg.ittalents.traffichero.main.ChangeUserPassword;
import bg.ittalents.traffichero.main.TextFieldManager;
import bg.ittalents.traffichero.screen.GameScreen;
import bg.ittalents.traffichero.screen.MainScreen;

/**
 * Created by PC on 5.7.2015 ?..
 */
public class ChangePasswordRequest {

    public ChangePasswordRequest(int id, String password) {
        final String loginString = "{\n" +
                "\"id\" : \"" + id + "\",\n" +
                "\"password\" : \"" + password+ "\"\n" +
                "}";
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.POST).url("http://192.168.7.57:8080/SpeedRacer/userManager").build();
        httpRequest.setContent(loginString);
        Gdx.net.sendHttpRequest(httpRequest, responseListener);
    }

    Net.HttpResponseListener responseListener = new Net.HttpResponseListener() {
        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            HttpStatus status = httpResponse.getStatus();
            int statusCode = status.getStatusCode();
            Gdx.app.log("1", "STATUS CODE: " + statusCode);
            if (statusCode == 200) {
                ChangeUserPassword.statusLabel.setText("Welcome: " + User.getSingletonUser().getNickname());
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());
                    }
                });
            }
            if (statusCode == 401) {
                TextFieldManager.getNewPasswordTextField().setColor(Color.RED);
            }
        }

        @Override
        public void failed(Throwable t) {

        }

        @Override
        public void cancelled() {

        }
    };

}
