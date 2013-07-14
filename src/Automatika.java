import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;                                                                             
import javax.swing.JLabel;
import java.util.*;
public class Automatika extends JFrame implements MouseListener, KeyListener
{
    JPanel draw_surface = new JPanel();
    boolean edit = false;
    Node trait_origin = null;
    LinkedList<Trace> trace = new LinkedList<Trace>();
    LinkedList<Node> coord = new LinkedList<Node>();

    public class Trace{
        int u1;
        int u2;
        int v1;
        int v2;

        Trace(int a, int b, int c, int d)
        {
            u1 = a;
            u2 = b;
            v1 = c;
            v2 = d;
        }
    }

    public class Node
    {
	int x;
	int y;
	String name;
	Set<Node> transitions = new HashSet<Node>();
	Node(int a, int b, String n)
	{
	    x   = a;
	    y   = b;
	    name = n;
	}
	
	public void add_transition(Node q)
  	{
	    transitions.add(q);
	}    
	public String print_transitions()
	{
	    return transitions.toString();
	}
	
	public String toString()
	{
	    return name +" (" + x + ", " + y + ")";
	}
    }
    
    Automatika(int mode)
    {
	if(mode == 1) // graphe non oriente
	    System.out.println("g");
	else if(mode == 2) // graphe oriente
	    System.out.println("o");		
	else if(mode == 3)// automaton
	    System.out.println("a");	
	setSize(789, 456);
	this.setContentPane(draw_surface);
	this.addMouseListener(this);
	this.addKeyListener(this);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
    }
    
    public void newNode(int x, int y, String name)
    {
        boolean go = true;
        for(int i = 0; i < coord.size(); i++)
            {
                if( y >= coord.get(i).y - 25 &&
                    y <= coord.get(i).y + 25 &&
                    x >= coord.get(i).x - 25 &&
                    x <= coord.get(i).x + 25)
                    {
                        go = false;
                    }
            }
        if(go)
            {
                draw_surface.getGraphics().drawOval(x-25, y-25, 50, 50);
                draw_surface.getGraphics().drawString(name, x-5, y+5);
                //System.out.println("q"+ coord.size());                                                   
                //System.out.println("coord.size:" + coord.size());                                        
            }
    }

    public void newTrait(int x, int y)
    {
        for(int i = 0; i < coord.size(); i++)
            {
                if( y >= coord.get(i).y - 25 &&
                    y <= coord.get(i).y + 25 &&
                    x >= coord.get(i).x - 25 &&
                    x <= coord.get(i).x + 25)
                    {
                        draw_surface.getGraphics().setColor(Color.red);
                        //System.out.println("new trait: ok");       
                        draw_surface.getGraphics().drawLine(trait_origin.x,trait_origin.y,coord.get(i).x, coord.get(i).y);
                        Graphics g = draw_surface.getGraphics();
                        g.setColor(draw_surface.getBackground());
                        g.fillOval(trait_origin.x-25, trait_origin.y-25, 50, 50);
                        g.fillOval(coord.get(i).x-25, coord.get(i).y-25, 50, 50);
                        g.setColor(Color.black);
                        g.drawOval(trait_origin.x-25, trait_origin.y-25, 50, 50);
                        g.drawOval(coord.get(i).x-25, coord.get(i).y-25, 50, 50);
                        draw_surface.getGraphics().drawString(coord.get(i).name, coord.get(i).x-5, coord.get(i).y+5);
                        draw_surface.getGraphics().drawString(trait_origin.name, trait_origin.x-5, trait_origin.y+5);
                        trait_origin.add_transition(coord.get(i));
                        coord.get(i).add_transition(trait_origin);

			//System.out.println(coord.get(i).print_transitions());                            
                        //System.out.println(trait_origin.print_transitions());                            
                        return;
                    }
                else
                    {
                        //System.out.println("new trait : fail");                                          
                    }
            }
    }

    public Node getNode(int x, int y)
    {
        for(int i = 0; i < coord.size(); i++)
            {
                if( y >= coord.get(i).y - 25 &&
                    y <= coord.get(i).y + 25 &&
                    x >= coord.get(i).x - 25 &&
                    x <= coord.get(i).x + 25)
                    {
                        return coord.get(i);
                    }
            }
        return null;
    }

    public void Move(int x, int y)
    {
        Iterator<Node> it = trait_origin.transitions.iterator();
        Graphics g = draw_surface.getGraphics();
        Node n = null;
        int old_x = trait_origin.x;
        int old_y = trait_origin.y;

        g.setColor(draw_surface.getBackground());
        trait_origin.x = x;
        trait_origin.y = y;
        g.drawOval(old_x-25, old_y-25, 50, 50);
        g.fillOval(old_x-25, old_y-25, 50, 50);
        while(it.hasNext())
            {
                n = it.next();
                //deleting phase                                                                           
                g.drawLine(old_x,old_y, n.x, n.y);
                g.drawOval(n.x-25, n.y-25, 50, 50);
                //add phase                                                                                
                //System.out.println(trait_origin.name);                                                   
                newTrait(n.x, n.y);
                //System.out.println(n);                                                                   
            }
        newNode(x, y, trait_origin.name);
    }

    public void keyTyped(KeyEvent e){System.out.println("keytyped");}
    public void keyReleased(KeyEvent e){
	if(edit) edit = false;
    }
    
    public void keyPressed(KeyEvent e){
	if(e.isControlDown())
	    edit = true;
    }
    public void mouseClicked(MouseEvent e){System.out.println("mouseclicked");}
    public void mouseReleased(MouseEvent e)
    {
	if(e.getX() < 0 || e.getY() -25 < 0 || e.getX() >= 789 || e.getY() -25  >= 456)
            {
                System.out.println("OUT OF THE SCREEN");
                /*System.out.println("e.getX() < 0 : "+ (e.getX() < 0) +"\n" +
		  "e.getY() < 0 : " + (e.getY() < 0)+ "\n" +
		  "e.getX() >= 500 : " + (e.getX() >= Main.window_x) + "\n" +
		  "e.getY() >= 300 : " + ( e.getY() >= Main.window_y));
		*/
                return;
            }
        if(edit == true && trait_origin != null)
            {
                Move(e.getX(), e.getY() - 25);
            }
        if(trait_origin != null && edit == false)
            {
                newTrait(e.getX(), e.getY() - 25);
                trait_origin = null;
            }
        else if(edit == false)
            {
                String name = "q" + coord.size();
                int x = e.getX(), y = e.getY() - 25;
                newNode(x, y, name);
                coord.add(new Node(x, y, name));
            }
    }
    
    public void mousePressed(MouseEvent e){
        trait_origin = getNode(e.getX(), e.getY() - 25);
    }
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    
    public static void help_args()
    {
	System.out.println("Automatika argument's:\n\tAutomatika -g : no oriented graph\n\tAutomatika -o : oriented graph\n\tAutomatika -a : automaton");
    }
    
    public static void main(String [] args)
    {
	if(args.length == 1)
	    {
		if(args[0].equals("-g"))
		    new Automatika(1);
		else if(args[0].equals("-o"))
		    new Automatika(2);
		else if(args[0].equals("-a"))
		    new Automatika(3);
		else
		    help_args();
	    }
	else if(args.length == 0)
	    System.out.println("no choice");
	else
	    help_args();
    }
}