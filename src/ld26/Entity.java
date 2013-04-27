package ld26;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Entity {
	private Vector2 pos;
	private TextureRegion texture;
	
	public Entity(TextureRegion texture, float x, float y) {
		this.texture = texture;
		this.pos = new Vector2(x, y);
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, this.pos.x, this.pos.y);
	}
}
