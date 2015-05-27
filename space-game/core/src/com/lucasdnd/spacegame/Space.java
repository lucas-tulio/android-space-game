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
		int worldSizeX = Gdx.graphics.getWidth() * 5;
		int worldSizeY = Gdx.graphics.getHeight() * 3;
		for (int i = 0; i < numStars; i++) {

			int randX = r.nextInt(worldSizeX) - worldSizeX / 2;
			int randY = r.nextInt(worldSizeY) - worldSizeY / 2;
			x[i] = randX;
			y[i] = randY;
		}
	}

	public void render(ShapeRenderer shapeRenderer) {
		shapeRenderer.begin(ShapeType.Point);
		shapeRenderer.setColor(Color.WHITE);
		for (int i = 0; i < numStars; i++) {
			shapeRenderer.point(x[i], y[i], 0);
		}
		shapeRenderer.end();
	}

}
