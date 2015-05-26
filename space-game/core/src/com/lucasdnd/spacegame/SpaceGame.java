package com.lucasdnd.spacegame;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SpaceGame extends ApplicationAdapter {

	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;

	Random r;

	ArrayList<Planet> planets;
	Space space;
	Planet planet;
	Rocket rocket;

	@Override
	public void create () {
		
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		r = new Random();
		
		// Input
		Gdx.input.setInputProcessor(new InputAdapter () {
		   public boolean touchDown (int x, int y, int pointer, int button) {
			   
			   if (x >= Gdx.graphics.getWidth() / 2) {
				   rocket.rotatingLeft = true;
			   } else {
				   rocket.rotatingRight = true;
			   }
			   
			   return true;
		   }

		   public boolean touchUp (int x, int y, int pointer, int button) {
			   rocket.rotatingRight = false;
			   rocket.rotatingLeft = false;
			   return true;
		   }
		});
		
		// Space (background)
		space = new Space(200);
		
		// Planets
		planets = new ArrayList<Planet>();
		planets.add(new Planet(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 10f, 30f));
		
		rocket = new Rocket(planets.get(0).x, planets.get(0).y + 200f, 10f, 30f);
	}

	public void update() {
		for (Planet p : planets) {
			p.update();
		}

		rocket.update();
	}

	@Override
	public void render() {

		update();

		// Black bg
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Entities
		space.render(shapeRenderer);

		for (Planet p : planets) {
			p.render(shapeRenderer);
		}

		rocket.render(shapeRenderer);
	}
}
