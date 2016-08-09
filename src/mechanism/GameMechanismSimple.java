package mechanism;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import scene.MainScene;
import scene.RankingScene;
import scene.RuleScene;
import scene.Scene;
import scene.TitleScene;

/**
 * ゲームの仕組みを簡略化したクラス
 * @author kudouhideki
 *
 */
public class GameMechanismSimple {

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
	public GameMechanismSimple() {
		sceneList = new LinkedList<>();
		sceneList.add(new TitleScene());
		sceneList.add(new MainScene());
		sceneList.add(new RankingScene());
		sceneList.add(new RuleScene());

		currentScene = sceneList.get(0);
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
	@SuppressWarnings("incomplete-switch")
	public void action() {
		switch (currentScene.getGameFlg()) {
		case RANKING:
			currentScene = sceneList.get(2);
			break;

		case RULE:
			currentScene = sceneList.get(3);
			break;

		case NEXT:
			if (currentScene.equals(sceneList.get(0))) {
				currentScene = sceneList.get(1);
			} else {
				currentScene = sceneList.get(0);
			}
			init();
			break;
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
