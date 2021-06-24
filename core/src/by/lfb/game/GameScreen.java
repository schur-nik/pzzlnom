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
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	private final Pzzlnom game;
	private Stage stage;
	private OrthographicCamera camera;

	public GameScreen (final Pzzlnom game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		stage = new Stage();
	}

	@Override
	public void render (float delta) {
		//Gdx.gl.glClearColor(1f,1f,1f,1f);
		ScreenUtils.clear(0, 0.2f, 0.2f, 1);
		update(delta);
		stage.draw();
/*		camera.update();
		game.batch.setProjectionMatrix(camera.combined);*/

/*		game.batch.begin();

		game.batch.end();*/
/*		game.batch.begin();
		game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);
		game.batch.draw(bucketImage, bucket.x, bucket.y);
		for(Rectangle raindrop: raindrops) {
			game.batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		game.batch.end();

		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}

		if(bucket.x < 0)
			bucket.x = 0;
		if(bucket.x > 800 - 64)
			bucket.x = 800 - 64;

		if(TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnRaindrop();

		Iterator<Rectangle> iter = raindrops.iterator();
		while (iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0)
				iter.remove();
			if (raindrop.overlaps(bucket)) {
				dropsGathered++;
				iter.remove();
			}
		}*/

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
		initGrid();
	}

	private void initGrid() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/impact2.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 22;
		BitmapFont font12 = generator.generateFont(parameter);
		generator.dispose();
		Skin skin = new Skin();
		skin.add("defaultFont", font12);
		skin.addRegions(new TextureAtlas(Gdx.files.internal("puzzleBlocks.pack")));
		skin.load(Gdx.files.internal("puzzleBlocks.json"));
/*		Skin skinTemp = new Skin(Gdx.files.internal("puzzleBlocks.json"), new TextureAtlas("puzzleBlocks.pack"));*/
		TextButton btn = new TextButton("1", skin, "dirt");
		//Button[][] btnGrid = new Button[7][7];
		/*Skin skin = new Skin();
		TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.local("puzzleBlocks.pack"));
		skin.addRegions(buttonAtlas);
		BitmapFont font = new BitmapFont();

		TextButton.TextButtonStyle btnStyle = new TextButton.TextButtonStyle();
		btnStyle.up = skin.getDrawable("copper");
		btnStyle.down = skin.getDrawable("dirt");
		btnStyle.font = font;

		Button btn1 = new TextButton("1", btnStyle);
		btn1.setPosition(100, 100);
		btn1.setHeight(100);
		btn1.setWidth(100);

		btn1.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				System.out.println("Button Pressed");
			}
		});
*/
		stage.addActor(btn);
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
/*		dropImage.dispose();
		bucketImage.dispose();*/
	}
}
