package com.github.achaaab.tetroshow.audio;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface Audio {

	/**
	 * Plays this sound asynchronously.
	 *
	 * @since 0.0.0
	 */
	void play();

	/**
	 * Plays this sound synchronously.
	 *
	 * @since 0.0.0
	 */
	void playAndWait();
}