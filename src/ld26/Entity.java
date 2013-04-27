package ld26;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
	
	private Rectangle axisAlignedBoundingBox;
	private Vector2 pos;
	private TextureRegion texture;
	
	public Entity(TextureRegion texture, float x, float y) {
		this.texture = texture;
		this.pos = new Vector2(x, y);
		axisAlignedBoundingBox = new Rectangle(x, y, texture.getRegionWidth(), texture.getRegionHeight());
	}
	
	public void debug(ShapeRenderer renderer) {
		renderer.rect(axisAlignedBoundingBox.x, axisAlignedBoundingBox.y, axisAlignedBoundingBox.width, axisAlignedBoundingBox.height);
	}
	
	public Rectangle getAABB() {
		return axisAlignedBoundingBox;
	}
	
	public Vector2 getPos() {
		return this.pos;
	}
	
	public TextureRegion getTexture() {
		return texture;
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, this.pos.x, this.pos.y);
	}
	
	public void setPos(Vector2 pos) {
		this.pos = pos;
		axisAlignedBoundingBox.x = pos.x;
		axisAlignedBoundingBox.y = pos.y;
	}
}
