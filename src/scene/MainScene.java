package scene;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

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
	private ArrayList<Enemy> enemyList;

	/**
	 * エネミーが出現される時間
	 */
	private float appEnemyTime;

	/**
	 * エネミーが出現されるまでの時間を計測
	 */
	private float appEnemyTimer;

	/**
	 * スコア
	 */
	private float score;

	/**
	 * MainScene を新しく生成
	 */
	public MainScene () {
		bg = new ImageFileReader("images/bg.png");

		player = new Player();

		enemyList = new ArrayList<>();
		int enemyNum = 100;
		for (int i = 0; i < enemyNum; i++) {
			enemyList.add(new Enemy());
		}
	}

	/**
	 * 初期化
	 */
	@Override
	public void init() {
		sceneFlg = null;

		player.init();

		for (Enemy e : enemyList) {
			e.init();
			int i = enemyList.indexOf(e);
			if (i > 0) {
				e.setPositionRandom(enemyList.get(i - 1).getPosition().x);
			} else {
				e.setPositionRandom(enemyList.get(enemyList.size() - 1).getPosition().x);
			}
		}

		appEnemyTime = 0.1f;

		appEnemyTimer = 0.0f;

		score = 0;
	}

	/**
	 * 処理
	 */
	@Override
	public void action() {
		player.action();

		for (Enemy e : enemyList) {
			e.action();

			if (isCollision(player.getPosition(), e.getPosition(), player.getSize(), e.getSize())) {
				sceneFlg = SceneFlg.GAMEOVER;
				return;
			}
		}

		if (appEnemyTimer > 0.0f) {
			appEnemyTimer -= 1/ 60.0f;
		} else {
			appEnemyTimer = appEnemyTime;
			appEnemy();
		}

		score += 1/ 60.0f;
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

		for (Enemy e : enemyList) {
			e.paint(graphics);
		}

		graphics.setFont(new Font("メイリオ", Font.BOLD, 50));
		graphics.drawString("SCORE : " + String.format("%.1f", score), 0, 50);
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

	/**
	 * ゲームフラグ取得
	 */
	@Override
	public SceneFlg getSceneFlg() {
		return sceneFlg;
	}

	/**
	 * 当たり判定
	 * @param p1
	 * @param p2
	 * @param d1
	 * @param d2
	 * @return
	 */
	private boolean isCollision(Point p1, Point p2, Dimension d1, Dimension d2) {
		boolean result = false;

		if (p1.x < p2.x + d2.width && p1.x + d1.width > p2.x && p1.y < p2.y + d2.height && p1.y + d1.height > p2.y) {
			result = true;
		}

		return result;
	}

	/**
	 * エネミーの出現処理
	 */
	private void appEnemy() {
		for (Enemy e : enemyList) {
			if (!e.getMoveFlg()) {
				int i = enemyList.indexOf(e);
				if (i > 0) {
					e.setPositionRandom(enemyList.get(i - 1).getPosition().x);
				} else {
					e.setPositionRandom(enemyList.get(enemyList.size() - 1).getPosition().x);
				}
				e.setMoveFlg(true);
				break;
			}
		}
	}

}
