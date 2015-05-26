package com.lucasdnd.spacegame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Rocket extends Entity {

	float width, height, angle;
	int fuel, battery;
	boolean thursting;
	
	public Rocket (float x, float y, float width, float height) {
		super.x = x;
		super.y = y;
		this.width = width;
		this.height = height;
		
		fuel = 100;
		battery = 100;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(ShapeRenderer shapeRenderer) {
		// TODO Auto-generated method stub
		
	}
	
}
