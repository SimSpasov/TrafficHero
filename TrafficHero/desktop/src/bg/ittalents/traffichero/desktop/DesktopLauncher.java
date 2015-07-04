package bg.ittalents.traffichero.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import bg.ittalents.traffichero.main.TrafficGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new TrafficGame(), config);
		config.width = 420;
        config.height = 640;
	}
}
