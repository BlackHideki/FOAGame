package scene;

import java.awt.Graphics;
import java.awt.Point;

import flg.SceneFlg;

/**
 * 各シーンに最低限必要な機能を定義する為のインタフェース
 * @author kudo
 *
 */
public interface Scene {

	public void init();

	public void action();

	public void keyPressed(int key);

	public void keyReleased();

	public void mouseClick(Point position);

	public void paint(Graphics graphics);

	public SceneFlg getSceneFlg();

	public void setSceneFlg(SceneFlg sceneFlg);
}
