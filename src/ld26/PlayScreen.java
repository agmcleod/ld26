package ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PlayScreen implements Screen, InputProcessor {
	
	private Texture background;
	private SpriteBatch batch;
	private Barrel barrel;
	private OrthographicCamera camera;
	private Game game;
	private TextureRegion potato;
	private Texture sprites;
	private Vector2 targetPos;
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
	public boolean mouseMoved(int x, int y) {
		Vector3 pos = new Vector3(x, y, 0);
		camera.unproject(pos);
		targetPos.set(pos.x, pos.y);
		return true;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		update();
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
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setAngleViaMouseCoords() {
		Vector2 bPos = barrel.getRotationPos();
		float angle = MathUtils.atan2(targetPos.y - bPos.y, targetPos.x - bPos.x) * (180 / MathUtils.PI);
		if(angle < 0) {
			angle = 360 - (-angle);
		}
		barrel.setAngle(angle);
	}

	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("assets/background.png"));
		batch = new SpriteBatch();
		sprites = new Texture(Gdx.files.internal("assets/potato.png"));
		potato = new TextureRegion(sprites, 0, 0, 64, 128);
		tower = new TextureRegion(sprites, 64, 0, 64, 128);
		barrel = new Barrel(sprites, new TextureRegion(sprites, 128, 0, 32, 8));
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		
		Vector3 startPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(startPos);
		
		targetPos = new Vector2(startPos.x, startPos.y);
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		barrel.fire(this.targetPos);
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
	
	public void update() {
		camera.update();
		// update barrel angle
		setAngleViaMouseCoords();
		barrel.update();
	}

}
