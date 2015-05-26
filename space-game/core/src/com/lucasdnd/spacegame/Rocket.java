package com.lucasdnd.spacegame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Rocket extends Entity {

	float width, height, angle;
	int fuel, battery;
	boolean thursting, rotatingRight, rotatingLeft;
	
	float rotationSpeed;
	
	Vector2 thurst, gravity, speed;
	
	public Rocket (float x, float y, float width, float height) {
		super.x = x;
		super.y = y;
		this.width = width;
		this.height = height;
		
		fuel = 100;
		battery = 100;
		rotationSpeed = 3f;
		
		thurst = new Vector2();
		gravity = new Vector2();
		speed = new Vector2();
	}
	
	@Override
	public void update() {
		
		if (rotatingRight) {
			angle += rotationSpeed;
		} else if (rotatingLeft) {
			angle -= rotationSpeed;
		}
		
	}

	@Override
	public void render(ShapeRenderer shapeRenderer) {
		
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        
        shapeRenderer.translate(x, y, 0f);
        shapeRenderer.rotate(0f, 0f, 1f, angle);
        
        	shapeRenderer.triangle(-width, 0f, 0f, height, width, 0f);
        
        shapeRenderer.rotate(0f, 0f, 1f, -angle);
        shapeRenderer.translate(-x, -y, 0f);
        
        shapeRenderer.end();
	}
	
}
