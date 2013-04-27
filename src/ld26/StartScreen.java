package ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartScreen implements Screen, InputProcessor {
	
	private Texture background;
	private SpriteBatch batch;
	private BitmapFont font;
	private Game game;
	private Texture screen;
	private int screenIndex;
	private BitmapFont titleFont;
	
	public StartScreen(Game game) {
		this.game = game;
	}

	@Override
	public void dispose() {
		background.dispose();
		font.dispose();
		titleFont.dispose();
		screen.dispose();
		batch.dispose();
	}

	@Override
	public void hide() {
		
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
			game.setScreen(game.getPlayScreen());
		}
		
		batch.begin();
		batch.draw(background, 0, 0);
		if(screenIndex == 0) {
			titleFont.draw(batch, "Protect The Potato Home Base!!!", 30, Gdx.graphics.getHeight() - 32);
			font.draw(batch, "Click to continue.", Gdx.graphics.getWidth() - 300, 32);
			batch.draw(screen, 180, 0);
		}
		else if(screenIndex == 1) {
			font.draw(batch, "The tater-haters are coming after our base,", 70, Gdx.graphics.getHeight() - 100);
			font.draw(batch, "that we have nicely constructed as a potato.", 50, Gdx.graphics.getHeight() - 145);
			font.draw(batch, "With your mouse, use the turret provided", 80, Gdx.graphics.getHeight() - 235);
			font.draw(batch, "and save our base!", 235, Gdx.graphics.getHeight() - 280);
		}
		else {
			game.setScreen(game.getPlayScreen());
		}
		
		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("assets/background.png"));
		font = new BitmapFont(Gdx.files.internal("assets/xolo24.fnt"), Gdx.files.internal("assets/xolo24.png"), false);
		titleFont = new BitmapFont(Gdx.files.internal("assets/xolotitledefault.fnt"), Gdx.files.internal("assets/xolotitledefault.png"), false);
		batch = new SpriteBatch();
		screen = new Texture(Gdx.files.internal("assets/screen.png"));
		screenIndex = 0;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		screenIndex++;
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
