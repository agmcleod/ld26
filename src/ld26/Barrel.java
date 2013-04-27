package ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Barrel extends Entity {
	
	private float angle;
	
	public Barrel(TextureRegion texture) {
		super(texture, 120, 110);
		angle = 0;
	}
	
	public float getAngle() {
		return this.angle;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(getTexture(), getPos().x, getPos().y, 0, 0, 32, 8, 1, 1, this.angle, true);
	}
	
	public void setAngle(float angle) {
		if((angle > 0 && angle < 90) || (angle < 360 && angle > 270)) {
			this.angle = angle;
		}
	}
}
