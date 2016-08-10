package character;

import java.awt.Graphics;

/**
 * 各キャラクターに最低限必要な機能を定義する為のインタフェース
 * @author kudo
 *
 */
public interface Character {

	public void init();

	public void action();

	public void keyPressed(int key);

	public void keyReleased();

	public void paint(Graphics graphics);
}
