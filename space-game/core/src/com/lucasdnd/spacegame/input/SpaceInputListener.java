package com.lucasdnd.spacegame.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.lucasdnd.spacegame.SpaceGame;

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
		} else if (y >= Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4) {
			if (x <= Gdx.graphics.getWidth() / 3) {
				((SpaceGame) Gdx.app.getApplicationListener()).getRocket().rotatingRight = true;
			} else if (x > Gdx.graphics.getWidth() / 3
					&& x <= Gdx.graphics.getWidth() / 3 * 2) {
				((SpaceGame) Gdx.app.getApplicationListener()).getRocket().thursting = true;
			} else {
				((SpaceGame) Gdx.app.getApplicationListener()).getRocket().rotatingLeft = true;
			}
		}

		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		
		if (y >= Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4) {
			if (x <= Gdx.graphics.getWidth() / 3) {
				((SpaceGame) Gdx.app.getApplicationListener()).getRocket().rotatingRight = false;
			} else if (x > Gdx.graphics.getWidth() / 3
					&& x <= Gdx.graphics.getWidth() / 3 * 2) {
				((SpaceGame) Gdx.app.getApplicationListener()).getRocket().thursting = false;
			} else {
				((SpaceGame) Gdx.app.getApplicationListener()).getRocket().rotatingLeft = false;
			}
		}
		
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
