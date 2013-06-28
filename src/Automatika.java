import java.util.Scanner;
import java.awt.Color;
import java.util.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Automatika
{

    /* variable générales d'affichage */
    static  volatile Automatika instance  = null;
    static           int        fenetre_x = 779;
    static           int        fenetre_y = 456;
    private          Fenetre f;
    
    //permet d'utiliser les variables et les fonctions de la classe Automatika 
    public final static Automatika getInstance()
    {
        if(Automatika.instance == null){
            synchronized(Automatika.class){
                if(Automatika.instance == null){
                    Automatika.instance = new Automatika();
                }
            }
        }
        return (Automatika.instance);
    }
    
    private Automatika()
    {
        super();
    }
    
    public Fenetre getFen()
    {
        return (this.f);
    }

    public void setFen(Fenetre newf)
    {
        this.f = newf;
    }


    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    try{
                        for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels())
                            {
                                if("Nimbus".equals(info.getName())){
                                    UIManager.setLookAndFeel(info.getClassName());
                                    break;
                                }
                            }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    Automatika.getInstance().setFen(new Fenetre());
                    Automatika.getInstance().getFen().setContentPane(new Menu("Automatika - Menu"));
                }
            });
    }    
}