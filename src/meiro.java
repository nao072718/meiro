import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class meiro extends MyFrame implements KeyListener {

	private int pointX; //ブロックを置いたり消したりする目印。
	private int pointY;
	private int width; //横幅と高さ。
	private int height;
	private byte[][] map; //マップを格納する配列

	public meiro(int w, int h) { //コンストラクター
		width = w;
		height = h;
		if (w % 2 != 0 && h % 2 != 0 && 5 <= w && 5 <= h) {

			map = new byte[width][height];
			make();
		} else {
			System.out.println("縦・横共に5以上の奇数で作成してください。");
		}

		addKeyListener(this);
	}

	public void run() {
		for (int f = 1; f < height; f++) {
			for (int n = 1; n < width; n++) {
				map[n][f] = 1;
			}
			map[pointX][pointY] = 0;
		}
		dig();
		for (int y = 0; y < map[0].length; y++) {
			System.out.println("");
			for (int x = 0; x < map.length; x++) {

				if (map[x][y] == 1) {
					setColor(255, 0, 255);
					fillRect(110 + x * 10, 110 + y * 10, 10, 10);

				}
			}
		}
		setColor(256,0,0);
		fillRect(120 , 120  , 10, 10);
		setColor(0,0,256);
fillRect(300,300,10,10);
	}

	int randomPos(int muki) { //x,y座標共に奇数なランダムな座標を返す
		int result = 1 + 2 * (int) Math.floor((Math.random() * (muki - 1)) / 2);
		return result;
	}

	private void make() { //マップを作成する

		pointX = randomPos(width);
		pointY = randomPos(height);

		for (int y = 0; y < height; y++) { //すべて壁で埋める。
			for (int x = 0; x < width; x++) {
				map[x][y] = 1;
			}
		}
		map[pointX][pointY] = 0;
		dig();

	}

	private void dig() {
		if (isAbleContinueDig() && map[pointX][pointY] == 0) {
			map[pointX][pointY] = 0;
			int direction = (int) Math.floor(Math.random() * 4);
			switch (direction) {
			case 0:
				if (pointY != 1) {
					if (map[pointX][pointY - 2] == 1) {
						map[pointX][pointY - 1] = 0;
						pointY -= 2;
						break;//u
					}
				}
			case 1:
				if (pointY != height - 2) {
					if (map[pointX][pointY + 2] == 1) {
						map[pointX][pointY + 1] = 0;
						pointY += 2;
						break;//d
					}
				}
			case 2:
				if (pointX != 1) {
					if (map[pointX - 2][pointY] == 1) {
						map[pointX - 1][pointY] = 0;
						pointX -= 2;
						break;//l
					}
				}
			case 3:
				if (pointX != width - 2) {
					if (map[pointX + 2][pointY] == 1) {
						map[pointX + 1][pointY] = 0;
						pointX += 2;
						break;//r
					}
				}
			}
			map[pointX][pointY] = 0;
			dig();
		} else if (isAbleDig()) {
			pointX = randomPos(width);
			pointY = randomPos(height);
			dig();
		}

	}

	private boolean isAbleDig() { //まだ掘るところがあるか確かめる
		boolean result;
		int cnt = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (x % 2 != 0 && y % 2 != 0) {

					if (map[x][y] != 0) {
						cnt++;
					}
				}
			}
		}
		if (cnt == 0) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	private boolean isAbleContinueDig() {//四方に掘れるところが残っているかどうか判断する

		if (pointY != 1) {
			if (map[pointX][pointY - 2] == 1) {
				return true;
			}
		}
		if (pointY != height - 2) {
			if (map[pointX][pointY + 2] == 1) {
				return true;
			}
		}
		if (pointX != 1) {
			if (map[pointX - 2][pointY] == 1) {
				return true;
			}
		}
		if (pointX != width - 2) {
			if (map[pointX + 2][pointY] == 1) {
				return true;
			}
		}
		return false;
	}

	public void show12() {

		for (int y = 0; y < map[0].length; y++) {
			System.out.println("");
			for (int x = 0; x < map.length; x++) {

				if (map[x][y] == 1) {
					setColor(256, 0, 0);
					System.out.print("##");

				} else {
					System.out.print("  ");
				}
			}
		}
	}

	public byte[][] getMaze() {

		return map;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyPressed(KeyEvent e) {
		clear();
		run();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
