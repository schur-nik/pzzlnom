package by.lfb.game.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class TablePuzzles extends Table {
    private PuzzleButton[][] tablePuzzles;
    private int size;
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
/*                puzzleForAdd.addListener(new ChangeListener() {
                    @Override
                    public void changed (ChangeEvent event, Actor actor) {
                        System.out.println("test");
                    }
                });*/
                tablePuzzles[i][j] = puzzleForAdd;
                addActor(tablePuzzles[i][j]);
                x += PuzzleButton.getFinalWidth() + gap;
            }
            x = posTableX;
            y += PuzzleButton.getFinalHeight() + gap;
        }
        return this;
    }

    public PuzzleButton[][] getTablePuzzles() {
        return tablePuzzles;
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

/*
    @Override
    public void setPosition(float x, float y) {
        posTableX = x;
        posTableY = y;
    }*/
}
