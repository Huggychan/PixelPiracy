package Launcher;

import playerInteraction.GameKeyListener;
import graphics.FontManager;
import graphics.ImageManager;
import gui.GameInstance;
import gui.GamePainter;
import gui.GameWindow;

public class Launcher
{
	public static void main(String[] args)
	{
		Launcher l = new Launcher();
		l.init();
	}
	public void init()
	{
		ImageManager im = new ImageManager();
		im.loadUp();
		FontManager fm = new FontManager();
		fm.loadFonts();
		GameInstance gi = new GameInstance();
		GameKeyListener gameKeyList = new GameKeyListener(gi);
		GameWindow gw = new GameWindow(gi);
		GamePainter.setP(gw);
		gw.addKeyListener(gameKeyList);
		Thread t1 = new Thread(gi);
		t1.start();
	//Thread graphicsThread = new Thread(gw);
		//graphicsThread.start();
	}
}
