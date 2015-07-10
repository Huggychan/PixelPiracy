package playerInteraction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.GameInstance;

public class GameKeyListener implements KeyListener
{
	GameInstance gi;
	public GameKeyListener(GameInstance gi) 
	{
		this.gi = gi;
	}
	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		switch(arg0.getKeyCode())
		{
		case KeyEvent.VK_UP:
			gi.getCurrentGameState().setUp(true);
			break;
		case KeyEvent.VK_DOWN:
			gi.getCurrentGameState().setDn(true);
			break;
		case KeyEvent.VK_LEFT:
			gi.getCurrentGameState().setLt(true);
			break;
		case KeyEvent.VK_RIGHT:
			gi.getCurrentGameState().setRt(true);
			break;
		case KeyEvent.VK_SPACE:
			gi.getCurrentGameState().setSpace(true);
			break;
		case KeyEvent.VK_ENTER:
			gi.getCurrentGameState().setSpace(true);
			break;
		case KeyEvent.VK_ESCAPE:
			gi.getCurrentGameState().setEscape(true);
			break;
		case KeyEvent.VK_1:
			gi.getCurrentGameState().setNumber(0, true);
			break;
		case KeyEvent.VK_2:
			gi.getCurrentGameState().setNumber(1, true);
			break;
		case KeyEvent.VK_3:
			gi.getCurrentGameState().setNumber(2, true);
			break;
		case KeyEvent.VK_4:
			gi.getCurrentGameState().setNumber(3, true);
			break;
		case KeyEvent.VK_5:
			gi.getCurrentGameState().setNumber(4, true);
			break;
		case KeyEvent.VK_6:
			gi.getCurrentGameState().setNumber(5, true);
			break;
		case KeyEvent.VK_7:
			gi.getCurrentGameState().setNumber(6, true);
			break;
		case KeyEvent.VK_8:
			gi.getCurrentGameState().setNumber(7, true);
			break;
		case KeyEvent.VK_9:
			gi.getCurrentGameState().setNumber(8, true);
			break;
			
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		switch(arg0.getKeyCode())
		{
		case KeyEvent.VK_UP:
			gi.getCurrentGameState().setUp(false);
			break;
		case KeyEvent.VK_DOWN:
			gi.getCurrentGameState().setDn(false);
			break;
		case KeyEvent.VK_LEFT:
			gi.getCurrentGameState().setLt(false);
			break;
		case KeyEvent.VK_RIGHT:
			gi.getCurrentGameState().setRt(false);
			break;
		case KeyEvent.VK_SPACE:
			gi.getCurrentGameState().setSpace(false);
			break;
		case KeyEvent.VK_ENTER:
			gi.getCurrentGameState().setSpace(false);
			break;
		case KeyEvent.VK_ESCAPE:
			gi.getCurrentGameState().setEscape(false);
			break;
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
