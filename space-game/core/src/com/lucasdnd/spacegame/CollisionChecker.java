package com.lucasdnd.spacegame;

import com.lucasdnd.spacegame.util.MathUtils;

public class CollisionChecker {
	public static boolean checkRocketPlanetCollision(Rocket r, Planet p) {
		return MathUtils.getHypotenuse(r.x, r.y, p.x, p.y) < p.radius;
	}
}
