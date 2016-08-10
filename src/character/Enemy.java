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
	 * 初期化
	 */
	@Override
	public void init() {
		image = new ImageFileReader("images/enemy.png", 560, 400);

		size = new Dimension(40, 40);

		position = new Point(0, 0);
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
		graphics.drawImage(image.getImage(), position.x, position.y, null);
	}

}
