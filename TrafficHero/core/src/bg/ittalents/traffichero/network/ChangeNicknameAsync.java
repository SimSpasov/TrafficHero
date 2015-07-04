package bg.ittalents.traffichero.network;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpStatus;


public class ChangeNicknameAsync{

    private int id;
    private String nickname;

    public ChangeNicknameAsync(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url("http://www.mocky.io/v2/5597dadf634471920557672a").build();
        Gdx.net.sendHttpRequest(httpRequest, responseListener);
    }

    Net.HttpResponseListener responseListener = new Net.HttpResponseListener() {
        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            HttpStatus status = httpResponse.getStatus();
            int statusCode = status.getStatusCode();
            Gdx.app.log("1", "STATUS CODE: " + statusCode);
        }

        @Override
        public void failed(Throwable t) {
            Gdx.app.log("FAILED TAG", "REQUEST FAILED!");
        }

        @Override
        public void cancelled() {
            Gdx.app.log("CANCEL TAG", "REQUEST CANCELED!");
        }
    };

}
