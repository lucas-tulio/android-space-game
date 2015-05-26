package com.lucasdnd.spacegame;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SpaceGame extends ApplicationAdapter {
	
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	
	Random r;
	
	ArrayList<Entity> entities;
	Space space;
	
	@Override
	public void create () {
		
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		r = new Random();
		
		// Entities
		entities = new ArrayList<Entity>();
		
		// Space
		space = new Space(100);
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
		
		// Entities
		space.render(shapeRenderer);
	}
}
