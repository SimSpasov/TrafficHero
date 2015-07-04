package bg.ittalents.traffichero.main;

import com.badlogic.gdx.Game;

import bg.ittalents.traffichero.screen.SplashScreen;

public class TrafficGame extends Game {
	
	@Override
	public void create () {
		setScreen(new SplashScreen());
	}
}
