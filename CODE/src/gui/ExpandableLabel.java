package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExpandableLabel extends JLabel {
	private boolean firstTimeExpand = true;
	private boolean expanded = false;
	private String label;

	public ExpandableLabel() {
		super();
		label = "Label";
	}

	public ExpandableLabel(String label) {
		this();
		this.label = label;
		setText("\u25BA " + label);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				expand();
			}
		});
	}

	public boolean isExpanded() {
		return expanded || firstTimeExpand;
	}

	private void expand() {
		firstTimeExpand = false;
		if (!isExpanded()) {
			setText("\u25BC " + this.label);
			expanded = true;
		} else {
			setText("\u25BA " + this.label);
			expanded = false;
		}

	}

	public void expand(JPanel panel, JComponent[] components, int length) {
		panel.setBounds(panel.getBounds().x,
				panel.getBounds().y,
				panel.getBounds().width,
				panel.getBounds().height + length);
		for (JComponent comp : components) {
			comp.setBounds(comp.getBounds().x, comp.getBounds().y + length,
					comp.getBounds().width, comp.getBounds().height);
		}

	}

}
