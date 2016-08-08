package mechanism;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import scene.GameFlg;
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
	private int sceneListIndex;

	/**
	 * 新しく生成する
	 */
	public GameMechanismSimple() {
		sceneList = new LinkedList<>();
		sceneList.add(new TitleScene());

		sceneListIndex = 0;
	}

	/**
	 * 初期化
	 */
	public void init() {
		sceneList.get(sceneListIndex).init();
	}

	/**
	 * 処理
	 */
	public void action() {
		if (sceneList.get(sceneListIndex).getGameFlg() == GameFlg.NEXT){
			if (sceneListIndex < 1) {
				sceneListIndex = 1;
			} else {
				sceneListIndex = 0;
			}
			init();
		}

		sceneList.get(sceneListIndex).action();
	}

	/**
	 * キーが押された瞬間に呼び出される
	 * @param key
	 */
	public void keyPressed(int key) {
		sceneList.get(sceneListIndex).keyPressed(key);
	}

	/**
	 * キーが離された瞬間に呼び出される
	 */
	public void keyReleased() {
		sceneList.get(sceneListIndex).keyReleased();
	}

	/**
	 * マウスがクリックした瞬間に呼び出される
	 * @param position クリックした瞬間の座標
	 */
	public void mouseClick(Point position) {
		sceneList.get(sceneListIndex).mouseClick(position);
	}

	/**
	 * 描画
	 */
	public void paint(Graphics graphics) {
		sceneList.get(sceneListIndex).paint(graphics);
	}
}
