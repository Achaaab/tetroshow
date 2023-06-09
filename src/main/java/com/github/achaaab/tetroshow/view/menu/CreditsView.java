package com.github.achaaab.tetroshow.view.menu;

import com.github.achaaab.tetroshow.view.play.TetroshowView;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import static com.github.achaaab.tetroshow.utility.ResourceUtility.loadFont;
import static com.github.achaaab.tetroshow.utility.SwingUtility.hideCursor;
import static com.github.achaaab.tetroshow.utility.SwingUtility.scaleFloat;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.round;
import static java.lang.Math.toIntExact;
import static javax.swing.SwingUtilities.invokeLater;

/**
 * credits view
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class CreditsView extends JComponent {

	private static final String FONT_RESOURCE_NAME = "font/monofonto_regular.otf";
	private static final Font FONT = loadFont(FONT_RESOURCE_NAME).deriveFont(scaleFloat(14.0f));
	private static final Color BACKGROUND = new Color(0, 0, 16);
	private static final Color FOREGROUND = new Color(192, 192, 192);

	/**
	 * credits scrolling speed in lines of text per second
	 */
	private static final double SCROLLING_SPEED = 1.5;

	private final List<String> lines;
	private double time;

	/**
	 * @param lines lines of credits
	 * @since 0.0.0
	 */
	public CreditsView(List<String> lines) {

		this.lines = lines;

		setPreferredSize(TetroshowView.DIMENSION);
		hideCursor(this);
	}

	/**
	 * Displays this credits view at given time. Lines should go up while time increases.
	 *
	 * @param time credits scene time in seconds
	 * @since 0.0.0
	 */
	public void display(double time) {

		this.time = time;

		invokeLater(this::repaint);
	}

	@Override
	protected void paintComponent(Graphics graphics) {

		var width = getWidth();
		var height = getHeight();
		graphics.setColor(BACKGROUND);
		graphics.fillRect(0, 0, width, height);

		graphics.setFont(FONT);
		graphics.setColor(FOREGROUND);
		var fontMetrics = graphics.getFontMetrics();
		double lineHeight = fontMetrics.getHeight();

		var lineCount = lines.size();

		var scrolledLineCount = time * SCROLLING_SPEED;
		var minLineIndex = toIntExact(round(scrolledLineCount - height / lineHeight - 2));
		var maxLineIndex = toIntExact(round(scrolledLineCount));

		minLineIndex = min(max(minLineIndex, 0), lineCount - 1);
		maxLineIndex = min(max(maxLineIndex, 0), lineCount - 1);

		for (var lineIndex = minLineIndex; lineIndex <= maxLineIndex; lineIndex++) {

			var line = lines.get(lineIndex);
			var lineWidth = fontMetrics.stringWidth(line);

			var x = (width - lineWidth) / 2;
			var y = height + lineHeight * (1 + lineIndex - scrolledLineCount);

			graphics.drawString(line, x, toIntExact(round(y)));
		}
	}
}
