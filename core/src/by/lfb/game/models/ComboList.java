package by.lfb.game.models;

import com.badlogic.gdx.utils.Array;

public class ComboList {
    private static Array<PuzzleButton> comboList = new Array<PuzzleButton>();
    private static String typeCombo;

    public static void addToCombo(PuzzleButton puzzle) {
        if (comboList.size == 0)
            typeCombo = puzzle.getTYPE();
        comboList.add(puzzle);
    }

    public static void clearCombo() {
        typeCombo = null;
        comboList.clear();
    }

    public static String getTypeCombo() {
        return typeCombo;
    }

    public static Boolean containsInCombo(PuzzleButton puzzle) {
        if (comboList.contains(puzzle, false))
            return true;
        else
            return false;
    }

    public static Array<PuzzleButton> getComboList() {
        return comboList;
    }

    public static PuzzleButton getLast() {
        return comboList.get(comboList.size-1);
    }
    public static PuzzleButton getPrevLast() {
        if (comboList.size > 1)
            return comboList.get(comboList.size-2);
        else return null;
    }
}
