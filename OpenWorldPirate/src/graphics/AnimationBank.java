package graphics;

import gameStates.GameState;
import gui.GameInstance;
import gui.GamePanel;

import java.awt.Graphics;
import java.awt.Image;

public class AnimationBank 
{
	
	public static Animation bananaToss = new Animation(new AnimationHandler(){
		
		int x = 0;
		int y = 200;
		int xOrigin;
		GameState toPlayOver;
		GameInstance gi;
		Image banana;
		@Override
		public void play(GameState toPlayOver, GameInstance gi, int xOrigin, int yOrigin) 
		{
			this.xOrigin = xOrigin;
			this.gi = gi;
			banana = ImageManager.getImage(ImageManager.BANANA);
			gi.setGameState(this);
		
			this.toPlayOver = toPlayOver;
		}

		@Override
		public void draw(Graphics g, GamePanel parent) 
		{
			toPlayOver.draw(g, parent);
			int xT = (xOrigin * parent.getWidth())/10;
			g.drawImage(banana,xT+x,parent.getHeight()-y, parent.getWidth()/20,parent.getHeight()/20, null);
		}

		@Override
		public void tick() 
		{
			x+=4;
			if(x > 500)
			{
				this.finish(toPlayOver, gi);
			}
		}});
}
