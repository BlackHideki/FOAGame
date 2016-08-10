package character;

/**
 * エネミーのイメージ内にあるカラーの部分の座標を変数に格納するためのクラス
 * @author kudouhideki
 *
 */
public class EnemyColor {

	public static int red;

	public static int blue;

	public static int green;

	public static int yellow;

	public static int purple;

	public static int brown;

	public static int sky;

	public static int white;

	public static int black;

	public EnemyColor(int height) {
		red = height * 0;

		blue = height * 1;

		green = height * 2;

		yellow = height * 3;

		purple = height * 4;

		brown = height * 5;

		sky = height * 6;

		white = height * 7;

		black = height * 8;
	}

}
