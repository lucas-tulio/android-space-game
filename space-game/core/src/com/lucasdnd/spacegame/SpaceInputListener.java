package com.lucasdnd.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class SpaceInputListener implements InputProcessor {
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {

		if (y <= Gdx.graphics.getHeight() / 10) {
			Gdx.app.getApplicationListener().create();
		} else if (y >= Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 8) {
			if (x <= Gdx.graphics.getWidth() / 3) {
				((SpaceGame) Gdx.app.getApplicationListener()).rocket.rotatingRight = true;
			} else if (x > Gdx.graphics.getWidth() / 3
					&& x <= Gdx.graphics.getWidth() / 3 * 2) {
				((SpaceGame) Gdx.app.getApplicationListener()).rocket.thursting = true;
			} else {
				((SpaceGame) Gdx.app.getApplicationListener()).rocket.rotatingLeft = true;
			}
		}

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		((SpaceGame) Gdx.app.getApplicationListener()).rocket.thursting = false;
		((SpaceGame) Gdx.app.getApplicationListener()).rocket.rotatingRight = false;
		((SpaceGame) Gdx.app.getApplicationListener()).rocket.rotatingLeft = false;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
