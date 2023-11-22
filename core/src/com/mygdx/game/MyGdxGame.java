package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.mygdx.game.Screens.MenuPrincipal;
import com.mygdx.game.Screens.PlayGame;


public class MyGdxGame extends Game {
	public SpriteBatch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();
		// Inicia com  o menu principal
		setScreen(new MenuPrincipal(this));
		//setScreen(new PlayGame(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}