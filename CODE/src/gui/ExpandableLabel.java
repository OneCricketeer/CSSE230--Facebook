package gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExpandableLabel extends JLabel {
	private boolean expanded = false;
	private final String label;

	public ExpandableLabel() {
		this("Label");
	}

	public ExpandableLabel(String label) {
		super();
		this.label = label;
		setText("\u25BA " + label);
		setForeground(new Color(250, 250, 250));
		setOpaque(true);
		setBackground(new Color(178, 34, 34));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				expand();
			}
		});
	}

	public boolean isExpanded() {
		return expanded;
	}

	private void expand() {
		if (!isExpanded()) {
			setText("\u25BC " + this.label);
		} else {
			setText("\u25BA " + this.label);
		}
		expanded = !expanded;

	}

	public void expand(JPanel panel, JComponent[] components, int length) {
		panel.setBounds(panel.getBounds().x, panel.getBounds().y,
				panel.getBounds().width, panel.getBounds().height + length);
		for (JComponent comp : components) {
			comp.setBounds(comp.getBounds().x, comp.getBounds().y + length,
					comp.getBounds().width, comp.getBounds().height);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (!isOpaque()) {
			super.paintComponent(g);
			return;
		}
		int w = getWidth();
		int h = getHeight();

		// Paint a gradient from top to bottom
		GradientPaint gp = new GradientPaint(0, h, new Color(150, 0, 0), 0, 0,
				new Color(234, 20, 20));

		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);

		setOpaque(false);
		super.paintComponent(g);
		setOpaque(true);
	}

}
