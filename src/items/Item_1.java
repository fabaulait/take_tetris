package items;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Item_1 extends Super_Item{
	public BufferedImage image;
	public int speed;
	public Item_1(String id, int speed){
		this.speed = speed;
		try{
			image = ImageIO.read(new File(id));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
