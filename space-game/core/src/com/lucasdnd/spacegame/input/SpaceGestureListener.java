package com.lucasdnd.spacegame.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.lucasdnd.spacegame.SpaceGame;

public class SpaceGestureListener implements GestureListener {

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {

		float zoom = ((SpaceGame) Gdx.app.getApplicationListener()).getCamera().zoom;

		if (distance >= initialDistance) {
			if (zoom <= 0.2f) {
				zoom = 0.2f;
			} else {
				zoom -= 0.015f;
			}
		} else {
			if (zoom >= 2f) {
				zoom = 2f;
			} else {
				zoom += 0.015f;
			}
		}

		((SpaceGame) Gdx.app.getApplicationListener()).getCamera().zoom = zoom;
		return true;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return true;
	}

}
