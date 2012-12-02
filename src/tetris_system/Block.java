package tetris_system;

public class Block {
	public int x;
	public int y;
	public int status;
	// ↑ 0,1,2,3 はブロックあり、　5はブロックなしを入れる
	// 6は壁
	public Block(int xx, int yy){
		x = xx;
		y = yy;
		status=5;
	}
}
