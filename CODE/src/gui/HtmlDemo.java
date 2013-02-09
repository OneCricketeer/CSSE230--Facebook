package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 * A complete Java class that demonstrates how to create an HTML viewer with
 * styles, using the JEditorPane, HTMLEditorKit, StyleSheet, and JFrame.
 * 
 * @author alvin alexander, devdaily.com.
 * 
 */
public class HtmlDemo {
	public static void main(String[] args) {
		new HtmlDemo();
	}

	public HtmlDemo() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// create jeditorpane
				JEditorPane jEditorPane = new JEditorPane();

				// make it read-only
				jEditorPane.setEditable(false);

				// create a scrollpane; modify its attributes as desired
				JScrollPane scrollPane = new JScrollPane(jEditorPane);

				// add an html editor kit
				HTMLEditorKit kit = new HTMLEditorKit();
				jEditorPane.setEditorKit(kit);

				// add some styles to the html
				StyleSheet styleSheet = kit.getStyleSheet();
				styleSheet
						.addRule("body {margin: 20px auto; color:#000; font-family:times; margin: 4px; }");
				styleSheet.addRule("h1 {color: blue;}");
				styleSheet.addRule("h2 {color: #ff0000;}");
				styleSheet
						.addRule("pre {font : 12px Verdana,Arial, Helvetica, sans-serif; color : black; background-color : #fafafa; }");
				styleSheet.addRule(".heading { margin: 1px; color: #ffffff;"
						+ "padding: 3px 10px; cursor: pointer;"
						+ "position: relative; background-color: #c11b17; }");
				styleSheet.addRule(".content { padding: 5px 10px;"
						+ "background-color:#fafafa; }");
				styleSheet.addRule("p { padding: 5px 0; }");

				// create some simple html as a string
				String htmlString = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
						+ "<head>\n"
						// +
						// "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
						+ "<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js\"></script>\n"
						+ "<script type=\"text/javascript\">\n"
						+ "jQuery(document).ready(function() {\n"
						+ "jQuery(\".content\").hide();\n"
						+ "jQuery(\".heading\").click(function() {\n"
						+ "jQuery(this).next(\".content\").slideToggle(500); });\n"
						+ "});\n"
						+ "</script>\n"
						+ "</head>\n"
						+ "<body>\n"
						+ "<div align=\"center\">\n"
						+ "<p>Click on the each header to expand and collapse the pane</p>\n"
						+ "</div>\n"						
						+ "<div class=\"layer1\">\n"
						+ "<p class=\"heading\">Header-1 </p>\n"
						+ "<div class=\"content\">\n"
						+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit orem ipsum dolor sit amet, consectetuer adipiscing elit<br />"
						+ "Lorem ipsum dolor sit amet, consectetuer adipiscing elit orem ipsum dolor sit amet, consectetuer adipiscing elit\n"
						+ "</div>\n" + "</body>\n";

				// create a document, set it on the jeditorpane, then add the
				// html
				Document doc = kit.createDefaultDocument();
				jEditorPane.setDocument(doc);
				jEditorPane.setText(htmlString);

				// now add it all to a frame
				JFrame j = new JFrame("HtmlEditorKit Test");
				j.getContentPane().add(scrollPane, BorderLayout.CENTER);

				// make it easy to close the application
				j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// display the frame
				j.setSize(new Dimension(300, 200));

				// pack it, if you prefer
				j.pack();

				// center the jframe, then make it visible
				j.setLocationRelativeTo(null);
				j.setVisible(true);
			}
		});
	}
}