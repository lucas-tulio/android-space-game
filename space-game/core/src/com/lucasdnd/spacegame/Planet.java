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
	
	public void update() {
		
	}
	
	public void render(ShapeRenderer shapeRenderer) {
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(x, y, radius);
		shapeRenderer.end();
	}

}
