package com.github.achaaab.tetroshow.action;

import com.github.achaaab.tetroshow.audio.Audio;
import com.github.achaaab.tetroshow.model.Tetroshow;
import com.github.achaaab.tetroshow.model.piece.Piece;

import static com.github.achaaab.tetroshow.audio.AudioFactory.getAudio;
import static com.github.achaaab.tetroshow.audio.AudioPlayer.SOUND_EFFECT;
import static com.github.achaaab.tetroshow.model.piece.Direction.DOWN;

/**
 * Hard drops the current falling piece, if any.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class HardDrop extends AbstractAction {

	private final Audio soundEffect;

	/**
	 * Creates a new hard drop action.
	 *
	 * @param tetroshow Tetroshow on which to apply hard drop
	 * @since 0.0.0
	 */
	public HardDrop(Tetroshow tetroshow) {
		this(tetroshow, getAudio("audio/effect/hard_drop.wav"));
	}

	/**
	 * Creates a new hard drop action with a specified sound effect.
	 *
	 * @param tetroshow Tetroshow on which to apply hard drop
	 * @param soundEffect sound effect to play when executed
	 * @since 0.0.0
	 */
	public HardDrop(Tetroshow tetroshow, Audio soundEffect) {

		super(tetroshow);

		this.soundEffect = soundEffect;
	}

	@Override
	public void execute() {
		tetroshow.getFallingPiece().ifPresent(this::apply);
	}

	/**
	 * Instantly (between 2 frames) drops and locks the given piece.
	 *
	 * @param piece piece to drop
	 * @since 0.0.0
	 */
	private void apply(Piece piece) {

		while (playfield.isMovePossible(piece, DOWN)) {

			piece.move(DOWN);
			tetroshow.increaseDropBonus();
		}

		if (tetroshow.lockFallingPiece()) {
			SOUND_EFFECT.play(soundEffect, false);
		} else {
			tetroshow.exit();
		}
	}
}
