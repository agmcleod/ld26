package ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayScreen implements Screen {
	
	private Texture background;
	private SpriteBatch batch;
	private Game game;
	private Texture potato;
	
	public PlayScreen(Game game) {
		this.game = game;
	}

	@Override
	public void dispose() {
		background.dispose();
		potato.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.draw(potato, 0, 0);
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
	public void show() {
		background = new Texture(Gdx.files.internal("assets/background.png"));
		batch = new SpriteBatch();
		potato = new Texture(Gdx.files.internal("assets/potato.png"));
	}

}
