package ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Particle extends Entity {
	
	private Vector2 target;
	private int movementSpeed = 500;
	private Vector2 originalPosition;
	
	public Particle(TextureRegion region, float x, float y, Vector2 target, float angle) {
		super(region, x, y);
		this.target = target;
		this.angle = angle;
		originalPosition = new Vector2(x, y);
	}
	
	public boolean update() {
		int velocityX = (int) (MathUtils.cos(angle * MathUtils.PI / 180) * this.movementSpeed * Gdx.graphics.getDeltaTime());
		int velocityY = (int) (MathUtils.sin(angle * MathUtils.PI / 180) * this.movementSpeed * Gdx.graphics.getDeltaTime());
		
		Vector2 pos = getPos();
		pos.x += velocityX;
		pos.y += velocityY;
		setPos(pos);
		return (Math.abs(pos.x - originalPosition.x) > 64 || Math.abs(pos.y - originalPosition.y) > 64);
	}
}
