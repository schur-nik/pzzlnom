package by.lfb.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;

import java.util.Iterator;

public class PuzzleButton extends TextButton{

    private String TYPE = "";
    private static final float width = 50f;
    private static final float height = 50f;

    public PuzzleButton(final String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        this.setHeight(height);
        this.setWidth(width);
        TYPE = styleName;
        this.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ComboList.addToCombo((PuzzleButton)event.getListenerActor());
                ((PuzzleButton)event.getListenerActor()).getStyle().up = getPuzzleSkin().getDrawable(TYPE+"Press");
                System.out.println(event.getListenerActor().getName());
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                for (PuzzleButton puzzleButton : ComboList.getComboList()) {
                    puzzleButton.getStyle().up = getPuzzleSkin().getDrawable(puzzleButton.TYPE);
                }
                ComboList.clearCombo();
            }

/*            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (TablePuzzles.getPuzzlePos(event.getTarget()) != null) {
                    if (!ComboList.containsInCombo((PuzzleButton) event.getTarget()) && ((PuzzleButton) event.getTarget()).getTYPE().equalsIgnoreCase(ComboList.getTypeCombo())) {
                        ((PuzzleButton) event.getTarget()).getStyle().up = getPuzzleSkin().getDrawable(TYPE + "Press");
                        ComboList.addToCombo((PuzzleButton) event.getTarget());
                    }
                }
            }*/
        });
        this.addListener(new FocusListener() {
            @Override
            public boolean handle(Event event) {
                if (TablePuzzles.getPuzzlePos(event.getTarget().getParent()) != null) {
                    if (!ComboList.containsInCombo((PuzzleButton) event.getTarget().getParent()) && ((PuzzleButton) event.getTarget().getParent()).getTYPE().equalsIgnoreCase(ComboList.getTypeCombo())) {
                        ((PuzzleButton) event.getTarget().getParent()).getStyle().up = getPuzzleSkin().getDrawable(TYPE + getTrueMerge(((PuzzleButton) event.getTarget().getParent()), ComboList.getComboList().get(ComboList.getComboList().size-1)));
                        ComboList.addToCombo((PuzzleButton) event.getTarget().getParent());
                    }
                }
                return true;
            }
        });
/*        this.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println(actor.getName());
            }
        });*/
/*        this.addListener(new FocusListener() {
             public boolean handle (Event event) {
                if (getClickListener().isPressed() &&
                    !ComboList.containsInCombo(event.getTarget()) &&
                    ((PuzzleButton) event.getTarget()).getTYPE().equalsIgnoreCase(ComboList.getTypeCombo())) {
                    ((PuzzleButton)event.getTarget()).getStyle().up = getPuzzleSkin().getDrawable(TYPE+"Press");
                    ComboList.addToCombo((PuzzleButton)event.getTarget());
                }
                return true;
            }
        });*/
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

    public String getTYPE() {
        return TYPE;
    }

    public static float getFinalWidth() {
        return width;
    }

    public static float getFinalHeight() {
        return height;
    }

    public String getTrueMerge(PuzzleButton curr, PuzzleButton prev) {
        Integer rowCurr, columnCurr, rowPrev, columnPrev;
        rowCurr = TablePuzzles.getPuzzlePos(curr)[0];
        columnCurr = TablePuzzles.getPuzzlePos(curr)[1];
        rowPrev = TablePuzzles.getPuzzlePos(prev)[0];
        columnPrev = TablePuzzles.getPuzzlePos(prev)[1];
        if (rowCurr < rowPrev && columnCurr == columnPrev) {return "MergeTop";};
        if (rowCurr > rowPrev && columnCurr == columnPrev) {return "MergeBottom";};
        return "";
    }
}
