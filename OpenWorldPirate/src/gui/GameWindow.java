package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import gui.GameInstance;

import javax.swing.JFrame;

public class GameWindow extends JFrame
{
	GameInstance gi;
	GamePanel gp;
public GameWindow(GameInstance gi)
{
	super();
	this.gi = gi;
	this.setUndecorated(true);
	Dimension d =Toolkit.getDefaultToolkit().getScreenSize();
	this.setSize(d);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	gp = new GamePanel(gi, d);
	this.add(gp);
	this.setVisible(true);
}

public GamePanel getPanel() {
	return gp;
}

}
