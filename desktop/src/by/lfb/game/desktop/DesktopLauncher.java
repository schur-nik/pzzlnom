package by.lfb.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import by.lfb.game.Pzzlnom;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Pzzlnom";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new Pzzlnom(), config);
	}
}
