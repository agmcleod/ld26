package ld26;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Barrel extends Entity {
	
	private float angle;
	private Vector2 rotationPos;
	
	public Barrel(TextureRegion texture) {
		super(texture, 120, 110);
		angle = 0;
		this.rotationPos = new Vector2(0, 4); 
	}
	
	public float getAngle() {
		return this.angle;
	}
	
	public Vector2 getRotationPos() {
		return new Vector2(getPos().x + rotationPos.x, getPos().y + rotationPos.y);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(getTexture(), getPos().x, getPos().y, rotationPos.x, rotationPos.y, 32, 8, 1, 1, this.angle, true);
	}
	
	public void setAngle(float angle) {
		if((angle > 0 && angle < 90) || (angle < 360 && angle > 270)) {
			this.angle = angle;
		}
	}
}
