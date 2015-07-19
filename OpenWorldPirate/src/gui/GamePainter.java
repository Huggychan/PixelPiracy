package gui;

public class GamePainter
{
	static GameWindow p;
	public static void setP(GameWindow gw)
	{
		p = gw;
	}
	public static void repaint()
	{
		p.getPanel().repaint();
	}
}
