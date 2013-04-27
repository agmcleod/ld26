package ld26;

import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Barrel extends Entity {
	
	private float angle;
	private TextureRegion bulletRegion;
	private Array<Bullet> bullets;
	public static float fireCooldown = 0.3f;
	private Vector2 rotationPos;
	
	public Barrel(Texture texture, TextureRegion region) {
		super(region, 120, 110);
		angle = 0;
		rotationPos = new Vector2(0, 4);
		bulletRegion = new TextureRegion(texture, 128, 8, 16, 8);
		bullets = new Array<Bullet>();
	}
	
	public float getAngle() {
		return this.angle;
	}
	
	public void fire(Vector2 targetPos) {
		bullets.add(Bullet.spawn(getPos(), angle, bulletRegion));
	}
	
	public Array<Bullet> getBullets() {
		return bullets;
	}
	
	public Vector2 getRotationPos() {
		return new Vector2(getPos().x + rotationPos.x, getPos().y + rotationPos.y);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(getTexture(), getPos().x, getPos().y, rotationPos.x, rotationPos.y, 32, 8, 1, 1, this.angle, true);
		Iterator<Bullet> it = bullets.iterator();
		while(it.hasNext()) {
			Bullet b = it.next();
			b.render(batch);
		}
	}
	
	public void setAngle(float angle) {
		if((angle > 0 && angle < 90) || (angle < 360 && angle > 270)) {
			this.angle = angle;
		}
	}
	
	public boolean update() {
		Iterator<Bullet> it = bullets.iterator();
		while(it.hasNext()) {
			Bullet b = it.next();
			if(b.update()) {
				it.remove();
			}
		}
		return false;
	}
}
