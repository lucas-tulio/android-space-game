package com.lucasdnd.spacegame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Planet extends Entity {
	
	float mass, radius;
	
	public Planet(float x, float y, float mass, float radius) {
		super.x = x;
		super.y = y;
		this.mass = mass;
		this.radius = radius;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.circle(x, y, radius);
		shapeRenderer.end();
	}

}
