package org.dcu.admin;

import java.awt.Color;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import org.dcu.util.Language;
import org.dcu.util.TimeStamp;

/**
 * Easier printing to the console as well as supports colors.
 * @author Stephen Andrews
 */
public class ConsoleMessage {

	/**
	 * A green highlight color.
	 */
	private static Highlighter.HighlightPainter greenPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
	
	/**
	 * A red highlight color.
	 */
    private static Highlighter.HighlightPainter redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
    
    /**
	 * A orange highlight color.
	 */
    private static Highlighter.HighlightPainter orangePainter = new DefaultHighlighter.DefaultHighlightPainter(Color.ORANGE);
    
	/**
	 * Sends a regular line of text to the console.
	 * @param message The message to send.
	 */
	public static void info(String message) {
		AdminPanel.getConsole().append(Language.NEW_LINE + TimeStamp.add() + message);
	}
	
	/**
	 * Sends a green highlighted line of text to the console.
	 * @param message The message to send.
	 */
	public static void success(String message) { //This method doesn't work
		AdminPanel.getConsole().append(Language.NEW_LINE + TimeStamp.add() + message);
		try {
			AdminPanel.getConsole().getHighlighter().addHighlight(message.indexOf(AdminPanel.getConsole().getText()), 
					message.lastIndexOf(AdminPanel.getConsole().getText()), greenPainter);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
