package ld26;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Game extends com.badlogic.gdx.Game {
	
	private StartScreen startScreen;
	private PlayScreen playScreen;

	@Override
	public void create() {
		playScreen = new PlayScreen(this);
		startScreen = new StartScreen(this);
		setScreen(startScreen);
	}

	public PlayScreen getPlayScreen() {
		return playScreen;
	}

	public StartScreen getStartScreen() {
		return startScreen;
	}

	public void setPlayScreen(PlayScreen playScreen) {
		this.playScreen = playScreen;
	}

	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}
	
	public static void main(String args[]) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 640;
		new LwjglApplication(new Game(), cfg);
	}
}
