package character;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import file.ImageFileReader;

/**
 * エネミーを構築する為のクラス
 * @author kudo
 *
 */
public class Enemy implements Character {

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
	private Point position;

	/**
	 * エネミーをカラー別に配列に格納
	 */
	private int[] enemyColor;

	/**
	 * Enemy を新しく生成
	 */
	public Enemy() {
		image = new ImageFileReader("images/enemy.png", 560, 400);

		size = new Dimension(40, 40);

		position = new Point(0, 0);
	}

	/**
	 * 初期化
	 */
	@Override
	public void init() {

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
	 * 描画
	 */
	@Override
	public void paint(Graphics graphics) {
		graphics.drawImage(image.getImage().getSubimage(70, size.height, size.width, size.height), position.x + 10, position.y, null);
	}

}
