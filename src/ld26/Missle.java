package ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Missle extends Entity {
	
	private double a;
	private Vector2 targetRoot;
	private float xMovement;
	static float rate = 400;
	
	public Missle(TextureRegion texture, float x, float y) {
		super(texture, x, y);
		float middle = Gdx.graphics.getWidth() / 2;
		targetRoot = new Vector2(MathUtils.random(middle - 15, middle + 15), MathUtils.random(100, Gdx.graphics.getHeight() - 100));
		xMovement = Missle.rate * ((Gdx.graphics.getWidth() - targetRoot.y) / Gdx.graphics.getHeight());
		double ac = Math.pow((x - targetRoot.x), 2d);
		a = (y - targetRoot.y) / ac;
	}
	
	public static Array<Particle> deathAnimation(Texture texture, Vector2 position) {
		TextureRegion[] particleTextures = {
			new TextureRegion(texture, 128, 96, 16, 16),
			new TextureRegion(texture, 144, 96, 16, 16),
			new TextureRegion(texture, 128, 112, 16, 16),
			new TextureRegion(texture, 144, 112, 16, 16)
		};
		
		int numberToSpawn = MathUtils.random(10, 15);
		Array<Particle> particles = new Array<Particle>();
		for(int i = 0; i < numberToSpawn; i++) {
			TextureRegion tr = particleTextures[MathUtils.random(0, 3)];
			Vector2 target = new Vector2();
			float deg = (360 / numberToSpawn) * i;
			deg *= (180 / MathUtils.PI);
			target.x = position.x * MathUtils.cos(deg);
			target.y = position.y * MathUtils.sin(deg);
			particles.add(new Particle(tr, position.x, position.y, target, deg));
		}
		return particles;
	}
	
	public boolean update() {
		Vector2 pos = getPos();
		pos.x -= xMovement * Gdx.graphics.getDeltaTime();
		pos.y = (float) (this.a * Math.pow((pos.x - targetRoot.x), 2) + targetRoot.y);
		setPos(pos);
		return (pos.x < 0 || pos.y < 0 || pos.y > Gdx.graphics.getHeight());
	}
}
