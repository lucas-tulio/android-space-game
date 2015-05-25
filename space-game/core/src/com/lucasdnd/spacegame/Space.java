package com.lucasdnd.spacegame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Space extends Entity {
	
	public Space(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(ShapeRenderer shapeRenderer) {
		shapeRenderer.point(x, y, 0);
	}

}
