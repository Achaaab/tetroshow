package com.github.achaaab.tetroshow.model.piece;

import com.github.achaaab.tetroshow.audio.Audio;

import java.awt.Color;
import java.util.List;

import static com.github.achaaab.tetroshow.audio.AudioFactory.createAudio;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TetrominoJ extends Tetromino {

	private static final Color COLOR = new Color(0, 101, 189);
	private static final Audio SOUND_EFFECT = createAudio("audio/effect/tetromino_j.wav");

	private static final int[][] BLOCK_POSITIONS = {
			{ 0, 4, 5, 6 },
			{ 1, 2, 5, 9 },
			{ 4, 5, 6, 10 },
			{ 1, 5, 8, 9 } };

	private static final int ENTRY_COLUMN = 3;
	private static final char LETTER = 'j';

	private static final List<List<Direction>> CLOCKWISE_WALL_KICKS = getClockwiseWallKicks(LETTER);
	private static final List<List<Direction>> COUNTERCLOCKWISE_WALL_KICKS = getCounterclockwiseWallKicks(LETTER);

	/**
	 * @since 0.0.0
	 */
	public TetrominoJ() {

		super(getRotations(COLOR, BLOCK_POSITIONS),
				ENTRY_COLUMN, SOUND_EFFECT, CLOCKWISE_WALL_KICKS, COUNTERCLOCKWISE_WALL_KICKS);
	}
}