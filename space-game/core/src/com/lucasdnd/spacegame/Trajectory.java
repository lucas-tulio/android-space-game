package com.lucasdnd.spacegame;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.lucasdnd.spacegame.util.MathUtils;

public class Trajectory {

	Vector2 periapsis = new Vector2(0f, 0f);
	Vector2 apoapsis = new Vector2(0f, 0f);

	int calcResolution, maxLoops;
	protected float ellipseX, ellipseY, ellipseWidth, ellipseHeight, ellipseAngle;

	public Trajectory() {
		this.calcResolution = 30;
		this.maxLoops = 1000;
	}

	public void calculateOrbitEllipse(ArrayList<Planet> planets, Rocket rocket) {

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
		
		float periapsisDistance = 100000f;
		float apoapsisDistance = -100000f;

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
		}
		
		// Here we have both the ap and the pe so we can calculate the orbit
		
		// 1. Find the major axis of the ellipse (width)
		ellipseWidth = periapsisDistance + apoapsisDistance;
		
		// 2. Find the distance between the two foci
		float fociDistance = apoapsisDistance - periapsisDistance;
		
		// 3. Find the minor axis of the ellipse (height)
		ellipseHeight = (float)Math.sqrt(Math.pow(apoapsisDistance + periapsisDistance, 2) - Math.pow(fociDistance, 2));
		
		// 4. Find the angle between the periapsis and one of the focus of the ellipse (that would be the planet)
		// This is the angle at which we'll need to rotate the ellipse when drawing it
		Planet p = planets.get(0);
		float peToPlanetY = periapsis.y - p.y; // opposite side
		
		ellipseAngle = (float)(Math.asin(peToPlanetY / periapsisDistance) / Math.PI * 180);
		
		// 5. Find the center of the ellipse
		ellipseX = (apoapsis.x + periapsis.x) / 2f;
		ellipseY = (apoapsis.y + periapsis.y) / 2f;
	}

	public void renderOrbitEllipse(ArrayList<Planet> planets, Rocket rocket, ShapeRenderer shapeRenderer, ShapeRenderer ellipseRenderer, Camera camera) {

		if (planets.size() != 1) {
			return;
		}
		
		// Ap and Pe points
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(periapsis.x, periapsis.y, 4f);
		shapeRenderer.circle(apoapsis.x, apoapsis.y, 4f);
		shapeRenderer.end();
		
		// Orbit Ellipse
		ellipseRenderer.setProjectionMatrix(new Matrix4());
		ellipseRenderer.setTransformMatrix(new Matrix4());
		ellipseRenderer.setProjectionMatrix(camera.combined);
		
		ellipseRenderer.begin(ShapeType.Line);
		ellipseRenderer.setColor(Color.WHITE);
		
		// Translate
		ellipseRenderer.translate(ellipseX - ellipseWidth / 2f, ellipseY - ellipseHeight / 2f, 0f);
		
		// Rotate
		ellipseRenderer.translate(ellipseWidth/2f, ellipseHeight/2f, 0f);
		ellipseRenderer.rotate(0f, 0f, 1f, ellipseAngle);
		ellipseRenderer.translate(-ellipseWidth/2f, -ellipseHeight/2f, 0f);
		
		// Draw
		ellipseRenderer.ellipse(0f, 0f, ellipseWidth, ellipseHeight);
		ellipseRenderer.end();
		
	}
}
