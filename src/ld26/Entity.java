package ld26;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
	
	private Rectangle axisAlignedBoundingBox;
	protected float angle;
	private Vector2 pos;
	private Vector2 rotateOffset;
	private Vector2 rotatePos;
	private TextureRegion texture;
	
	public Entity(TextureRegion texture, float x, float y) {
		angle = 0;
		this.texture = texture;
		this.pos = new Vector2(x, y);
		rotatePos = new Vector2(x, y);
		rotateOffset = new Vector2();
		axisAlignedBoundingBox = new Rectangle(x, y, texture.getRegionWidth(), texture.getRegionHeight());
	}
	
	public void debug(ShapeRenderer renderer) {
		renderer.rect(axisAlignedBoundingBox.x, axisAlignedBoundingBox.y, axisAlignedBoundingBox.width, axisAlignedBoundingBox.height);
	}
	
	public Rectangle getAABB() {
		return axisAlignedBoundingBox;
	}
	
	public Vector2[] getPoints() {
		float width = texture.getRegionWidth();
		float height = texture.getRegionHeight();
		Vector2[] points = {
			rotateAroundPoint(new Vector2(pos)),
			rotateAroundPoint(new Vector2(pos.x + width, pos.y)),
			rotateAroundPoint(new Vector2(pos.x + width, pos.y + height)),
			rotateAroundPoint(new Vector2(pos.x, pos.y + height))
		};
		
		return points;
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public Vector2 getRotatePos() {
		return rotatePos;
	}
	
	public TextureRegion getTexture() {
		return texture;
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, this.pos.x, this.pos.y);
	}
	
	public Vector2 rotateAroundPoint(Vector2 point) {
		float x = point.x;
		float a = (angle * MathUtils.PI / 180);
		point.x = MathUtils.cos(a) * (point.x - rotatePos.x) - MathUtils.sin(a) * (point.y-rotatePos.y) + rotatePos.x;
		point.y = MathUtils.sin(a) * (point.x-rotatePos.x) + MathUtils.cos(a) * (point.y-rotatePos.y) + rotatePos.y;
		return point;
	}
	
	public void setPos(Vector2 pos) {
		this.pos = pos;
		
		setRotatePos(pos.x + rotateOffset.x, pos.y + rotateOffset.y);
		
		float minX = 1000, minY = 1000, maxX = 0, maxY = 0;
		
		Vector2[] points = getPoints();
		for(int i = 0; i < points.length; i++) {
			Vector2 point = points[i];
			maxX = Math.max(maxX, point.x);
			maxY = Math.max(maxY, point.y);
			minX = Math.min(minX, point.x);
			minY = Math.min(minY, point.y);
		}
		
		axisAlignedBoundingBox.x = minX;
		axisAlignedBoundingBox.y = minY;
		axisAlignedBoundingBox.width = maxX - minX;
		axisAlignedBoundingBox.height = maxY - minY;
	}
	
	public void setRotatePos(float x, float y) {
		this.rotatePos.set(x, y);
	}

	public Vector2 getRotateOffset() {
		return rotateOffset;
	}

	public void setRotateOffset(float x, float y) {
		this.rotateOffset.set(x, y);
	}
}
