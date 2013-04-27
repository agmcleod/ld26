package ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayScreen implements Screen {
	
	private Texture background;
	private SpriteBatch batch;
	private Barrel barrel;
	private Game game;
	private TextureRegion potato;
	private Texture sprites;
	private TextureRegion tower;
	
	
	public PlayScreen(Game game) {
		this.game = game;
	}

	@Override
	public void dispose() {
		background.dispose();
		sprites.dispose();
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
		batch.draw(tower, 64, 0);
		barrel.render(batch);
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
		sprites = new Texture(Gdx.files.internal("assets/potato.png"));
		potato = new TextureRegion(sprites, 0, 0, 64, 128);
		tower = new TextureRegion(sprites, 64, 0, 64, 128);
		barrel = new Barrel(new TextureRegion(sprites, 128, 0, 8, 41));
	}

}
