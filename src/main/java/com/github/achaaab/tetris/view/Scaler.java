package com.github.achaaab.tetris.view;

import static java.awt.Toolkit.getDefaultToolkit;
import static java.lang.Math.round;

/**
 * basic DPI-aware scaler for Swing UI
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Scaler {

	private static final float BASE_RESOLUTION = 72.0f;
	private static final int RESOLUTION = getDefaultToolkit().getScreenResolution();

	/**
	 * @param size normalized size for 72 DPI resolution
	 * @return scaled size
	 * @since 0.0.0
	 */
	public static int scale(float size) {
		return round(size * RESOLUTION / BASE_RESOLUTION);
	}
}
