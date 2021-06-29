package by.lfb.game.models;

import java.util.Stack;

public class StackPuzzle{

    private static Stack<PuzzleButton> stackPuzzles;
    private static final Integer maxNumberOfElemInStack = 9;

    public StackPuzzle() {
        stackPuzzles = new Stack<>();
        while (stackPuzzles.size() < maxNumberOfElemInStack) {
            stackPuzzles.push(new PuzzleButton("", PuzzleButton.getPuzzleSkin(), PuzzleButton.getRandomPuzzleStyleName()));
        }
    }

    public static void pushPuzzleInStack(String puzzleName) {
        stackPuzzles.push(new PuzzleButton("", PuzzleButton.getPuzzleSkin(), puzzleName));
    }

    public static void addPuzzleInStackInRange(String puzzleName, Integer range) {
        stackPuzzles.add(((int)(Math.random() * range + 1))-1, new PuzzleButton("", PuzzleButton.getPuzzleSkin(), puzzleName));
    }

    public static PuzzleButton getPuzzleFromStack() {
        PuzzleButton res = stackPuzzles.pop();
        while (stackPuzzles.size() < maxNumberOfElemInStack) {
            stackPuzzles.add(stackPuzzles.size(), new PuzzleButton("", PuzzleButton.getPuzzleSkin(), PuzzleButton.getRandomPuzzleStyleName()));
        }
        return res;
    }

}
