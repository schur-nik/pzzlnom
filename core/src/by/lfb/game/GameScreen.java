package by.lfb.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Iterator;

import by.lfb.game.models.PuzzleButton;
import by.lfb.game.models.StackPuzzle;

public class GameScreen implements Screen {
	private final Pzzlnom game;
	private Stage stage;
	private OrthographicCamera camera;
	private Table tablePuzzleButtons;
	private StackPuzzle stackPuzzle;
	private Array<PuzzleButton> arrPuzzles;
	private Integer X;

	public GameScreen (final Pzzlnom game) {
		X = 200;
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		stage = new Stage();
		stackPuzzle = new StackPuzzle();
		arrPuzzles = new Array<PuzzleButton>();
		addPuzzleInArray();
	}

	@Override
	public void render (float delta) {
		ScreenUtils.clear(0, 0.2f, 0.2f, 1);
		update(delta);

		for (PuzzleButton arrPuzzle: arrPuzzles){
			stage.addActor(arrPuzzle);
		}
		stage.draw();

		if(Gdx.input.isTouched()){
			addPuzzleInArray();
		}

		Iterator<PuzzleButton> iter = arrPuzzles.iterator();
		while (iter.hasNext()) {
			PuzzleButton puzzleButton = iter.next();
			puzzleButton.setY(puzzleButton.getY()-200 * Gdx.graphics.getDeltaTime());
			if (puzzleButton.getY() + X < 0) {
				X += 105;
				break;
			};
		}
	}

	private void addPuzzleInArray() {
		PuzzleButton puzzleForAdd = stackPuzzle.getPuzzleFromStack();
		puzzleForAdd.setX(400);
		puzzleForAdd.setY(400);
		arrPuzzles.add(puzzleForAdd);
	}

	private void update(float delta){
		stage.act(delta);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		stage.clear();
		//initGrid();
	}

	private void initGrid() {
		stackPuzzle = new StackPuzzle();
		tablePuzzleButtons = new Table();
		tablePuzzleButtons.setPosition(400,240);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tablePuzzleButtons.add(stackPuzzle.getPuzzleFromStack());
			}
			tablePuzzleButtons.row();
		}
		stage.addActor(tablePuzzleButtons);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
