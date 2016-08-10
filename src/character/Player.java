package character;

import java.awt.Dimension;
import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

import base.Execute;
import file.ImageFileReader;

/**
 * プレイヤーを構築する為のクラス
 * @author kudo
 *
 */
public class Player implements Character {

	/**
	 * イメージ
	 */
	private ImageFileReader image;

	/**
	 * サイズ
	 */
	private Dimension size;

	/**
	 * 表示されているX座標
	 */
	private int positionX;

	/**
	 * 左を向いている時のイメージ
	 */
	private int dirLeft;

	/**
	 * 右を向いている時のイメージ
	 */
	private int dirRight;

	/**
	 * 向き
	 */
	private int dir;

	/**
	 * イメージの中のX座標(アニメーション処理用)
	 */
	private int imageInX;

	/**
	 * イメージの中のX座標(切り替え前)
	 */
	private int imageInXBefore;

	/**
	 * 切り替える時間
	 */
	private float animeTimer;

	/**
	 * 最後に押下したキー
	 */
	private int key;

	/**
	 * Player を新しく生成
	 */
	public Player() {
		image = new ImageFileReader("images/player.png", 150, 200);

		size = new Dimension(50, 50);
	}

	/**
	 * 初期化
	 */
	@Override
	public void init() {
		positionX = 0;

		dirLeft = 50;

		dirRight = 100;

		dir = dirRight;

		imageInX = 0;

		imageInXBefore = 0;

		animeTimer = 0.0f;

		key = -1;
	}

	/**
	 * 処理
	 */
	@Override
	public void action() {
		switch (key) {
		case KeyEvent.VK_LEFT:
			if (dir != dirLeft) {
				dir = dirLeft;
			}

			if (positionX >= - 9) {
				positionX -= 5;
			}
			break;

		case KeyEvent.VK_RIGHT:
			if (dir != dirRight) {
				dir = dirRight;
			}
			if (positionX <= Execute.WINDOW_WIDTH - size.width + 9) {
				positionX += 5;
			}
			break;
		}

		if ( key != -1) {
			anime();
		} else {
			animeStop();
		}
	}

	/**
	 * キー押下
	 */
	@Override
	public void keyPressed(int key) {
		this.key = key;
	}

	/**
	 * キー解放
	 */
	@Override
	public void keyReleased() {
		key = -1;
	}

	/**
	 * 描画
	 */
	@Override
	public void paint(Graphics graphics) {
		graphics.drawImage(image.getImage().getSubimage(imageInX, dir, size.width, size.height), positionX, Execute.WINDOW_HEIGHT - 131, null);
	}

	/**
	 * 移動アニメーション
	 */
	private void anime() {
		if (animeTimer > 0.0f) {
			animeTimer -= 1 / 60.0f;
		} else {
			switch (imageInX) {
			case 0:
				imageInX = 50;
				imageInXBefore = 0;
				break;

			case 50:
				if (imageInXBefore == 100) {
					imageInX = 0;
				} else {
					imageInX = 100;
				}
				imageInXBefore = 50;
				break;

			case 100:
				imageInX = 50;
				imageInXBefore = 100;
				break;
			}
			animeTimer = 0.1f;
		}
	}

	/**
	 * 移動アニメーション停止
	 */
	private void animeStop() {
		if (animeTimer != 0.0f) {
			animeTimer = 0.0f;
		}

		if (imageInX != 50) {
			imageInX = 50;
		}
	}

}
