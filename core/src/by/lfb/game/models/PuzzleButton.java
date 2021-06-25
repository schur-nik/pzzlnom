package by.lfb.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PuzzleButton extends TextButton{

    public PuzzleButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        this.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
            }
        });
    }
    public static Skin getPuzzleSkin() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/impact2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 22;
        BitmapFont font12 = generator.generateFont(parameter);
        generator.dispose();
        Skin skin = new Skin();
        skin.add("defaultFont", font12);
        skin.addRegions(new TextureAtlas(Gdx.files.internal("puzzleBlocks.pack")));
        skin.load(Gdx.files.internal("puzzleBlocks.json"));
        return skin;
    }

    public static String getRandomPuzzleStyleName() {
        if (((int) ( Math.random() * 2 + 1)) == 1) {
            return "dirt";
        } else {
            return "copper";
        }
    }
}
