package com.github.achaaab.tetroshow.scene;

import com.github.achaaab.tetroshow.settings.Settings;
import com.github.achaaab.tetroshow.view.menu.ControlsView;

import java.awt.Container;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * controls scene
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class ControlsScene extends AbstractScene {

	private final ControlsView view;

	/**
	 * Creates a new controls scene.
	 *
	 * @param manager scene manager
	 * @param parent parent scene
	 * @since 0.0.0
	 */
	public ControlsScene(SceneManager manager, Scene parent) {

		super(manager, parent);

		view = new ControlsView();
		view.onBack(this::back);
	}

	@Override
	public void initialize() {

		view.requestFocus();
		view.selectFirstInput();
	}

	@Override
	public void update(double deltaTime) {
		invokeLater(view::repaint);
	}

	/**
	 * @since 0.0.0
	 */
	private void back() {

		Settings.getDefaultInstance().save();
		exit();
	}

	@Override
	public Container getView() {
		return view;
	}
}
