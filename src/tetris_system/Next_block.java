package tetris_system;

public class Next_block {
	//  新クラス 落ちるブロック定義
	public int type;
	int[][] a = new int[4][2];
	public Next_block(int type){
		this.type = type;
		a[0][0]=6;
		a[0][1]=0;
		switch(type){
		// 棒
			case 1:
				int j=1;
				for(int i=1; i<4; i++){
					a[i][0]=6;
					a[i][1]=j;
					j++;
				}
				break;
		// T字
			case 2:
				j=5;
				for(int i=1; i<4; i++){
					a[i][0]=j;
					a[i][1]=1;
					j++;
				}
				break;
		// L字
			case 3:
				j=0;
				for(int i=1; i<4; i++){
					a[i][0]=7;
					a[i][1]=j;
					j++;
				}
				break;
			default:// 0の時、四角
				a[1][0] = 6;
				a[1][1] = 1;
				a[2][0] = 7;
				a[2][1] = 0;
				a[3][0] = 7;
				a[3][1] = 1;
				break;
		}
	}
	public void set(int rl, int d){
		a[0][0]+=rl;
		a[0][1]+=d;
		a[1][0]+=rl;
		a[1][1]+=d;
		a[2][0]+=rl;
		a[2][1]+=d;
		a[3][0]+=rl;
		a[3][1]+=d;
	}
	public int[][] getPoint(){
		return a;
	}
	public void around(){
		switch(type){
			case 1:
				stick();
				break;
			case 2:
				t_or_l();
				break;
			case 3:
				t_or_l();
				break;
			default:
				break;
		}
	}
	public void t_or_l(){
		for(int i=0; i<2; i++){
			int x = a[i][0]-a[2][0];
			int y = a[i][1]-a[2][1];
			a[i][0]=a[2][0]-y;
			a[i][1]=a[2][1]+x;
		}
		int x = a[3][0]-a[2][0];
		int y = a[3][1]-a[2][1];
		a[3][0]=a[2][0]-y;
		a[3][1]=a[2][1]+x;
	}
	public void stick(){
		if(a[0][1]==a[2][1]){
			int j=a[2][1]-2;
			for(int i=0; i<4; i++){
				a[i][0]=a[2][0];
				a[i][1]=j;
				j++;
			}
		}
		else{
			int j=a[2][0]-2;
			for(int i=0; i<4; i++){
				a[i][1]=a[2][1];
				a[i][0]=j;
				j++;
			}
		}
	}
}
