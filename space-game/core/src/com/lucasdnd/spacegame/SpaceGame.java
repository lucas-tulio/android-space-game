package com.lucasdnd.spacegame;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SpaceGame extends ApplicationAdapter {
	
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	
	Space[] space;
	int numStars = 100;
	
	Random r;
	
	ArrayList<Entity> entities;
	
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// Entities
		entities = new ArrayList<Entity>();
		
		// Create stars
		r = new Random();
		space = new Space[numStars];
		for (int i = 0; i < space.length; i++) {
			space[i] = new Space(r.nextInt(Gdx.graphics.getWidth()), r.nextInt(Gdx.graphics.getHeight()));
			entities.add(space[i]);
		}
	}
	
	public void update() {
		for (Entity e : entities) {
			e.update();
		}
	}

	@Override
	public void render () {
		
		update();
		
		// Black bg
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Stars
		shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeType.Point);
        for (int i = 0; i < space.length; i++) {
			space[i].render(shapeRenderer);
		}
		shapeRenderer.end();
	}
}
