package scene;

import java.awt.Graphics;
import java.awt.Point;

public class RankingScene implements Scene {

	/**
	 * ゲームフラグ
	 */
	private GameFlg gameFlg;

	/**
	 * RankingScene を新しく生成
	 */
	public RankingScene () {
		gameFlg = GameFlg.TOP;
	}

	/**
	 * 初期化
	 */
	@Override
	public void init() {
		gameFlg = GameFlg.TOP;
	}

	/**
	 * 処理
	 */
	@Override
	public void action() {
	}

	/**
	 * キー押下
	 */
	@Override
	public void keyPressed(int key) {
	}

	/**
	 * キー解放
	 */
	@Override
	public void keyReleased() {
	}

	/**
	 * マウスクリック
	 */
	@Override
	public void mouseClick(Point position) {
	}

	/**
	 * 描画
	 */
	@Override
	public void paint(Graphics graphics) {
	}

	/**
	 * ゲームフラグ取得
	 */
	@Override
	public GameFlg getGameFlg() {
		return gameFlg;
	}

}
