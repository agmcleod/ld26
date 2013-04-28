package ld26;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class PlayScreen implements Screen, InputProcessor {
	
	private Array<Particle> activeParticles;
	private Texture background;
	private SpriteBatch batch;
	private Barrel barrel;
	private OrthographicCamera camera;
	private Music chordLoop;
	private Sound explosionSound;
	private Sound fireSound;
	private float fireTimer = 0;
	private BitmapFont font;
	private Game game;
	private Array<Missle> missles;
	private float missleCooldown = 1.5f;
	private TextureRegion missleRegion;
	private float missleTimer = 0;
	private float musicTimer = 0;
	private boolean playingFirstTrack;
	private TextureRegion potato;
	private int potatoHealth;
	private ShapeRenderer renderer;
	private int score;
	private Music simpleLoop;
	private Texture sprites;
	private TextureRegion spud;
	private Vector2 targetPos;
	private TextureRegion tower;
	
	
	public PlayScreen(Game game) {
		this.game = game;
	}
	
	public void checkBulletCollision() {
		Array<Bullet> bullets = barrel.getBullets();
		if(bullets.size > 0 && missles.size > 0) {
			Iterator<Bullet> ib = bullets.iterator();
			while(ib.hasNext()) {
				Bullet b = ib.next();
				Iterator<Missle> im = missles.iterator();
				while(im.hasNext()) {
					Missle m = im.next();
					if(b.getAABB().overlaps(m.getAABB())) {
						Vector2 pos = m.getPos();
						pos.x += 16;
						pos.y += 16;
						if(m.deincrementHealth()) {
							im.remove();
							explosionSound.play(0.5f);
							incrementScore();
						}
						ib.remove();
						
						spawnParticles(pos);
					}
				}
			}
		}
	}
	
	public void checkMissleCollision() {
		Iterator<Missle> im = missles.iterator();
		Rectangle potatoRect = new Rectangle(0, 0, 64, 128);
		while(im.hasNext()) {
			Missle m = im.next();
			if(potatoRect.overlaps(m.getAABB())) {
				im.remove();
				potatoHealth--;
			}
		}
		
		if(potatoHealth <= 0) {
			ReplayScreen r = game.getReplayScreen();
			r.setLoss(true);
			r.setScore(score);
			game.setScreen(r);
		}
	}
	
	public void debug() {
		renderer.begin(ShapeType.Rectangle);
		renderer.setColor(Color.WHITE);
		Iterator<Missle> it = missles.iterator();
		while(it.hasNext()) {
			it.next().debug(renderer);
		}
		Iterator<Bullet> itb = barrel.getBullets().iterator();
		while(itb.hasNext()) {
			itb.next().debug(renderer);
		}
		renderer.end();
	}

	@Override
	public void dispose() {
		background.dispose();
		sprites.dispose();
		font.dispose();
		batch.dispose();
		chordLoop.dispose();
		simpleLoop.dispose();
		renderer.dispose();
		explosionSound.dispose();
		fireSound.dispose();
	}
	
	public void fireMissle() {
		missles.add(Missle.spawn(sprites));
	}

	@Override
	public void hide() {
		
	}
	
	public void incrementScore() {
		score += 200;
		if(score % 1000 == 0) {
			missleCooldown -= 0.07f;
		}
		
		win();
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
		renderMissles(batch);
		renderParticles(batch);
		renderHealth();
		String s = String.valueOf(score);
		font.draw(batch, s, Gdx.graphics.getWidth() - 10 - (s.length() * 24), 32);
		batch.end();
	}
	
	public void renderHealth() {
		for(int i = 0; i < potatoHealth; i++) {
			batch.draw(spud, 32 + (32 * i), Gdx.graphics.getHeight() - 42, 32, 32);
		}
	}
	
	public void renderMissles(SpriteBatch batch) {
		Iterator<Missle> it = missles.iterator();
		while(it.hasNext()) {
			Missle m = it.next();
			m.render(batch);
		}
	}
	
	public void renderParticles(SpriteBatch batch) {
		Iterator<Particle> it = activeParticles.iterator();
		while(it.hasNext()) {
			Particle p = it.next();
			p.render(batch);
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
		potatoHealth = 10;
		score = 0;
		
		background = new Texture(Gdx.files.internal("assets/background.png"));
		batch = new SpriteBatch();
		sprites = new Texture(Gdx.files.internal("assets/potato.png"));
		potato = new TextureRegion(sprites, 0, 0, 64, 128);
		tower = new TextureRegion(sprites, 64, 0, 64, 128);
		barrel = new Barrel(sprites, new TextureRegion(sprites, 128, 0, 32, 8));
		missleRegion = new TextureRegion(sprites, 128, 32, 32, 32);
		spud = new TextureRegion(sprites, 128, 64, 32, 32);
		font = new BitmapFont(Gdx.files.internal("assets/xolo24.fnt"), Gdx.files.internal("assets/xolo24.png"), false);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		
		Vector3 startPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(startPos);
		
		targetPos = new Vector2(startPos.x, startPos.y);
		
		Gdx.input.setInputProcessor(this);
		
		missles = new Array<Missle>();
		renderer = new ShapeRenderer();
		
		activeParticles = new Array<Particle>();
		
		// sound
		
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("assets/explosion.wav"));
		fireSound = Gdx.audio.newSound(Gdx.files.internal("assets/fire.wav"));
		
		simpleLoop = Gdx.audio.newMusic(Gdx.files.internal("assets/simpleloop.mp3"));
		simpleLoop.setVolume(0.2f);
		simpleLoop.setLooping(true);
		simpleLoop.play();
		musicTimer = 0;
		playingFirstTrack = true;
	}
	
	public void spawnParticles(Vector2 position) {
		activeParticles.addAll(Missle.deathAnimation(sprites, position));
	}
	
	public void switchTracks() {
		if(musicTimer >= 60.0f && playingFirstTrack) {
			chordLoop = Gdx.audio.newMusic(Gdx.files.internal("assets/chordloop.mp3"));
			chordLoop.setVolume(0.2f);
			simpleLoop.stop();
			chordLoop.setLooping(true);
			chordLoop.play();
			playingFirstTrack = false;
		}
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		if(fireTimer > Barrel.fireCooldown) {
			barrel.fire(this.targetPos);
			fireSound.play(0.5f);
			fireTimer = 0;
		}
		
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
		updateMissles();
		updateParticles();
		fireTimer += Gdx.graphics.getDeltaTime();
		missleTimer += Gdx.graphics.getDeltaTime();
		musicTimer += Gdx.graphics.getDeltaTime();
		if(score < 10000 && missleTimer > missleCooldown) {
			missleTimer = 0;
			fireMissle();
		}
		checkBulletCollision();
		checkMissleCollision();
		switchTracks();
	}
	
	public void updateMissles() {
		Iterator<Missle> it = missles.iterator();
		while(it.hasNext()) {
			Missle m = it.next();
			if(m.update()) {
				it.remove();
			}
		}
	}
	
	public void updateParticles() {
		Iterator<Particle> it = activeParticles.iterator();
		while(it.hasNext()) {
			Particle p = it.next();
			if(p.update()) {
				it.remove();
			}
		}
	}
	
	public void win() {
		if(score >= 10000 && missles.size == 0) {
			ReplayScreen r = game.getReplayScreen();
			r.setLoss(false);
			r.setScore(score);
			game.setScreen(r);
		}
	}

}
