package scene;

import java.awt.Graphics;
import java.awt.Point;

import com.sun.glass.events.KeyEvent;

import base.Execute;
import file.ImageFileReader;

public class RuleScene implements Scene {

	/**
	 * シーンフラグ
	 */
	private SceneFlg sceneFlg;

	/**
	 * カーソル
	 */
	private ImageFileReader cursor;

	/**
	 * メニューバック
	 */
	private ImageFileReader menuBack;

	/**
	 * TitleScene を新しく生成
	 */
	public RuleScene () {
		cursor = new ImageFileReader("images/player.png", 120, 160);

		menuBack = new ImageFileReader("images/menu_back.png", 88, 50);
	}

	/**
	 * 初期化
	 */
	@Override
	public void init() {
		sceneFlg = null;
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
		if (key == KeyEvent.VK_ENTER) {
			sceneFlg = SceneFlg.TITLE;
		}
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
		graphics.drawImage(menuBack.getImage(), Execute.WINDOW_WIDTH - menuBack.getSize().width, Execute.WINDOW_HEIGHT - menuBack.getSize().height, null);

		graphics.drawImage(cursor.getImage().getSubimage(40, 0, 40, 40), Execute.WINDOW_WIDTH - menuBack.getSize().width - 40, Execute.WINDOW_HEIGHT - menuBack.getSize().height, null);
	}

	/**
	 * ゲームフラグ取得
	 */
	@Override
	public SceneFlg getSceneFlg() {
		return sceneFlg;
	}

}
