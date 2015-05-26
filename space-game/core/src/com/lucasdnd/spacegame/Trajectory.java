package com.lucasdnd.spacegame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.lucasdnd.spacegame.util.MathUtils;

public class Trajectory {

	Vector2 periapsis = new Vector2(0f, 0f);
	float periapsisDistance = 100000f;

	Vector2 apoapsis = new Vector2(0f, 0f);
	float apoapsisDistance = -100000f;

	int drawEvery, maxPoints;
	boolean invertDrawing;

	public Trajectory(int drawEvery, int maxPoints) {
		this.drawEvery = drawEvery;
		this.maxPoints = maxPoints;
	}

	public void calculateAndDraw(ArrayList<Planet> planets, Rocket rocket,
			ShapeRenderer shapeRenderer) {

		// Create a copy of our rocket and run the same calculations
		// on it. Use the results to draw the trajectory

		Rocket r = new Rocket(0f, 0f, 0f, 0f);
		r.x = rocket.x;
		r.y = rocket.y;
		r.gravity.x = rocket.gravity.x;
		r.gravity.y = rocket.gravity.y;
		r.thurst.x = rocket.thurst.x;
		r.thurst.y = rocket.thurst.y;
		r.speed.x = rocket.speed.x;
		r.speed.y = rocket.speed.y;

		for (int i = 0; i < maxPoints; i++) {
			for (Planet p : planets) {

				// Pythagoras
				float hypotenuse = MathUtils.getHypotenuse(r.x, r.y, p.x, p.y);

				// Calculate gravity force and angle
				float gravitySin = (r.x - p.x) / hypotenuse;
				float gravityCos = (r.y - p.y) / hypotenuse;
				float gravityForce = MathUtils.getGravityForce(hypotenuse,
						p.mass, p.radius);
				r.gravity.x = gravityForce * gravitySin;
				r.gravity.y = gravityForce * gravityCos;

				// Update thrust vector
				if (r.thursting) {
					r.thurst.x = (float) -Math.sin(r.angle * Math.PI / 180)
							* r.force;
					r.thurst.y = (float) Math.cos(r.angle * Math.PI / 180)
							* r.force;
				} else {
					r.thurst.x = 0f;
					r.thurst.y = 0f;
				}

				// Sum it all up
				r.speed.x += -r.gravity.x + r.thurst.x;
				r.speed.y += -r.gravity.y + r.thurst.y;

				r.x += r.speed.x;
				r.y += r.speed.y;

				// Get the apoapsis and periapsis
				if (periapsisDistance > hypotenuse) {
					periapsis.x = r.x;
					periapsis.y = r.y;
					periapsisDistance = hypotenuse;
				} else if (apoapsisDistance < hypotenuse) {
					apoapsis.x = r.x;
					apoapsis.y = r.y;
					apoapsisDistance = hypotenuse;
				}

				if (i % drawEvery == 0) {
					shapeRenderer.begin(ShapeType.Filled);
					shapeRenderer.setColor(Color.WHITE);
					shapeRenderer.point(r.x, r.y, 0f);
					shapeRenderer.end();
				}
			}
		}

		periapsisDistance = 100000f;
		apoapsisDistance = -100000f;
	}

	public void renderApAndPe(ArrayList<Planet> planets, Rocket rocket, ShapeRenderer shapeRenderer) {

		if (planets.size() != 1) {
			return;
		}

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.ellipse(periapsis.x, periapsis.y, 4f, 4f);
		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.ellipse(apoapsis.x, apoapsis.y, 4f, 4f);
		shapeRenderer.end();
	}
}
