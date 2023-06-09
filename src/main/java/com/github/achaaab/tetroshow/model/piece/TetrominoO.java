package com.github.achaaab.tetroshow.model.piece;

import java.awt.Color;
import java.util.List;

/**
 * O-shape tetromino
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TetrominoO extends Tetromino {

	private static final Color COLOR = new Color(254, 203, 0);

	private static final int[][] BLOCK_POSITIONS = {
			{ 1, 2, 5, 6 },
			{ 1, 2, 5, 6 },
			{ 1, 2, 5, 6 },
			{ 1, 2, 5, 6 } };

	private static final int ENTRY_COLUMN = 3;
	private static final char LETTER = 'o';

	private static final List<List<Direction>> CLOCKWISE_WALL_KICKS = getClockwiseWallKicks(LETTER);
	private static final List<List<Direction>> COUNTERCLOCKWISE_WALL_KICKS = getCounterclockwiseWallKicks(LETTER);

	/**
	 * Creates a new O-shape tetromino.
	 *
	 * @since 0.0.0
	 */
	public TetrominoO() {

		super(getRotations(COLOR, BLOCK_POSITIONS),
				ENTRY_COLUMN, CLOCKWISE_WALL_KICKS, COUNTERCLOCKWISE_WALL_KICKS);
	}
}
