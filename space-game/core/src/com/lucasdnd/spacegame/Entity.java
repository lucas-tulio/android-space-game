package com.lucasdnd.spacegame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Entity {
	protected float x, y;
	
	public abstract void update();
	public abstract void render(ShapeRenderer shapeRenderer);
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
}
