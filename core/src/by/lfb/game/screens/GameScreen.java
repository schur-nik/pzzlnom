package by.lfb.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import by.lfb.game.Pzzlnom;
import by.lfb.game.models.ComboList;
import by.lfb.game.models.PuzzleButton;
import by.lfb.game.models.StackPuzzle;
import by.lfb.game.models.TablePuzzles;

public class GameScreen implements Screen {
	private final Pzzlnom game;
	private Stage stage;
	private OrthographicCamera camera;
	private Table gridPuzzles;
	private StackPuzzle stackPuzzle;
	private TablePuzzles tablePuzzles;
	private Map<Actor, Float> gridPosPuzzles;
	private Boolean flagChangedTable = true;

	public GameScreen (final Pzzlnom game) {
		this.game = game;

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		stackPuzzle = new StackPuzzle();
		initTablePuzzles();
		//gridPosPuzzles = new HashMap<>();
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();

		if (flagChangedTable) {
			updateTest();
			flagChangedTable = false;
		}

	}

	private void updateTest() {
	}

	private void initTablePuzzles() {
		tablePuzzles = new TablePuzzles(8, 400f, 240f).createTablePuzzles();
		stage.addActor(tablePuzzles);
	} //.draw(stage.getBatch(), 0f)

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
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
		stage.dispose();
	}
}
