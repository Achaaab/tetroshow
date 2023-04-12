package com.github.achaaab.tetroshow.action;

import com.github.achaaab.tetroshow.model.Tetroshow;
import org.slf4j.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import static com.github.achaaab.tetroshow.model.piece.Direction.CLOCKWISE;
import static com.github.achaaab.tetroshow.model.piece.Direction.COUNTERCLOCKWISE;
import static com.github.achaaab.tetroshow.model.piece.Direction.DOWN;
import static com.github.achaaab.tetroshow.model.piece.Direction.LEFT;
import static com.github.achaaab.tetroshow.model.piece.Direction.RIGHT;
import static java.util.Arrays.fill;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Keyboard extends AbstractAction implements KeyListener {

	private static final Logger LOGGER = getLogger(Keyboard.class);

	private static final int KEY_COUNT = 1024;

	private final Move rotateClockwise;
	private final Move rotateCounterclockwise;
	private final Move shiftLeft;
	private final Move shiftRight;
	private final Move softDrop;
	private final HardDrop hardDrop;
	private final Hold hold;
	private final Pause pause;
	private final Exit exit;

	private final boolean[] pressedKeys;
	private final int[] frame;
	private final Map<AbstractAction, Integer> keyMapping;

	private int frameCounter;

	/**
	 * @param tetroshow
	 * @since 0.0.0
	 */
	public Keyboard(Tetroshow tetroshow) {

		super(tetroshow);

		pressedKeys = new boolean[KEY_COUNT];
		frame = new int[KEY_COUNT];
		keyMapping = new HashMap<>();

		rotateClockwise = new Move(tetroshow, CLOCKWISE, 1);
		rotateCounterclockwise = new Move(tetroshow, COUNTERCLOCKWISE, 1);
		shiftLeft = new Move(tetroshow, LEFT, 1);
		shiftRight = new Move(tetroshow, RIGHT, 1);
		softDrop = new Move(tetroshow, DOWN, 1);
		hardDrop = new HardDrop(tetroshow);
		hold = new Hold(tetroshow);
		pause = new Pause(tetroshow);
		exit = new Exit(tetroshow);

		var clockwiseKey = configuration.getClockwiseKey();
		var counterclockwiseKey = configuration.getCounterclockwiseKey();
		var leftKey = configuration.getLeftKey();
		var rightKey = configuration.getRightKey();
		var softDropKey = configuration.getSoftDropKey();
		var hardDropKey = configuration.getHardDropKey();
		var holdKey = configuration.getHoldKey();
		var pauseKey = configuration.getPauseKey();
		var exitKey = configuration.getExitKey();

		keyMapping.put(rotateClockwise, clockwiseKey);
		keyMapping.put(rotateCounterclockwise, counterclockwiseKey);
		keyMapping.put(shiftLeft, leftKey);
		keyMapping.put(shiftRight, rightKey);
		keyMapping.put(softDrop, softDropKey);
		keyMapping.put(hardDrop, hardDropKey);
		keyMapping.put(hold, holdKey);
		keyMapping.put(pause, pauseKey);
		keyMapping.put(exit, exitKey);

		reset();
	}

	@Override
	public void reset() {

		fill(pressedKeys, false);
		fill(frame, -1);
		frameCounter = 0;
	}

	/**
	 * @param key code de la touche à tester
	 * @param repeatable indique que la touche est répétable passé un certain délai
	 * @return {@code true} si la touche est effective ou {@code false} sinon
	 * @since 0.0.0
	 */
	private boolean isEffective(int key, boolean repeatable) {

		boolean effective;

		var duration = frameCounter - frame[key];

		if (duration == 0) {

			effective = true;

		} else if (repeatable) {

			var keyPressed = pressedKeys[key];
			var levelSpeed = tetroshow.getLevelSpeed();
			var delayAuto = configuration.getAutoRepeatDelay(levelSpeed);
			var autoRepeat = duration >= delayAuto;
			effective = keyPressed && autoRepeat;

		} else {

			effective = false;
		}

		return effective;
	}

	/**
	 * Executes this keyboard while paused.
	 *
	 * @since 0.0.0
	 */
	public void executePaused() {

		execute(pause, false);
		execute(exit, false);

		frameCounter++;
	}

	@Override
	public void execute() {

		if (!execute(rotateClockwise, false)) {
			execute(rotateCounterclockwise, false);
		}

		var shiftRightDone = execute(shiftRight, false);
		var shiftLeftDone = !shiftRightDone && execute(shiftLeft, false);

		if (!shiftRightDone && !shiftLeftDone) {

			if (!execute(shiftRight, true)) {
				execute(shiftLeft, true);
			}
		}

		execute(softDrop, true);
		execute(hardDrop, false);
		execute(hold, false);
		execute(pause, false);
		execute(exit, false);

		frameCounter++;
	}

	/**
	 * @param action action à exécuter
	 * @param repeatable indique si l'action est répétable lorsque la touche est maintenue enfoncée
	 * @return {@code true} si l'action a été exécutée, {@code false} sinon
	 * @since 0.0.0
	 */
	private boolean execute(AbstractAction action, boolean repeatable) {

		var key = keyMapping.get(action);
		var effective = isEffective(key, repeatable);

		if (effective) {
			action.execute();
		}

		return effective;
	}

	@Override
	public void keyPressed(KeyEvent event) {

		var keyCode = event.getKeyCode();

		LOGGER.debug("key pressed: {}", keyCode);

		if (!pressedKeys[keyCode]) {

			pressedKeys[keyCode] = true;
			frame[keyCode] = frameCounter;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {

		var keyCode = event.getKeyCode();

		LOGGER.debug("key released: {}", keyCode);

		pressedKeys[keyCode] = false;
	}

	@Override
	public void keyTyped(KeyEvent event) {
		event.consume();
	}
}