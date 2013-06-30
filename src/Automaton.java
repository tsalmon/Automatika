import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class Automaton extends JPanel implements MouseListener, KeyListener
{
    JPanel draw_surface = new JPanel();
    JButton save_button = new JButton("save");    
    static boolean move = false;
    Node trait_origin = null;
    LinkedList<Node> coord = new LinkedList<Node>();
    //private Panneau pan;
    public Automaton()
    {
	JPanel header = new JPanel();
	header.add(save_button);
	setSize(Main.window_x, Main.window_x);
	setLayout(new BorderLayout());
	add("North", header);
	add("Center", draw_surface);
	draw_surface.addMouseListener(this);
	draw_surface.addKeyListener(this);
	setVisible(true);
    }
    
    public void newNode(int x, int y)
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
		draw_surface.getGraphics().drawString("q" + coord.size(), x-5, y+5);
		//System.out.println("q"+ coord.size());
		coord.add(new Node(x,y, "q"+coord.size()));
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
			draw_surface.getGraphics().drawLine(trait_origin.x, trait_origin.y, coord.get(i).x, coord.get(i).y);
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
			System.out.println(coord.get(i).print_transitions());
			System.out.println(trait_origin.print_transitions());
			return;
		    }
		else
		    {
			//System.out.println("new trait : fail");
		    }
	    }
    }
    //Pressed => Released
    public void mouseClicked(MouseEvent e)
    {
	if(e.getSource() == save_button)
	    {

	    }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e)
    {
	for(int i = 0; i < coord.size(); i++)
	    {
		if( e.getY() >= coord.get(i).y - 25 && 
		    e.getY() <= coord.get(i).y + 25 && 
		    e.getX() >= coord.get(i).x - 25 && 
		    e.getX() <= coord.get(i).x + 25)
		    {
			System.out.println("origin pret");
			trait_origin = coord.get(i);
			return ;
		    }
	    }
    }
    public void mouseReleased(MouseEvent e)
    {
	if(e.getX() < 0 || e.getY() < 0 || e.getX() >= Main.window_x || e.getY() >= Main.window_y)
	    {
		System.out.println("OUT OF THE SCREEN");
		/*System.out.println("e.getX() < 0 : "+ (e.getX() < 0) +"\n" + 
				   "e.getY() < 0 : " + (e.getY() < 0)+ "\n" + 
				   "e.getX() >= 500 : " + (e.getX() >= Main.window_x) + "\n" + 
				   "e.getY() >= 300 : " + ( e.getY() >= Main.window_y));
		*/
		return;
	    }
	if(trait_origin != null)
	    {
		System.out.println("trait released : ok");
		newTrait(e.getX(), e.getY());
		trait_origin = null;
	    }
	else
	    {
		System.out.println("mouseReleased: nouveau noeud");
		newNode(e.getX(), e.getY());
	    }
    }

    @Override
	public void keyPressed(KeyEvent arg0) {
	// TODO Auto-generated method stub
	System.out.println(arg0.getKeyCode());
    }

    @Override
	public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
	public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub

    }
}

