package by.lfb.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import by.lfb.game.screens.MainMenuScreen;

public class Pzzlnom extends Game {

    public void create() {
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

}
