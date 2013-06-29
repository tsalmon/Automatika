import java.util.Scanner;
import java.awt.Color;
import java.util.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main
{
    
    /* variable générales d'affichage */
    static  volatile Main instance  = null;
    static           int        window_x = 779;
    static           int        window_y = 456;
    private          Windows f;
    
    //permet d'utiliser les variables et les fonctions de la classe Main 
    public final static Main getInstance()
    {
        if(Main.instance == null){
            synchronized(Main.class){
                if(Main.instance == null){
                    Main.instance = new Main();
                }
            }
        }
        return (Main.instance);
    }
    
    private Main()
    {
        super();
    }
    
    public Windows getWin()
    {
        return (this.f);
    }

    public void setWin(Windows newf)
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
                    Main.getInstance().setWin(new Windows());
                    Main.getInstance().getWin().setContentPane(new Menu("Automatika - Menu"));
                }
            });
    }    
}