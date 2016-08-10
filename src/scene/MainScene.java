package scene;

import java.awt.Graphics;
import java.awt.Point;

import character.Enemy;
import character.Player;
import file.ImageFileReader;

/**
 * メインシーンを構築する為のクラス
 * @author kudo
 *
 */
public class MainScene implements Scene {

	/**
	 * シーンフラグ
	 */
	private SceneFlg sceneFlg;

	/**
	 * 背景
	 */
	private ImageFileReader bg;

	/**
	 * プレイヤー
	 */
	private Player player;

	/**
	 * エネミー
	 */
	private Enemy enemy;

	/**
	 * MainScene を新しく生成
	 */
	public MainScene () {
		bg = new ImageFileReader("images/bg.png");

		player = new Player();

		enemy = new Enemy();
	}

	/**
	 * 初期化
	 */
	@Override
	public void init() {
		sceneFlg = null;

		player.init();

		enemy.init();
	}

	/**
	 * 処理
	 */
	@Override
	public void action() {
		player.action();

		enemy.action();
	}

	/**
	 * キー押下
	 */
	@Override
	public void keyPressed(int key) {
		player.keyPressed(key);
	}

	/**
	 * キー解放
	 */
	@Override
	public void keyReleased() {
		player.keyReleased();
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
		graphics.drawImage(bg.getImage(), 0, 0, null);

		player.paint(graphics);

		enemy.paint(graphics);
	}

	/**
	 * ゲームフラグ取得
	 */
	@Override
	public SceneFlg getSceneFlg() {
		return sceneFlg;
	}

}
