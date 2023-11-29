package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.MenuGameOver;
import com.mygdx.game.Cenas.Hud;
import com.mygdx.game.Screens.MainMenuScreen;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();
		// Inicia com  o menu principal
		setScreen(new MainMenuScreen(this));
		//setScreen(new MenuGameOver(this, new Hud()));
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
