package character;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import base.Execute;
import file.ImageFileReader;

public class Efect implements Character {

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
	 * イメージの中のX座標セル数
	 */
	private int xSell;

	/**
	 * イメージの中のX座標(切り替え前)
	 */
	private int xSellBefore;

	/**
	 * 切り替える時間
	 */
	private float animeTimer;

	/**
	 * Efect を新しく生成
	 */
	public Efect() {
		image = new ImageFileReader("images/efect.png", 50 * 30, 50 * 9);

		size = new Dimension(50, 50);

		position = new Point(Execute.WINDOW_WIDTH - size.width, Execute.WINDOW_HEIGHT - size.height * 4 + size.height / 2);
	}

	@Override
	public void init() {
		xSell = 3;

		xSellBefore = -1;

		animeTimer = 0.0f;
	}

	@Override
	public void action() {
		float animeTime = 0.3f;

		if (animeTimer > 0.0f) {
			animeTimer -= 1 / 60.0f;
		} else {
			switch (xSell) {
			case 3:
				xSell = 4;
				xSellBefore = 3;
				break;

			case 4:
				if (xSellBefore == 5) {
					xSell = 3;
				} else {
					xSell = 5;
				}
				xSellBefore = 4;
				break;

			case 5:
				xSell = 4;
				xSellBefore = 5;
				break;
			}
			animeTimer = animeTime;
		}
	}

	@Override
	public void keyPressed(int key) {

	}

	@Override
	public void keyReleased() {

	}

	@Override
	public void paint(Graphics graphics) {
		graphics.drawImage(image.getImage().getSubimage(size.width * xSell, size.height * 8, size.width, size.height), position.x, position.y, null);
	}

}
