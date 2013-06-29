import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JFileChooser;
import java.io.*;

public class Menu extends JPanel
{
    int x              = Main.window_x;
    int y              = Main.window_y;
    JPanel contain     = new JPanel();
    Button automaton   = new Button("Automaton");
    Button list         = new Button("List");
    Button open        = new Button("Open");
    
    Menu(String titre)
    {
	setSize(x, y);
	contain.setLayout(new GridLayout(1, 3, 10, 0 ));
	contain.add(automaton);
	contain.add(list);
	contain.add(open);
        add(contain);
	setVisible(true);
    }
    
    class Button extends JButton implements MouseListener
    {
	public Button(String s)
	{
	    super(s);
	    JLabel text = new JLabel(s);
	    //texte.setFont(new Font("Verdana", Font.PLAIN, 8));                                          
	    //texte.setForeground(new Color(30,30,60));                                                   
	    JPanel textpan = new JPanel();
	    textpan.add(text);
	    textpan.setOpaque(false);
	    add(textpan);
	    this.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e)
	{
	    if(e.getSource() == automaton)
		{
		    System.out.println("automaton");
		    Main.getInstance().getWin().getContentPane().add(new Automaton());
		    
		}
	    if(e.getSource() == list)
		{
		    Main.getInstance().getWin().setContentPane(new Automatika(1));
		}
	    if(e.getSource() == open)
		{
		    try{
			JFileChooser dialogue = new JFileChooser(".");
			PrintWriter sortie;
			File fichier;
			if (dialogue.showOpenDialog(null) ==  JFileChooser.APPROVE_OPTION) {
			    fichier = dialogue.getSelectedFile();
			    sortie = new PrintWriter(new FileWriter(fichier.getPath(), true));
			    //sortie.println("a");
			    sortie.close();
			}
			Main.getInstance().getWin().setContentPane(new Automatika(2));
		    }
		    catch(IOException ex){
			ex.printStackTrace();
		    }
		}
	}
	
	public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
    }
}
