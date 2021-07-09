package by.lfb.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;

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
                for (PuzzleButton puzzleButton : new Array.ArrayIterator<>(ComboList.getComboList())) {
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
                try {
                    PuzzleButton puzzleButton = (PuzzleButton) event.getTarget().getParent();
                        if (!ComboList.containsInCombo(puzzleButton) && puzzleButton.getTYPE().equalsIgnoreCase(ComboList.getTypeCombo()) && puzzleButton.canMerge(ComboList.getLast())) {
                            setSkinLineCombo(puzzleButton);
                            ComboList.addToCombo(puzzleButton);
                        }
                    return true;
                }
                catch (ClassCastException e) {return false;}
            }

            private void setSkinLineCombo(PuzzleButton puzzleButton) {
                ComboList.getLast().getStyle().up = getPuzzleSkin().getDrawable(ComboList.getLast().TYPE + getTrueMerge(ComboList.getLast(), ComboList.getPrevLast(), puzzleButton));
                puzzleButton.getStyle().up = getPuzzleSkin().getDrawable(puzzleButton.TYPE + getTrueMerge(puzzleButton, ComboList.getLast(), null));
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

    private boolean canMerge(PuzzleButton last) {
/*        Integer[] thisPos = TablePuzzles.getPuzzlePos(this);
        if (Arrays.equals(thisPos, new Integer[]{TablePuzzles.getPuzzlePos(last)[0] - 1, TablePuzzles.getPuzzlePos(last)[1] - 1}))
            return true;
        else if (Arrays.equals(thisPos, new Integer[]{TablePuzzles.getPuzzlePos(last)[0] + 1, TablePuzzles.getPuzzlePos(last)[1] + 1}))
            return true;
        else if (Arrays.equals(thisPos, new Integer[]{TablePuzzles.getPuzzlePos(last)[0] + 1, TablePuzzles.getPuzzlePos(last)[1] - 1}))
            return true;
        else if (Arrays.equals(thisPos, new Integer[]{TablePuzzles.getPuzzlePos(last)[0] - 1, TablePuzzles.getPuzzlePos(last)[1] + 1}))
            return true;
        else
            return false;*/
        return true;
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

    public String getTrueMerge(PuzzleButton curr, PuzzleButton prev, PuzzleButton next) {
        Integer rowCurr = null, columnCurr = null, rowPrev = null, columnPrev = null, rowNext = null, columnNext = null;
        if (curr != null) {
            rowCurr = TablePuzzles.getPuzzlePos(curr)[0];
            columnCurr = TablePuzzles.getPuzzlePos(curr)[1];
        }
        if (prev != null) {
            rowPrev = TablePuzzles.getPuzzlePos(prev)[0];
            columnPrev = TablePuzzles.getPuzzlePos(prev)[1];
        }
        if (next != null) {
            rowNext = TablePuzzles.getPuzzlePos(next)[0];
            columnNext = TablePuzzles.getPuzzlePos(next)[1];
        }
        if (curr != null && prev != null && next == null) {
            if (rowCurr < rowPrev && columnCurr.equals(columnPrev)) {
                return "MergeTop";
            }
            if (rowCurr > rowPrev && columnCurr.equals(columnPrev)) {
                return "MergeBottom";
            }
            if (rowCurr.equals(rowPrev) && columnCurr > columnPrev) {
                return "MergeRight";
            }
            if (rowCurr.equals(rowPrev) && columnCurr < columnPrev) {
                return "MergeLeft";
            }
        }
        if (curr != null && next != null && prev == null) {
            if (rowCurr < rowNext && columnCurr.equals(columnNext)) {
                return "MergeTop";
            }
            if (rowCurr > rowNext && columnCurr.equals(columnNext)) {
                return "MergeBottom";
            }
            if (rowCurr.equals(rowNext) && columnCurr > columnNext) {
                return "MergeRight";
            }
            if (rowCurr.equals(rowNext) && columnCurr < columnNext) {
                return "MergeLeft";
            }
        }
        if (curr != null && next != null && prev != null) {
            if (columnPrev.equals(columnNext)) {
                return "MergeVert";
            }
            if (rowPrev.equals(rowNext)) {
                return "MergeHorz";
            }
            if (rowPrev < rowNext && columnPrev < columnNext) {
                return "MergeTopLeft";
            }
            if (rowPrev > rowNext && columnPrev < columnNext) {
                return "MergeTopRight";
            }
            if (rowPrev < rowNext && columnPrev > columnNext) {
                return "MergeBottomRight";
            }
            if (rowPrev > rowNext && columnPrev > columnNext) {
                return "MergeBottomLeft";
            }
        }
        return "";
    }
}
