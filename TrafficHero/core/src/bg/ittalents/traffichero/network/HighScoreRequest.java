package bg.ittalents.traffichero.network;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpStatus;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import bg.ittalents.traffichero.main.ChangeUserPassword;
import bg.ittalents.traffichero.main.TextFieldManager;
import bg.ittalents.traffichero.screen.HighScoreScreen;
import bg.ittalents.traffichero.screen.MainScreen;

public class HighScoreRequest {

    public HighScoreRequest() {
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url("http://192.168.7.57:8080/SpeedRacer/userManager").build();
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
                JsonElement element = gson.fromJson(responseJson, JsonArray.class);
                JsonObject obj = element.getAsJsonObject();
                final String[] nicknameArray = new String[10];
                final int[] highScoreArray = new int[10];
                Gdx.app.log("TAG", "" + obj.getAsJsonArray().get(0).getAsJsonObject().get("nickname").getAsString());
                for (int i = 0; i< 10; i++) {
                    nicknameArray[i] = obj.getAsJsonArray().get(i).getAsJsonObject().get("nickname").getAsString();
                    highScoreArray[i] = obj.getAsJsonArray().get(i).getAsJsonObject().get("highscore").getAsInt();
                }
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new HighScoreScreen(nicknameArray, highScoreArray));
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
