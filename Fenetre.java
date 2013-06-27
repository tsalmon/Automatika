import javax.swing.*;
import java.awt.*;

//ce fichier contient la fenetre du programme                                                             
public class Fenetre extends JFrame
{
    int taille_x = Automatika.fenetre_x ;
    int taille_y = Automatika.fenetre_y ;

    Fenetre()
    {
        setTitle("Automatika");
        setSize(taille_x, taille_y);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}









