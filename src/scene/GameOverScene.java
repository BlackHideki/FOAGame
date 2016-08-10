package scene;

import java.awt.Graphics;
import java.awt.Point;

import file.TextFileReader;

public class GameOverScene implements Scene {



	/**
	 * ランキング
	 */
	private TextFileReader ranking;

	/**
	 * スコア
	 */
	private float score;

	/**
	 * ResultScene を新しく生成
	 */
	public GameOverScene () {
		ranking = new TextFileReader("text/ranking.txt");
	}

	@Override
	public void init() {

	}

	@Override
	public void action() {

	}

	@Override
	public void keyPressed(int key) {

	}

	@Override
	public void keyReleased() {

	}

	@Override
	public void mouseClick(Point position) {

	}

	@Override
	public void paint(Graphics graphics) {

	}

	@Override
	public SceneFlg getSceneFlg() {
		return null;
	}

	/**
	 * スコアを取得
	 * @return
	 */
	public float getScore() {
		return score;
	}

	/**
	 * スコアを格納
	 * @param score
	 */
	public void setScore(float score) {
		this.score = score;
	}

}
