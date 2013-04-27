package ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ReplayScreen implements Screen {
	
	private Texture background;
	private SpriteBatch batch;
	private BitmapFont font;
	private Game game;
	private boolean loss;
	private int score;
	private BitmapFont titleFont;
	
	public ReplayScreen(Game game) {
		this.game = game;
		score = 0;
	}

	@Override
	public void dispose() {
		font.dispose();
		background.dispose();
		batch.dispose();
		titleFont.dispose();
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
		String s = String.valueOf(score);
		if(loss) {
			font.draw(batch, "The base has been destroyed", 150, Gdx.graphics.getHeight() - 64);
			font.draw(batch, "But you got the following score:", 140, Gdx.graphics.getHeight() - 104);
		}
		else {
			font.draw(batch, "You won the game, by stopping the enemy!", 80, Gdx.graphics.getHeight() - 64);
		}
		
		titleFont.draw(batch, String.valueOf(score), 420 - (s.length() * 34), Gdx.graphics.getHeight() - 150);
		font.draw(batch, "Press enter to play again, or escape to quit", 80, Gdx.graphics.getHeight() - 210);
		batch.end();
		
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			game.setScreen(game.getPlayScreen());
			score = 0;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}
	
	public void setLoss(boolean loss) {
		this.loss = loss;
	}
	
	public void setScore(int s) {
		score = s;
	}

	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("assets/background.png"));
		font = new BitmapFont(Gdx.files.internal("assets/xolo24.fnt"), Gdx.files.internal("assets/xolo24.png"), false);
		batch = new SpriteBatch();
		titleFont = new BitmapFont(Gdx.files.internal("assets/xolotitledefault.fnt"), Gdx.files.internal("assets/xolotitledefault.png"), false);
	}

}
