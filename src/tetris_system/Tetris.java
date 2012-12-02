package tetris_system;

import java.util.Random;

public class Tetris {
	public Block[][] block = new Block[14][22];
	public Next_block next_block;
	private Random rnd = new Random();
	public Tetris(){
		for(int i = 0; i<14; i++){
			for(int j = 0; j<22; j++){
				block[i][j] = new Block(i*16+4, j*16+30);
			}
		}
		for(int i=0; i<14; i++) block[i][21].status=10;
		for(int i=0; i<22; i++){
			block[0][i].status=10;
			block[1][i].status=10;
			block[12][i].status=10;
			block[13][i].status=10;
		}
		next_block = new Next_block(0);
	}
	public int check(){
		for(int i=0; i<4; i++){
			if(block[next_block.a[i][0]][next_block.a[i][1]+1].status>=10){
				for(int j=0; j<4; j++){
					block[next_block.a[j][0]][next_block.a[j][1]].status=
														next_block.type+11;
				}
				return 0;
			}
		}
		return 1;
	}
	public int next_check(int x){
		Next_block b = new Next_block(next_block.type);
		for(int i=0; i<4; i++){
			b.a[i][0]=next_block.a[i][0];
			b.a[i][1]=next_block.a[i][1];
		}
		if(x==5) b.set(0, 1);
		else if(x==6) b.around();
		else if(x==1 || x==-1) b.set(x,0);
		for(int i=0; i<4; i++){
			if(block[b.a[i][0]][b.a[i][1]].status>=10){
				return 0;
			}
		}
		return 1;
	}
	public void start_next_block(){
		next_block = new Next_block(rnd.nextInt(4));
	}
	public int[][] get_next_block(){
		int [][] temp = next_block.getPoint();
		int [][] ret = new int[4][2];
		for(int i=0; i<4; i++){
			ret[i][0] = block[temp[i][0]][temp[i][1]].x;
			ret[i][1] = block[temp[i][0]][temp[i][1]].y;
		}
		return ret;
	}
	public void next_turn(int rl, int ob){
		next_block.set(rl,ob);
	}
	public void around(){
		next_block.around();
	}
	public void row_break(){
		int j;
		int s=0;
		int x=0;
		boolean flag=false;
		for(int i=20; i>=0; i--){
			for(j=2; j<12; j++){
				if(block[j][i].status==5){
					break;
				}
			}
			if(j==12){
				s=i+x;
				x++;
				flag=true;
			}
			else if(flag==true){
				for(int k=2; k<12; k++){
					block[k][s].status=block[k][i].status;
				}
				s--;
			}
		}
	}
}

