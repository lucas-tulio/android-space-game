package com.lucasdnd.spacegame;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lucasdnd.spacegame.util.MathUtils;

public class SpaceGame extends ApplicationAdapter {

	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
    BitmapFont font;

	Random r;

	ArrayList<Planet> planets;
	Space space;
	Rocket rocket;

	@Override
	public void create () {
		
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();    
        font = new BitmapFont();
		r = new Random();
		
		// Input
		Gdx.input.setInputProcessor(new InputAdapter () {
		   public boolean touchDown (int x, int y, int pointer, int button) {
			   
			   if (y <= Gdx.graphics.getHeight() / 10) {
				   create();
			   } else {
				   if (x <= Gdx.graphics.getWidth() / 3) {
					   rocket.rotatingRight = true;
				   } else if (x > Gdx.graphics.getWidth() / 3 && x <= Gdx.graphics.getWidth() / 3 * 2) {
					   rocket.thursting = true;
				   } else {
					   rocket.rotatingLeft = true;
				   }
			   }
			   
			   return true;
		   }

		   public boolean touchUp (int x, int y, int pointer, int button) {
			   rocket.thursting = false;
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
			
			if (CollisionChecker.checkRocketPlanetCollision(rocket, p)) {
				rocket.rekt = true;
				create();
			}
		}
		
		rocket.update(planets);
	}

	@Override
	public void render() {

		update();

		// Black bg
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Text
		batch.begin();
        font.draw(batch, "r.x: " + rocket.x, 0, Gdx.graphics.getHeight());
        font.draw(batch, "r.y: " + rocket.y, 0, Gdx.graphics.getHeight() - 20f);
        font.draw(batch, "p.x: " + planets.get(0).x, 0, Gdx.graphics.getHeight() - 40f);
        font.draw(batch, "p.y: " + planets.get(0).y, 0, Gdx.graphics.getHeight() - 60f);
        font.draw(batch, "p.r: " + planets.get(0).radius, 0, Gdx.graphics.getHeight() - 80f);
        font.draw(batch, "dist: " + MathUtils.getHypotenuse(rocket.x, rocket.y, planets.get(0).x, planets.get(0).y), 0, Gdx.graphics.getHeight() - 100f);
        batch.end();

		// Entities
		space.render(shapeRenderer);

		for (Planet p : planets) {
			p.render(shapeRenderer);
		}

		rocket.render(shapeRenderer);
	}
}
