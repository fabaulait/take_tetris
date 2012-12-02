package window;

import items.Item_1;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import tetris_system.Tetris;

@SuppressWarnings("serial")
public class Set_window extends JFrame implements ActionListener {
    int time=0;
    // 画像表示に必要
    BufferStrategy bstrategy;
    // 画像取得
    Item_1[] item = new Item_1[4];
    BufferedImage backImage;
	public Set_window(String text){
		super(text);
	    Insets insets = this.getInsets();
	    setBounds(100, 100, 250+insets.right+insets.left, 400+insets.bottom+insets.top);
	    setVisible(true);
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    item[0] = new Item_1("src/images/block_black.jpg",1);
	    item[1] = new Item_1("src/images/block_pink.jpg",1);
	    item[2] = new Item_1("src/images/block_green.jpg",1);
	    item[3] = new Item_1("src/images/block_blue.jpg",1);
	    try{
	    	backImage = ImageIO.read(new File("src/images/background.jpg"));
	    }catch(IOException e){}
	}
	public void window_start(){
	    try{
	    	this.setIgnoreRepaint(true);
	    	this.createBufferStrategy(2);
	    	bstrategy = this.getBufferStrategy();
	    }catch(java.lang.IllegalStateException e){
	    }
	    MyTimerTask task = new MyTimerTask(this);
	    Timer t = new Timer();
	    t.schedule(task, 99,130);
	    addKeyListener(task.keyAction);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("actionPerformed");
	}

	class MyTimerTask extends TimerTask{
		KeyAction keyAction;
		JFrame frame;
		Tetris tetris = new Tetris();
		int time;
		int check=1;
		int[][] block = new int[4][2];
		public MyTimerTask(JFrame f){
			frame=f;
			keyAction = new KeyAction();
			time = 0;
		}
		public void run() {
			time++;
			if(time>=5 && check==1){
				tetris.next_turn(0,1);
				if(check==1){
					paint();
			    }
				time=0;
			}
			if(check==0){
				tetris.row_break();
				tetris.start_next_block();
				time=0;
			}
				// next_turn には押されたカーソルの方向x軸y軸を入力
			if(keyAction.key!=0 && tetris.next_check(keyAction.key)==1){
				if(keyAction.key==5) tetris.next_turn(0, 1);
				else if(keyAction.key==6) tetris.around();
				else tetris.next_turn(keyAction.key,0);
				paint();
			}
			check = tetris.check();
			keyAction.key=0;
		}
		public void paint(){
			Graphics g = bstrategy.getDrawGraphics();
		    if(bstrategy.contentsLost()==false){
				time++;
				g.clearRect(0, 0, 300, 450);
		    	g.drawImage(backImage,20,30,frame);
		    	for(int i=0; i<14; i++){
		    		for(int j=0; j<22;j++)
		    			if(tetris.block[i][j].status>10)
		    				g.drawImage(item[tetris.block[i][j].status-11].image,
		    						tetris.block[i][j].x,tetris.block[i][j].y ,frame);
		    	}
				block = tetris.get_next_block();
		    	for(int i=0; i<4; i++)
		    		g.drawImage(item[tetris.next_block.type].image,block[i][0],block[i][1] ,frame);
				bstrategy.show();
				g.dispose();
// 2 x 14  33 226       17*17
		    }
		}
	}
	class KeyAction extends KeyAdapter{
		public int key=0;
		public void keyPressed(KeyEvent e){
			switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
					key= -1;
					break;
				case KeyEvent.VK_RIGHT:
					key= 1;
					break;
				case KeyEvent.VK_DOWN:
					key= 5;
					break;
				default:
					key=6;
			}
		}
	}
}
