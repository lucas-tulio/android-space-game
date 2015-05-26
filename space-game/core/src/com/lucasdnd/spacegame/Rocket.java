package com.lucasdnd.spacegame;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.lucasdnd.spacegame.util.MathUtils;

public class Rocket extends Entity {

	public float width, height, angle, force, fuel;
	public boolean thursting, rotatingRight, rotatingLeft;
	public boolean rekt;
	
	public float rotationSpeed;
	
	public Vector2 thurst, gravity, speed;
	
	public Rocket (float x, float y, float width, float height) {
		super.x = x;
		super.y = y;
		this.width = width;
		this.height = height;
		
		fuel = 1f;
		rotationSpeed = 3f;
		force = 0.01f;
		
		thurst = new Vector2();
		gravity = new Vector2();
		speed = new Vector2();
	}
	
	public void update(ArrayList<Planet> planets) {
		
		if (rotatingRight) {
			angle += rotationSpeed;
		} else if (rotatingLeft) {
			angle -= rotationSpeed;
		}
		
		if (thursting) {
			fuel -= 0.001f;
			if (fuel <= 0f) {
				thursting = false;
			}
		}
		
		for (Planet p : planets) {
			
			// Pythagoras
		    float hypotenuse = MathUtils.getHypotenuse(x, y, p.x, p.y);
		    
		    // Calculate gravity force and angle
		    float gravitySin = (x - p.x) / hypotenuse;
		    float gravityCos = (y - p.y) / hypotenuse;
		    float gravityForce = MathUtils.getGravityForce(hypotenuse, p.mass, p.radius);
		    gravity.x = gravityForce * gravitySin;
		    gravity.y = gravityForce * gravityCos;
		    
		    // Update thrust vector
		    if (thursting) {
		    	thurst.x = (float)-Math.sin(angle * Math.PI / 180) * force;
		    	thurst.y = (float)Math.cos(angle * Math.PI / 180) * force;
		    } else {
		    	thurst.x = 0f;
		    	thurst.y = 0f;
		    }
		    
		    // Sum it all up
		    speed.x += -gravity.x + thurst.x;
		    speed.y += -gravity.y + thurst.y;
		    
		    this.x += speed.x;
		    this.y += speed.y;
		}
	}
	
	public void render(ShapeRenderer shapeRenderer) {
		
        shapeRenderer.begin(ShapeType.Filled);
        
        if (thursting) {
        	shapeRenderer.setColor(Color.RED);
        } else {
        	shapeRenderer.setColor(Color.WHITE);
        }
        
        shapeRenderer.translate(x, y, 0f);
        shapeRenderer.rotate(0f, 0f, 1f, angle);
        
        	shapeRenderer.triangle(-width, 0f, 0f, height, width, 0f);
        
        shapeRenderer.rotate(0f, 0f, 1f, -angle);
        shapeRenderer.translate(-x, -y, 0f);
        
        shapeRenderer.end();
	}
	
}
