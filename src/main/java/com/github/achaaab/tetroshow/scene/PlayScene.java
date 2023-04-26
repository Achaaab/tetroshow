package com.github.achaaab.tetroshow.scene;

import com.github.achaaab.tetroshow.model.Tetroshow;
import com.github.achaaab.tetroshow.view.play.TetroshowView;

import java.awt.Container;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * play scene
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PlayScene extends AbstractScene {

	private final Tetroshow tetroshow;
	private final TetroshowView view;

	/**
	 * Creates a play scene.
	 *
	 * @param manager scene manager
	 * @param parent parent scene
	 * @since 0.0.0
	 */
	public PlayScene(SceneManager manager, Scene parent) {

		super(manager, parent);

		tetroshow = new Tetroshow();
		view = new TetroshowView(tetroshow);
		tetroshow.setExitListener(this::exit);

		var keyboard = tetroshow.getKeyboard();
		view.addKeyListener(keyboard);
	}

	@Override
	public void initialize() {

		tetroshow.reset();
		view.requestFocus();
		tetroshow.newPiece();
	}

	@Override
	public void update(double deltaTime) {

		tetroshow.update(deltaTime);

		invokeLater(view::repaint);
	}

	@Override
	public Container getView() {
		return view;
	}
}
