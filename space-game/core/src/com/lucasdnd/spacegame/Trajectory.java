package com.lucasdnd.spacegame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.lucasdnd.spacegame.util.MathUtils;

public class Trajectory {

	Vector2 periapsis = new Vector2(0f, 0f);
	float periapsisDistance = 100000f;

	Vector2 apoapsis = new Vector2(0f, 0f);
	float apoapsisDistance = -100000f;

	int calcResolution, maxLoops;
	boolean invertDrawing;
	
	boolean trajectoryChanged;

	public Trajectory() {
		this.calcResolution = 30;
		this.maxLoops = 1000;
	}

	public void calculateApAndPe(ArrayList<Planet> planets, Rocket rocket, ShapeRenderer shapeRenderer) {

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

		for (int i = 0; i < maxLoops; i++) {
			for (Planet p : planets) {

				// Pythagoras
				float hypotenuse = MathUtils.getHypotenuse(r.x, r.y, p.x, p.y);

				// Calculate gravity force and angle
				float gravitySin = (r.x - p.x) / hypotenuse;
				float gravityCos = (r.y - p.y) / hypotenuse;
				float gravityForce = MathUtils.getGravityForce(hypotenuse, p.mass, p.radius);
				r.gravity.x = gravityForce * gravitySin;
				r.gravity.y = gravityForce * gravityCos;

				// Update thrust vector
				if (r.thursting) {
					r.thurst.x = (float) -Math.sin(r.angle * Math.PI / 180) * r.force;
					r.thurst.y = (float) Math.cos(r.angle * Math.PI / 180) * r.force;
				} else {
					r.thurst.x = 0f;
					r.thurst.y = 0f;
				}

				// Sum it all up
				r.speed.x += -r.gravity.x + r.thurst.x;
				r.speed.y += -r.gravity.y + r.thurst.y;

				r.x += r.speed.x;
				r.y += r.speed.y;
				
				if (trajectoryChanged) {
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
				}
				
				// Draw
				if (i % calcResolution == 0) {
					shapeRenderer.begin(ShapeType.Filled);
					Gdx.gl20.glEnable(GL20.GL_BLEND);
					shapeRenderer.setColor(1f, 1f, 1f, (1f-(float)i/(float)maxLoops));
					shapeRenderer.circle(r.x, r.y, 2f);
					shapeRenderer.end();
				}
			}
		}

		periapsisDistance = 100000f;
		apoapsisDistance = -100000f;
		trajectoryChanged = false;
	}

	public void renderOrbitEllipse(ArrayList<Planet> planets, Rocket rocket, ShapeRenderer shapeRenderer) {

		if (planets.size() != 1) {
			return;
		}

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(periapsis.x, periapsis.y, 4f);
		shapeRenderer.circle(apoapsis.x, apoapsis.y, 4f);
		shapeRenderer.end();
	}
}
