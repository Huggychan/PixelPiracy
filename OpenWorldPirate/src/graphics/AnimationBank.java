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
		//int y;
		int xOrigin;
		GameState toPlayOver;
		GameInstance gi;
		Image banana;
		@Override
		public void play(GameState toPlayOver, GameInstance gi, int xOrigin, int yOrigin) 
		{
			this.x = 0;
			//this.y = yOrigin;
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
			if(this.x > parent.getWidth()/2)
			{
				this.finish(toPlayOver, gi);
			}
			g.drawImage(banana,xT+x,parent.getHeight()*3/5, parent.getWidth()/20,parent.getHeight()/20, null);
		}

		@Override
		public void tick() 
		{
			x+=22;

		}});
}
