package bg.ittalents.traffichero.network;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpStatus;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import bg.ittalents.traffichero.main.TextFieldManager;
import bg.ittalents.traffichero.screen.GameScreen;
import bg.ittalents.traffichero.screen.LoginScreen;
import bg.ittalents.traffichero.screen.MainScreen;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest {

    Map<String, String> parameters = new HashMap<String, String>();

    public LoginRequest(String username, String password) {
//        parameters.put("username", username);
//        parameters.put("password", password);
        final String loginString = "{\n" +
                "\"username\" : \"" + username + "\",\n" +
                "\"password\" : \"" + password + "\"\n" +
                "}";
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        //Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.POST).url("192.168.7.57:8080/SpeedRacer/loginManager/").build();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.POST).url("http://192.168.7.57:8080/SpeedRacer/loginManager").build();
        //httpRequest.setContent(HttpParametersUtils.convertHttpParameters(parameters));
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
                String responseJson = httpResponse.getResultAsString();
                Gson gson = new Gson();
                JsonElement element = gson.fromJson(responseJson, JsonElement.class);
                JsonObject obj = element.getAsJsonObject();
                int id = obj.get("id").getAsInt();
                String nickname = obj.get("nickname").getAsString();
                int highscore = obj.get("highscore").getAsInt();
                int money = obj.get("money").getAsInt();
                int maxLevel = obj.get("maxlevel").getAsInt();
                User.makeSingletonUser(id, nickname, highscore, money, maxLevel);
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        MainScreen.greetingLabel.setText("Welcome " + User.getSingletonUser().getNickname());
                                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());
                    }
                });
            }
            if (statusCode == 302) {
                LoginScreen.statusLabel.setText("Username doesn't exist");
            }
            if (statusCode == 401) {
                LoginScreen.statusLabel.setText("Invalid password");
            }
        }

        @Override
        public void failed(Throwable t) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    LoginScreen.statusLabel.setText("Connection timeout!");
                }
            });
        }

        @Override
        public void cancelled() {

        }
    };
}
