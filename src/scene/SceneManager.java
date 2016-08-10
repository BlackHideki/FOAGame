package scene;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

/**
 * ゲームの仕組みを簡略化したクラス
 * @author kudouhideki
 *
 */
public class SceneManager {

	/**
	 * 各シーンを格納するリスト
	 */
	private LinkedList<Scene> sceneList;

	/**
	 * 現在のシーン
	 */
	private Scene currentScene;

	/**
	 * 新しく生成する
	 */
	public SceneManager() {
		sceneList = new LinkedList<>();
		sceneList.add(new TitleScene());
		sceneList.add(new MainScene());
		sceneList.add(new RankingScene());
		sceneList.add(new RuleScene());

		currentScene = sceneList.get(1);
	}

	/**
	 * 初期化
	 */
	public void init() {
		currentScene.init();
	}

	/**
	 * 処理
	 */
	public void action() {
		if (currentScene.getSceneFlg() != null) {
			switch (currentScene.getSceneFlg()) {
			case TITLE:
				currentScene = sceneList.get(0);
				init();
				break;

			case MAIN:
				currentScene = sceneList.get(1);
				init();
				break;

			case RANKING:
				currentScene = sceneList.get(2);
				init();
				break;

			case RULE:
				currentScene = sceneList.get(3);
				break;
			}
		}

		currentScene.action();
	}

	/**
	 * キーが押された瞬間に呼び出される
	 * @param key
	 */
	public void keyPressed(int key) {
		currentScene.keyPressed(key);
	}

	/**
	 * キーが離された瞬間に呼び出される
	 */
	public void keyReleased() {
		currentScene.keyReleased();
	}

	/**
	 * マウスがクリックした瞬間に呼び出される
	 * @param position クリックした瞬間の座標
	 */
	public void mouseClick(Point position) {
		currentScene.mouseClick(position);
	}

	/**
	 * 描画
	 */
	public void paint(Graphics graphics) {
		currentScene.paint(graphics);
	}
}
