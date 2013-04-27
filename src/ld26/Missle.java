package ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Missle extends Entity {
	
	private double a;
	private Vector2 targetRoot;
	private float xMovement;
	
	public Missle(TextureRegion texture, float x, float y) {
		super(texture, x, y);
		float middle = Gdx.graphics.getWidth() / 2;
		targetRoot = new Vector2(MathUtils.random(middle - 15, middle + 15), MathUtils.random(100, Gdx.graphics.getHeight()));
		xMovement = 400 * ((Gdx.graphics.getWidth() - targetRoot.y) / Gdx.graphics.getHeight());
		double ac = Math.pow((x - targetRoot.x), 2d);
		a = (y - targetRoot.y) / ac;
	}
	
	public boolean update() {
		Vector2 pos = getPos();
		pos.x -= xMovement * Gdx.graphics.getDeltaTime();
		pos.y = (float) (this.a * Math.pow((pos.x - targetRoot.x), 2) + targetRoot.y);
		setPos(pos);
		return (pos.x < 0 || pos.y < 0 || pos.y > Gdx.graphics.getHeight());
	}
}
