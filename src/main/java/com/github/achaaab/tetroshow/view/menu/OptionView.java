package com.github.achaaab.tetroshow.view.menu;

import com.github.achaaab.tetroshow.view.message.Language;
import com.github.achaaab.tetroshow.view.message.Messages;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import static com.github.achaaab.tetroshow.view.Scaler.scale;
import static com.github.achaaab.tetroshow.view.message.Language.getLanguage;
import static com.github.achaaab.tetroshow.view.message.Messages.BACK;
import static com.github.achaaab.tetroshow.view.message.Messages.LANGUAGE;
import static com.github.achaaab.tetroshow.view.message.Messages.getMessage;
import static java.awt.Font.DIALOG;
import static java.awt.Font.PLAIN;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class OptionView extends JPanel {

	private static final int FONT_SIZE = 20;
	private static final Font FONT = new Font(DIALOG, PLAIN, scale(FONT_SIZE));

	private final JLabel languageLabel;
	private final JComboBox<Language> languages;
	private final JButton back;

	/**
	 * @since 0.0.0
	 */
	public OptionView() {

		Messages.register(this::localeChanged);

		languageLabel = new JLabel(getMessage(LANGUAGE));
		languages = new JComboBox<>(Language.values());
		back = new JButton(getMessage(BACK));

		languageLabel.setFont(FONT);
		languages.setFont(FONT);
		back.setFont(FONT);

		var currentLocale = Messages.getLocale();
		var code = currentLocale.getLanguage();
		languages.setSelectedItem(getLanguage(code));

		add(languageLabel);
		add(languages);
		add(back);
	}

	/**
	 * @since 0.0.0
	 */
	private void localeChanged() {

		languageLabel.setText(getMessage(LANGUAGE));
		back.setText(getMessage(BACK));
	}

	/**
	 * @param itemListener
	 * @since 0.0.0
	 */
	public void onLanguageChanged(ItemListener itemListener) {
		languages.addItemListener(itemListener);
	}

	/**
	 * @param actionListener
	 * @since 0.0.0
	 */
	public void onBack(ActionListener actionListener) {
		back.addActionListener(actionListener);
	}
}
