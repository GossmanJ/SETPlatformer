package com.trifluxsoftware.set.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trifluxsoftware.set.MainGame;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.width;
        double height = screenSize.height;

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.title = MainGame.TITLE + " " + MainGame.VERSION;
		cfg.vSyncEnabled = true;
		cfg.useGL30 = true;
		cfg.width = 800; //(int)width
		cfg.height = 600; //(int)height
		//cfg.fullscreen = true;

		new LwjglApplication(new MainGame(), cfg);
	}
}
