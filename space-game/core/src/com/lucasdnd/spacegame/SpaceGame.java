package com.lucasdnd.spacegame;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.lucasdnd.spacegame.input.SpaceGestureListener;
import com.lucasdnd.spacegame.input.SpaceInputListener;
import com.lucasdnd.spacegame.util.MathUtils;

public class SpaceGame extends ApplicationAdapter {

	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	ShapeRenderer uiShapeRenderer;
	SpriteBatch batch;
	BitmapFont font;

	Random r;

	ArrayList<Planet> planets;
	Space space;
	Rocket rocket;
	Trajectory trajectory;

	@Override
	public void create() {

		// Game control stuff
		shapeRenderer = new ShapeRenderer();
		uiShapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		font = new BitmapFont();
		r = new Random();

		// Input
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new GestureDetector(new SpaceGestureListener()));
		multiplexer.addProcessor(new SpaceInputListener());
		Gdx.input.setInputProcessor(multiplexer);

		// Space (background)
		space = new Space(3000);

		// Planets
		planets = new ArrayList<Planet>();
		planets.add(new Planet(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 1f, 30f));

		rocket = new Rocket(planets.get(0).x, planets.get(0).y + 400f, 4f, 12f);

		trajectory = new Trajectory();
	}

	public void update() {

		for (Planet p : planets) {
			if (CollisionChecker.checkRocketPlanetCollision(rocket, p)) {
				rocket.rekt = true;
				create();
			}
		}

		rocket.update(planets);

		// Make the camera follow the rocket
		camera.position.set(rocket.x, rocket.y, 0f);
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
	}

	@Override
	public void render() {

		update();

		// Black bg
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// UI
		uiShapeRenderer.begin(ShapeType.Line);
		uiShapeRenderer.setColor(Color.WHITE);
		uiShapeRenderer.rect(80f, Gdx.graphics.getHeight() - 33f, Gdx.graphics.getWidth() - 100f, 12f);
		uiShapeRenderer.end();
		uiShapeRenderer.begin(ShapeType.Filled);
		uiShapeRenderer.setColor(Color.WHITE);
		uiShapeRenderer.rect(80f + ((1 - rocket.fuel) * (Gdx.graphics.getWidth() - 100f)), Gdx.graphics.getHeight() - 33f,
				(Gdx.graphics.getWidth() - 100f) * rocket.fuel, 12f);
		uiShapeRenderer.end();

		// Debug texts
		batch.begin();
		font.draw(batch, "Fuel ", 0, Gdx.graphics.getHeight() - 20f);
		font.draw(batch, "r.x: " + rocket.x, 0, Gdx.graphics.getHeight() - 60f);
		font.draw(batch, "r.y: " + rocket.y, 0, Gdx.graphics.getHeight() - 80f);
		font.draw(batch, "p.x: " + planets.get(0).x, 0, Gdx.graphics.getHeight() - 100f);
		font.draw(batch, "p.y: " + planets.get(0).y, 0, Gdx.graphics.getHeight() - 120f);
		font.draw(batch, "p.r: " + planets.get(0).radius, 0, Gdx.graphics.getHeight() - 140f);
		font.draw(batch, "dist: " + MathUtils.getHypotenuse(rocket.x, rocket.y, planets.get(0).x, planets.get(0).y), 0,
				Gdx.graphics.getHeight() - 160f);
		font.draw(batch, "ap: " + trajectory.getApoapsisDistance(), 0, Gdx.graphics.getHeight() - 200f);
		font.draw(batch, "pe: " + trajectory.getPeriapsisDistance(), 0, Gdx.graphics.getHeight() - 220f);
		batch.end();

		// Entities
		space.render(shapeRenderer);

		for (Planet p : planets) {
			p.render(shapeRenderer);
		}

		rocket.render(shapeRenderer);

		trajectory.calculateApAndPe(planets, rocket, shapeRenderer);
		trajectory.renderOrbitEllipse(planets, rocket, shapeRenderer);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public Rocket getRocket() {
		return rocket;
	}

	public void setRocket(Rocket rocket) {
		this.rocket = rocket;
	}
}
