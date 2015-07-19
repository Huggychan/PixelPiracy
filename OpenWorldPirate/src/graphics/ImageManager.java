package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageManager {
	
	static Image[] images;
	public final static int BOAT = 0;
	public final static int HOMEPAGE = 1;
	public static final int COIN = 2;
	public static final int FOOD = 3;
	public static final int ENERGY = 4;
	public static final int CAPTAIN = 5;
	public static final int BACKGROUNDPIC = 6;
	public static final int PIRATE1 = 7;
	public static final int BOOK = 8;
	public static final int CHEST = 9;
	public static final int GOLDSTASH = 10;
	public static final int CITY = 11;
	public static final int SOLDOUT = 12;
	public void loadUp() 
	{
		images = new Image[13];
		images[0] =  (new ImageIcon(this.getClass().getResource("/boat.png"))).getImage();
		images[1] =  (new ImageIcon(this.getClass().getResource("/homepage.png"))).getImage();
		images[2] =  (new ImageIcon(this.getClass().getResource("/coin.png"))).getImage();
		images[3] =  (new ImageIcon(this.getClass().getResource("/food.png"))).getImage();
		images[4] =  (new ImageIcon(this.getClass().getResource("/energy.png"))).getImage();
		images[5] = (new ImageIcon(this.getClass().getResource("/captain.gif"))).getImage();
		images[6] = (new ImageIcon(this.getClass().getResource("/backgroundPic.png"))).getImage();
		images[7] =  (new ImageIcon(this.getClass().getResource("/Pirate1.png"))).getImage();
		images[8] =  (new ImageIcon(this.getClass().getResource("/book.png"))).getImage();
		images[9] =  (new ImageIcon(this.getClass().getResource("/pixelChest.png"))).getImage();
		images[10] =  (new ImageIcon(this.getClass().getResource("/goldstash.jpg"))).getImage();
		images[11] =  (new ImageIcon(this.getClass().getResource("/town.jpg"))).getImage();
		images[12] =  (new ImageIcon(this.getClass().getResource("/soldout.png"))).getImage();
	}
	public static Image getImage(int i) 
	{
		return images[i];
	}
public static Image flipImage(Image i)
{
	Image img = new BufferedImage(i.getWidth(null),i.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
	Graphics g = img.getGraphics();
	g.drawImage(i, i.getWidth(null), 0, -i.getWidth(null),i.getHeight(null), null);
	return img;
}

}
