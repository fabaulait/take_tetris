package items;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Super_Item {
	BufferedImage image;
	int speed;
	public Super_Item(String id, int speed){
		this.speed = speed;
		try{
			image = ImageIO.read(new File(id));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public Super_Item(){
		image = null;
		speed = 0;
	}
}
