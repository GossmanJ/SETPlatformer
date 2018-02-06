package com.trifluxsoftware.set;

import com.badlogic.gdx.Game;
import com.trifluxsoftware.set.screens.MainMenu;
import com.trifluxsoftware.set.screens.StartUp;

import java.awt.*;

public class MainGame extends Game {
	public static final String TITLE = "TSD Platformer", VERSION = "v:1.1A";

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public double width = screenSize.width;
    public double height = screenSize.height;

	@Override
	public void create () {
        setScreen(new StartUp(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void pause(){
		super.pause();
	}

	@Override
	public void resume(){
		super.resume();
	}
}
