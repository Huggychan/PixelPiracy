package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FontManager 
{
	private static final Font SERIF_FONT = new Font("serif", Font.PLAIN, 24);
	static ArrayList<Font> existingFonts = new ArrayList<Font>();
	public static Font pirateFont;
	public void loadFonts()
	{
		String fName = this.getClass().getResource("/KING OF PIRATE.ttf").getPath();
		pirateFont = FontManager.getFont(fName);
	}
	private static Font getFont(String name) 
	{
	    Font font = null;
	    if (name == null) 
	    {
	        return SERIF_FONT;
	    }

	        File fontFile = new File(name);
	        try {
				font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
				System.out.println("This Worked");
			} catch (FontFormatException | IOException e) {
				e.printStackTrace();
				return SERIF_FONT;
			}
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(font);
	    return font;
	}
}
