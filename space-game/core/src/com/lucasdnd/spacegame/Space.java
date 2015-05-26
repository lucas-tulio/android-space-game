package com.lucasdnd.spacegame;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Space {
	
	int numStars;
	float x[], y[];
	
	public Space(int numStars) {
		
		this.numStars = numStars;
		x = new float[numStars];
		y = new float[numStars];
		
		Random r = new Random();
		for (int i = 0; i < numStars; i++) {
			x[i] = r.nextInt(Gdx.graphics.getWidth());
			y[i] = r.nextInt(Gdx.graphics.getHeight());
		}
	}
	
	public void render(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeType.Point);
        for (int i = 0; i < numStars; i++) {
        	shapeRenderer.point(x[i], y[i], 0);
		}
		shapeRenderer.end();
	}

}
