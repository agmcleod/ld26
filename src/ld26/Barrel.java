package ld26;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Barrel extends Entity {
	public Barrel(TextureRegion texture) {
		super(texture, 120, 110);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(getTexture(), getPos().x, getPos().y, 0, 0, 8, 41, 1, 1, 270);
	}
}
