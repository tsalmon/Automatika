import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.util.*;
import javax.imageio.*; 
public class Save extends JPanel
    implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
 
    public Save(BufferedImage draw_surface) {
        super(new BorderLayout());
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        fc = new JFileChooser();
        saveButton = 
	    new JButton("Save a File...", createImageIcon("images/Save16.gif"));
        saveButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
 
    public void actionPerformed(ActionEvent e) { 
	if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                log.append("Saving: " + file.getName() + "." + newline);
		try {
		    int width = 200, height = 200;

		    // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
		    // into integer pixels
		    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		    Graphics2D ig2 = bi.createGraphics();


		    Font font = new Font("TimesRoman", Font.BOLD, 20);
		    ig2.setFont(font);
		    String message = "www.java2s.com!";
		    FontMetrics fontMetrics = ig2.getFontMetrics();
		    int stringWidth = fontMetrics.stringWidth(message);
		    int stringHeight = fontMetrics.getAscent();
		    ig2.setPaint(Color.black);
		    ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

		    ImageIO.write(bi, "png", new File(file.getName() + ".png"));
		    //ImageIO.write(bi, "JPEG", new File(file.getName() + ".jpg"));
		    //ImageIO.write(bi, "gif", new File("c:\\yourImageName.GIF"));
		    //ImageIO.write(bi, "BMP", new File("c:\\yourImageName.BMP"));
      
		} catch (IOException ie) {
		    ie.printStackTrace();
		}

            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }
 
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Save.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new Save(null));
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    //Turn off metal's use of bold fonts
		    UIManager.put("swing.boldMetal", Boolean.FALSE); 
		    createAndShowGUI();
		}
	    });
    }
}