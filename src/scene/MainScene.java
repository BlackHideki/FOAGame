package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import base.Execute;
import character.Efect;
import character.Enemy;
import character.Player;
import file.ImageFileReader;
import file.WAVFileReader;
import flg.GameFlg;
import flg.SceneFlg;

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
	 * ゲームフラグ
	 */
	private GameFlg gameFlg;

	/**
	 * 背景
	 */
	private ImageFileReader bg;

	/**
	 * ゲームスタートボタン
	 */
	private ImageFileReader gameStartBtn;

	/**
	 * bgm
	 */
	private WAVFileReader bgm;

	/**
	 * 死んだ音
	 */
	private WAVFileReader deadSE;

	/**
	 * ゲームスタート案内
	 */
	private Efect gameStartInfo;

	/**
	 * プレイヤー
	 */
	private Player player;

	/**
	 * エネミー
	 */
	private ArrayList<Enemy> enemyList;

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

		gameStartBtn = new ImageFileReader("images/enemy.png", 700, 500);

		bgm = new WAVFileReader("sound/main_bgm.wav");

		deadSE = new WAVFileReader("sound/dead.wav");

		gameStartInfo = new Efect();

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

		bgm.loop();

		gameFlg = GameFlg.READY;

		gameStartInfo.init();

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

		appEnemyTimer = 0.0f;

		score = 0;
	}

	/**
	 * 処理
	 */
	@Override
	public void action() {
		player.action();

		switch (gameFlg) {
		case READY:
			gameStartInfo.action();
			if (Execute.WINDOW_WIDTH - 50 < player.getPosition().x) {
				gameFlg = GameFlg.START;
			}
			break;

		case START:
			for (Enemy e : enemyList) {
				e.action();

				if (isCollision(player.getPosition(), e.getPosition(), player.getSize(), e.getSize())) {
					deadSE.play();
					sceneFlg = SceneFlg.GAMEOVER;
					bgm.stop();
					return;
				}
			}

			appEnemy();

			score += 1/ 60.0f;
			break;

		case END:
			break;
		}
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

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("メイリオ", Font.BOLD, 50));
		graphics.drawString("SCORE : " + String.format("%.1f", score), 0, Execute.WINDOW_HEIGHT - 25);

		if (gameFlg == GameFlg.READY) {
			gameStartInfo.paint(graphics);
			graphics.drawImage(gameStartBtn.getImage().getSubimage(50 * 2 - 10, 50 * 1, 50, 50 - 30), Execute.WINDOW_WIDTH - 50, Execute.WINDOW_HEIGHT - 101, null);
		}
	}

	/**
	 * ゲームフラグを取得
	 */
	@Override
	public SceneFlg getSceneFlg() {
		return sceneFlg;
	}

	/**
	 * ゲームフラグを格納
	 */
	@Override
	public void setSceneFlg(SceneFlg sceneFlg) {
		this.sceneFlg = sceneFlg;
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
	 * 当たり判定
	 * @param p1
	 * @param p2
	 * @param d1
	 * @param d2
	 * @return
	 */
	private boolean isCollision(Point p1, Point p2, Dimension d1, Dimension d2) {
		boolean result = false;

		if (p1.x < p2.x + (d2.width - 10) && p1.x + (d1.width - 10) > p2.x && p1.y < p2.y + (d2.height - 10) && p1.y + (d1.height - 10) > p2.y) {
			result = true;
		}

		return result;
	}

	/**
	 * エネミーの出現処理
	 */
	private void appEnemy() {
		float appEnemyTime = 0.1f;

		if (appEnemyTimer > 0.0f) {
			appEnemyTimer -= 1/ 60.0f;
		} else {
			appEnemyTimer = appEnemyTime;

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

}
