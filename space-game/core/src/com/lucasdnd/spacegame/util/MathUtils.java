package com.lucasdnd.spacegame.util;

public class MathUtils {
	
	public static final float G = 6.673f * (float)Math.pow(10, -11); // Gravitational constant

	// Planet data
	public static final float EARTH_MASS = 5.98f * (float)Math.pow(10, 24); // Real Earth mass
	public static final float EARTH_RADIUS = 6.38f * (float)Math.pow(10, 6); // Real Earth radius

	// Scaling it down
	public static final float EARTH_RADIUS_IN_PIXELS = 10f;
	public static final float SCALE = EARTH_RADIUS / EARTH_RADIUS_IN_PIXELS;
	
	public static float getGravityForce(float distance) {
		return G * EARTH_MASS / (float)Math.pow(distance * SCALE, 2);
	}
	
	public static float getHypotenuse(float x1, float y1, float x2, float y2) {
		float adjacent = x1 - x2;
		float opposite = y1 - y2;
		return (float)Math.sqrt(Math.pow(adjacent, 2) + Math.pow(opposite, 2));
	}
}
