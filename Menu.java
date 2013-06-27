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

public class Menu extends JPanel
{
    int x              = Automatika.fenetre_x;
    int y              = Automatika.fenetre_y;
    JPanel conteneur   = new JPanel();
    Bouton automate    = new Bouton("Automate");
    Bouton liste       = new Bouton("Liste");
    Bouton ouvrir      = new Bouton("Ouvrir");

    Menu(String titre)
    {
	setSize(x, y);
	conteneur.setLayout(new GridLayout(3, 1, 10, 15));
	conteneur.add(automate);
	conteneur.add(liste);
	conteneur.add(ouvrir);
        add(conteneur);
	setVisible(true);
    }
    
    class Bouton extends JButton implements MouseListener
    {
	public Bouton(String s)
	{
	    super(s);
	    JLabel texte = new JLabel(s);
	    //texte.setFont(new Font("Verdana", Font.PLAIN, 8));                                          
	    //texte.setForeground(new Color(30,30,60));                                                   
	    JPanel textepan = new JPanel();
	    textepan.add(texte);
	    textepan.setOpaque(false);
	    add(textepan);
	    this.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e)
        {
	    
        }
	public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
    }
}
