package org.dcu.admin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

import org.dcu.util.Language;
import org.dcu.util.TimeStamp;

/**
 * Easier printing to the console as well as supports colors.
 * @author Stephen Andrews
 */
public class ConsoleMessage {

	//TODO: Seems that if you set one highlight color it won't change back.
	
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
	public static void success(String message) {
		AdminPanel.getConsole().append(Language.NEW_LINE + TimeStamp.add() + message);
		String timeStamp = TimeStamp.add();
		int startingIndex = AdminPanel.getConsole().getText().indexOf(timeStamp + message);
		int endingIndex = startingIndex + (timeStamp.length() + message.length());
		System.out.println("Starting: " + startingIndex + " Ending: " + endingIndex);
		try {
			AdminPanel.getConsole().getHighlighter().addHighlight(startingIndex, endingIndex, greenPainter);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a red highlighted line of text to the console.
	 * @param message The message to send.
	 */
	public static void error(String message) {
		String timeStamp = TimeStamp.add();
		AdminPanel.getConsole().append(Language.NEW_LINE + timeStamp + message);
		int startingIndex = AdminPanel.getConsole().getText().indexOf(timeStamp + message);
		int endingIndex = startingIndex + (timeStamp.length() + message.length());
		try {
			AdminPanel.getConsole().getHighlighter().addHighlight(startingIndex, endingIndex, redPainter);
			AdminPanel.getConsole().repaint();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a orange highlighted line of text to the console.
	 * @param message The message to send.
	 */
	public static void warning(String message) {
		String timeStamp = TimeStamp.add();
		AdminPanel.getConsole().append(Language.NEW_LINE + timeStamp + message);
		int startingIndex = AdminPanel.getConsole().getText().indexOf(timeStamp + message);
		int endingIndex = startingIndex + (timeStamp.length() + message.length());
		System.out.println("Starting: " + startingIndex + " Ending: " + endingIndex);
		try {
			AdminPanel.getConsole().getHighlighter().addHighlight(startingIndex, endingIndex, orangePainter);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
