package base;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import mechanism.GameMechanismSimple;

/**
 * ゲームの仕組みを構築する為に必要な機能を構築する為のクラス
 * @author kudouhideki
 *
 */
public class GameMechanism extends JPanel implements Runnable {

	/**
	 * ゲームの仕組みを簡略化したクラス
	 */
	private GameMechanismSimple gms;

	/**
	 * 指定された幅、高さのパネルを新しく生成する
	 */
	public GameMechanism(int width, int height){
		setSize(width, height);

		gms = new GameMechanismSimple();

		new Thread(this).start();
	}

	/**
	 * 初期化
	 */
	public void init() {
		gms.init();
	}

	/**
	 * ゲームループ
	 */
	@Override
	public void run() {
		while(true){
			action();
			repaint();
			try{
				Thread.sleep(1000/60);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 処理
	 */
	public void action(){
		gms.action();
	}

	/**
	 * 描画
	 */
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		gms.paint(graphics);
	}

	/**
	 * キーが押された瞬間に呼び出される
	 * @param key 押されたキーコード
	 */
	public void keyPressed(int key) {
		gms.keyPressed(key);
	}

	/**
	 * キーが離された瞬間に呼び出される
	 */
	public void keyReleased() {
		gms.keyReleased();
	}

	/**
	 * マウスがクリックした瞬間に呼び出される
	 * @param position クリックした瞬間の座標
	 */
	public void mouseClick(Point position) {
		gms.mouseClick(position);
	}
}
