package by.lfb.game.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.lang.reflect.Array;

public class TablePuzzles extends Table {
    private static PuzzleButton[][] tablePuzzles;
    private static int size;
    private float x, y;
    private float posTableX, posTableY;
    private float gap = 0f;
    private PuzzleButton puzzleForAdd;

    public TablePuzzles(Integer size) {
        this.size = size;
        tablePuzzles = new PuzzleButton[size][size];
        posTableX = -PuzzleButton.getFinalWidth()*size/2;
        posTableY = -PuzzleButton.getFinalHeight()*size/2;
    }

    public TablePuzzles(Integer size, float posTableX, float posTableY) {
        this.size = size;
        tablePuzzles = new PuzzleButton[size][size];
        this.posTableX = posTableX - PuzzleButton.getFinalWidth()*size/2;
        this.posTableY = posTableY - PuzzleButton.getFinalHeight()*size/2;
    }

    public TablePuzzles createTablePuzzles() {
        x = posTableX;
        y = posTableY;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                puzzleForAdd = StackPuzzle.getPuzzleFromStack();
                puzzleForAdd.setPosition(x, y);
                puzzleForAdd.setName(i+"/"+j);
                tablePuzzles[i][j] = puzzleForAdd;
                addActor(tablePuzzles[i][j]);
                x += PuzzleButton.getFinalWidth() + gap;
            }
            x = posTableX;
            y += PuzzleButton.getFinalHeight() + gap;
        }
        return this;
    }

    public static PuzzleButton[][] getTablePuzzles() {
        return tablePuzzles;
    }

/*    public PuzzleButton getCell() {
        return super.getCell(actor);
    }*/

/*    public Integer getRow(PuzzleButton puzzleButton) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tablePuzzles[i][j] == puzzleButton)
                        return i;
            }
        }
    }*/

    public static Integer[] getPuzzlePos(Actor actor) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tablePuzzles[i][j].equals(actor)) {
                    return new Integer[] {i, j};
                }
            }
        }
        return new Integer[] {-1,-1};
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawChildren(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                tablePuzzles[i][j].act(delta);
    }

}
