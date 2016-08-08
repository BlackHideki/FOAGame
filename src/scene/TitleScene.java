package scene;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

import base.Execute;
import file.ImageFileReader;

public class TitleScene implements Scene {

	/**
	 * ゲームフラグ
	 */
	private GameFlg gameFlg;

	/**
	 * 背景
	 */
	private ImageFileReader bg;

	/**
	 * タイトルロゴ
	 */
	private ImageFileReader titleLogo;

	/**
	 * メニュースタート
	 */
	private ImageFileReader menuStart;

	/**
	 * メニューランキング
	 */
	private ImageFileReader menuRanking;

	/**
	 * メニュールール
	 */
	private ImageFileReader menuRule;

	/**
	 * カーソル
	 */
	private ImageFileReader cursor;

	/**
	 * カーソルの表示座標
	 */
	private ArrayList<Point> cursorPointList;

	/**
	 * カーソルの現在の表示座標
	 */
	private Point currentPoint;

	/**
	 * TitleScene を新しく生成
	 */
	public TitleScene () {
		gameFlg = GameFlg.TOP;

		bg = new ImageFileReader("images/bg.png");

		titleLogo = new ImageFileReader("images/title_logo.png", 500, 150);

		menuStart = new ImageFileReader("images/menu_start.png", 101, 50);

		menuRanking = new ImageFileReader("images/menu_ranking.png", 152, 50);

		menuRule = new ImageFileReader("images/menu_rule.png", 88, 50);

		cursor = new ImageFileReader("images/player.png", 120, 160);

		cursorPointList = new ArrayList<>();
		cursorPointList.add(new Point(Execute.WINDOW_WIDTH / 2 - menuStart.getSize().width, Execute.WINDOW_HEIGHT / 2 - menuStart.getSize().height));
		cursorPointList.add(new Point(Execute.WINDOW_WIDTH / 2 - menuRanking.getSize().width, Execute.WINDOW_HEIGHT / 2));
		cursorPointList.add(new Point(Execute.WINDOW_WIDTH / 2 - menuRule.getSize().width, Execute.WINDOW_HEIGHT / 2 + menuRule.getSize().height));

		currentPoint = cursorPointList.get(0);
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
		switch (key) {
		case KeyEvent.VK_UP:
			if(currentPoint.equals(cursorPointList.get(1))) {
				currentPoint = cursorPointList.get(0);
			} else if (currentPoint.equals(cursorPointList.get(2))) {
				currentPoint = cursorPointList.get(1);
			}
			break;

		case KeyEvent.VK_DOWN:
			if(currentPoint.equals(cursorPointList.get(0))) {
				currentPoint = cursorPointList.get(1);
			} else if (currentPoint.equals(cursorPointList.get(1))) {
				currentPoint = cursorPointList.get(2);
			}
			break;
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
		graphics.drawImage(bg.getImage(), 0, 0, null);

		graphics.drawImage(titleLogo.getImage(), Execute.WINDOW_WIDTH / 2 - titleLogo.getSize().width / 2, Execute.WINDOW_HEIGHT / 5, null);

		graphics.drawImage(menuStart.getImage(), Execute.WINDOW_WIDTH / 2 - menuStart.getSize().width / 2, Execute.WINDOW_HEIGHT / 2 - menuStart.getSize().height, null);

		graphics.drawImage(menuRanking.getImage(), Execute.WINDOW_WIDTH / 2 - menuRanking.getSize().width / 2, Execute.WINDOW_HEIGHT / 2, null);

		graphics.drawImage(menuRule.getImage(), Execute.WINDOW_WIDTH / 2 - menuRule.getSize().width / 2, Execute.WINDOW_HEIGHT / 2 + menuRule.getSize().height, null);

		graphics.drawImage(cursor.getImage().getSubimage(40, 0, 40, 40), currentPoint.x, currentPoint.y, null);
	}

	/**
	 * ゲームフラグ取得
	 */
	@Override
	public GameFlg getGameFlg() {
		return gameFlg;
	}

}
