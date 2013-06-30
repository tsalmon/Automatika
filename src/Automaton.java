import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

public class Automaton extends JPanel implements MouseListener, KeyListener
{
    JPanel draw_surface = new JPanel();
    //Graphics g = .getGraphics();

    static boolean move = true;
    int trait_x = -1;
    int trait_y = -1;
    LinkedList<Node> coord = new LinkedList<Node>();
    //private Panneau pan;
    public Automaton()
    {
	setSize(Main.window_x, Main.window_x);
	setLayout(new BorderLayout());
	add("North", new JButton("Lol"));
	add("Center", draw_surface);
	draw_surface.addMouseListener(this);
	draw_surface.addKeyListener(this);
	setVisible(true);
    }
    
    public void mouseClicked(MouseEvent e)
    {
	newNode(e.getX(), e.getY());
    }

    public void newNode(int x, int y)
    {
	boolean go = true;
	for(int i = 0; i < coord.size(); i+=2)
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
		coord.add(new Node(x,y, "q"+coord.size()));
	    }
    }

    public void newTrait(int x, int y)
    {
	for(int i = 0; i < coord.size(); i+=2)
	    {
		if( y >= coord.get(i).y - 25 && 
		    y <= coord.get(i).y + 25 && 
		    x >= coord.get(i).x - 25 && 
		    x <= coord.get(i).x + 25)
		    {
			draw_surface.getGraphics().setColor(Color.red);
			System.out.println("new trait: ok");
			draw_surface.getGraphics().drawLine(trait_x, trait_y, coord.get(i).x, coord.get(i).y);
			Graphics g = draw_surface.getGraphics();
			g.setColor(draw_surface.getBackground());
			g.fillOval(trait_x-25, trait_y-25, 50, 50);
			g.fillOval(coord.get(i).x-25, coord.get(i).y-25, 50, 50);
			g.setColor(Color.black);
			draw_surface.getGraphics().drawString("q" + coord.size(), x-5, y+5);
			
			return;
		    }
		else
		    {
			System.out.println("new trait : fail");
		    }
	    }
	trait_x = -1;
    }

    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

    public void mousePressed(MouseEvent e)
    {
	for(int i = 0; i < coord.size(); i+=2)
	    {
		if( e.getY() >= coord.get(i).y - 25 && 
		    e.getY() <= coord.get(i).y + 25 && 
		    e.getX() >= coord.get(i).x - 25 && 
		    e.getX() <= coord.get(i).x + 25)
		    {
			trait_x = coord.get(i).x;
			trait_y = coord.get(i+1).y;
			System.out.println(trait_x + " " + trait_y);
			return ;
		    }
	    }
    }
    public void mouseReleased(MouseEvent e)
    {
	if(e.getX() < 0 || e.getY() < 0 || e.getX() >= Main.window_x || e.getY() >= Main.window_y)
	    {
		System.out.println("OUT OF THE SCREEN");
		System.out.println("e.getX() < 0 : "+ (e.getX() < 0) +"\n" + 
				   "e.getY() < 0 : " + (e.getY() < 0)+ "\n" + 
				   "e.getX() >= 500 : " + (e.getX() >= Main.window_x) + "\n" + 
				   "e.getY() >= 300 : " + ( e.getY() >= Main.window_y));
		return;
	    }
	if(trait_x > -1)
	    {
		System.out.println("trait released : ok");
		newTrait(e.getX(), e.getY());
	    }
	else
	    {
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

