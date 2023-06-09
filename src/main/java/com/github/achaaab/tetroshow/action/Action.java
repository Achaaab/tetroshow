package com.github.achaaab.tetroshow.action;

import com.github.achaaab.tetroshow.model.GamePart;

/**
 * An action, from the user or from the game engine, having an influence on the game state.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface Action extends GamePart {

	/**
	 * Executes this action.
	 *
	 * @since 0.0.0
	 */
	void execute();
}
