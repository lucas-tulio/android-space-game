package com.lucasdnd.spacegame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Entity {
	public float x, y;
	
	public abstract void update();
	public abstract void render(ShapeRenderer shapeRenderer);
}
