package bg.ittalents.traffichero.network;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpStatus;

import bg.ittalents.traffichero.screen.ChangeProfileScreen;
import bg.ittalents.traffichero.screen.MainScreen;


public class ChangeNicknameRequest {

    private String tempNickname;

    public ChangeNicknameRequest(int id, String nickname) {
        final String loginString = "{\n" +
                "\"id\" : \"" + id + "\",\n" +
                "\"nickname\" : \"" + nickname+ "\"\n" +
                "}";
        tempNickname = nickname;
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
                User.getSingletonUser().setNickname(tempNickname);
                ChangeProfileScreen.statusLabel.setText("Welcome " + User.getSingletonUser().getNickname());
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new MainScreen());
                    }
                });
            }
            if (statusCode == 401) {
                ChangeProfileScreen.statusLabel.setText("Welcome " + User.getSingletonUser().getNickname());
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