package ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity {
	
	private float angle;
	private int movementSpeed;
	
	public Bullet(TextureRegion texture, float x, float y, float angle) {
		super(texture, x, y);
		this.angle = angle;
		movementSpeed = 500;
	}
	
	public static Bullet spawn(Vector2 position, float angle, TextureRegion texture) {
		return new Bullet(texture, position.x, position.y, angle);
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(getTexture(), getPos().x, getPos().y, 0, 4, 16, 8, 1, 1, this.angle, true);
	}
	
	public boolean update() {
		int velocityX = (int) (MathUtils.cos(angle * MathUtils.PI / 180) * this.movementSpeed * Gdx.graphics.getDeltaTime());
		int velocityY = (int) (MathUtils.sin(angle * MathUtils.PI / 180) * this.movementSpeed * Gdx.graphics.getDeltaTime());
		Vector2 pos = getPos();
		pos.x += velocityX;
		pos.y += velocityY;
		setPos(pos);
		return (pos.x > Gdx.graphics.getWidth() || pos.y < 0 || pos.y > Gdx.graphics.getHeight());
	}
}
