 /**
   TODO:
   --- ACTIONS
   1 : actions class
   2 : ctrl X
   3 : ctrl Y
   --- IMG
   4 : save to (API)
   5 : save
   6 : save as jpg
   --- IMPRESSION
   7 : API Windows
   8 : API Linux
   --- automaton
   9: double trace
**/
import java.util.LinkedList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JCheckBox; 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Event;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Automatika extends JFrame implements MouseListener, KeyListener
{
    int mode = 0; // 1 = no orient, 2 = orient, 3 = list
    int indice = 0;
    int id_hist = -1;
    int mousePressed_x = 0;
    int mousePressed_y = 0;
    JPanel draw_surface = new JPanel();
    boolean edit = false;
    boolean suppr = false;
    Node trait_origin = null;
    LinkedList<Trace> trace = new LinkedList<Trace>();
    LinkedList<Node> coord = new LinkedList<Node>();
    LinkedList<Action> actions = new LinkedList<Action>();
    int width = 789;
    int height = 456;
    
    /*
      In this constructor deserve to choose wich mod we will use 
      At this moment, we have just the mod no oriented graph
     */
    Automatika(int mode)
    {
	if(mode == 1) // graphe non oriente
	    this.mode = 1;
	else if(mode == 2) // graphe oriente
	    this.mode = 2;
	else if(mode == 3)// automaton
	    this.mode = 3;
	setSize(width, height);
	this.setContentPane(draw_surface);
	this.addMouseListener(this);
	this.addKeyListener(this);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
    }

    /***********************
     ***   CLASS  EDIT   ***
     ***********************/
    //this class is use for edit a node or a line in the paint
    public class Edit extends JFrame implements ActionListener
    {
	JPanel pan = new JPanel(new BorderLayout());
	JPanel pan_valeur = new JPanel();
	JPanel pan_line = new JPanel();
	JPanel pan_btn = new JPanel();
	JPanel pan_node = new JPanel();
	JTextField txt = new JTextField(10);
	JCheckBox start = new JCheckBox("Start");
	JCheckBox end = new JCheckBox("End");
	JCheckBox inversion = new JCheckBox("Inversion");
	JButton ok = new JButton("Ok");
	JButton cancel = new JButton("Cancel");
	int num;
	boolean is_node;
	Edit(boolean b, int n)// true => node; false => line
	{
	    this.is_node = b;
	    this.num = n;
	    setSize(300, 150);
	    if(b)
		node();
	    else
		line();
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setVisible(true);
	}

	//if object is a node
	public void node()
	{
	    pan_valeur.add(new JLabel("Valeur"));
	    pan_valeur.add("jtxtfld", txt);
	    pan_node.add(start);
	    pan_node.add(end);
	    pan_btn.add(cancel);
	    pan_btn.add(ok);
	    cancel.addActionListener(this);
	    ok.addActionListener(this);
	    pan.add("North", pan_valeur);
	    pan.add("Center", pan_node);
	    pan.add("South", pan_btn);
	    add(pan);
	}

	//if object is a line
	public void line()
	{
	    pan_valeur.add(new JLabel("Valeur"));
	    pan_valeur.add("jtxtfld", txt);
	    pan_line.add(inversion); 
	    pan_btn.add(cancel);
	    pan_btn.add(ok);
	    cancel.addActionListener(this);
	    ok.addActionListener(this);
	    pan.add("North", pan_valeur);
	    pan.add("Center", pan_line);
	    pan.add("South", pan_btn);
	    add(pan);	    
	}
	
	public void edit_node()
	{
	    coord.get(num).setStart(start.isSelected());
	    coord.get(num).setEnd(end.isSelected());	    
	    coord.get(num).setName(txt.getText());
	}
	
	public void edit_line()//TODO: complete
	{
	    trace.get(num).setName(txt.getText());
	    System.out.println("edit trace");
	}
	
	public void actionPerformed(ActionEvent e)
	{  
	    if(e.getSource() == ok)
		{
		    //System.out.println("btn -> ok, " + start.isSelected() + " " + end.isSelected());
		    if(is_node)
			{
			    id_hist++;
			    actions.add(new Action(5, coord.get(num), coord.get(num).getStart(), coord.get(num).getEnd(), coord.get(num).getName()));
			    
			    edit_node();
			}
		    else
			{
			    edit_line();
			}
		    setVisible(false);
		    repaint();
		}
	    else if(e.getSource() == cancel){
		//System.out.println("btn -> cancel");
		setVisible(false);
	    }
	}
    }
    
    /** end class edit**/

    
    // to do it again, jpg
    public void Save()
    {
	BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g = bi.createGraphics();
	g.setColor(draw_surface.getBackground());
	g.fillRect(0, 0, 789, 456);
 	g.setColor(Color.black);
	for(int i = 0; i < trace.size(); i++)
	    {
		int x1 = trace.get(i).getX1(),
		    y1 = trace.get(i).getY1(),
		    x2 = trace.get(i).getX2(),
		    y2 = trace.get(i).getY2();
		draw_surface.getGraphics().drawLine(x1, y1, x2, y2);
	    }
	for(int i = 0; i < coord.size(); i++)
	    {
		g.setColor(draw_surface.getBackground());
		g.fillOval(coord.get(i).x-26, coord.get(i).y-26, 50, 50);
		g.setColor(Color.black);
		g.drawOval(coord.get(i).x-25, coord.get(i).y-25, 50, 50);
		if(coord.get(i).isEnd())
		    {
			g.drawOval(coord.get(i).x-20, coord.get(i).y-20, 40, 40);			
		    }
		if(coord.get(i).isStart())
		   {
		       g.drawLine(coord.get(i).x - 25, coord.get(i).y, coord.get(i).x - 100, coord.get(i).y);
		       g.drawLine(coord.get(i).x - 25, coord.get(i).y, coord.get(i).x - 50, coord.get(i).y + 15);
		       g.drawLine(coord.get(i).x - 25, coord.get(i).y, coord.get(i).x - 50, coord.get(i).y - 15);
		   }
		g.drawString(coord.get(i).name, coord.get(i).x-5, coord.get(i).y+5);			
	    }
	JFileChooser fc = new JFileChooser();
	int returnVal = fc.showSaveDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    try{
		File file = fc.getSelectedFile();
		ImageIO.write(bi, "png", new File(file.getName() + ".png"));
	    }
	    catch(IOException ioe)
		{
		    ioe.printStackTrace();
		}
	}
    }

    public int dist(int x1, int y1, int x2, int y2)
    {
	return (int)(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }

    //actualize the panel after moved a node
    public void repaint()
    {
	Graphics g = draw_surface.getGraphics();
	g.setColor(draw_surface.getBackground());
	g.fillRect(0, 0, 789, 456);
 	g.setColor(Color.black);
	for(int i = 0; i < trace.size(); i++)
	    {
		if(trace.get(i).isLoop())
		    {
			g.drawOval(trace.get(i).getX1()-50, trace.get(i).getY1()-50, 50, 50);
			g.drawString(trace.get(i).name, trace.get(i).getX1()-40-(int)(trace.get(i).name.length()*2.8), trace.get(i).getY1()-50);
		    }
		else
		    {
			int x1 = trace.get(i).getX1(),
			    y1 = trace.get(i).getY1(),
			    x2 = trace.get(i).getX2(),
			    y2 = trace.get(i).getY2();
			if(dist(x1, y1, x2, y2) > 80)
			    g.drawString(trace.get(i).name, (x1+x2)/2, (y1+y2)/2);
			draw_surface.getGraphics().drawLine(x1, y1, x2, y2);
		    }
	    }
	for(int i = 0; i < coord.size(); i++)
	    {
		g.setColor(draw_surface.getBackground());
		g.fillOval(coord.get(i).x-26, coord.get(i).y-26, 50, 50);
		g.setColor(Color.black);
		g.drawOval(coord.get(i).x-25, coord.get(i).y-25, 50, 50);
		if(coord.get(i).isEnd())
		    {
			g.drawOval(coord.get(i).x-20, coord.get(i).y-20, 40, 40);			
		    }
		if(coord.get(i).isStart())
		    {
			g.drawLine(coord.get(i).x - 25, coord.get(i).y, coord.get(i).x - 100, coord.get(i).y);
			g.drawLine(coord.get(i).x - 25, coord.get(i).y, coord.get(i).x - 50, coord.get(i).y + 15);
			g.drawLine(coord.get(i).x - 25, coord.get(i).y, coord.get(i).x - 50, coord.get(i).y - 15);
		    }
		g.drawString(coord.get(i).name, coord.get(i).x-5, coord.get(i).y+5);			
	    }
	//System.out.println("repaint");		
    }

    public void ctrl_z()
    {	
	if(id_hist >= 0 )
	    {
		switch(actions.get(id_hist).getNum())
		    {
		    case 1:
			System.out.println("1 => Node suppr");
			delete(actions.get(id_hist).getNode());
			break;
		    case 2:
			coord.add(actions.get(id_hist).getNode());
			System.out.println("2 => Node re-creat: " + coord.get(coord.size() - 1));
			break;
		    case 3:
			System.out.println("3 => Trace suppr");
			delTrace(actions.get(id_hist).getTrace());
			break;
		    case 4:
			System.out.println("4 => Trace re-creat");
			Trace t = actions.get(id_hist).getTrace(); 
			trait_origin = t.getN1();
			addTrace(t.getN2());
			break;
		    case 5:
			Action a = actions.get(id_hist);
			System.out.println("5 => edit node");	
			a.getNode().setStart(a.getStart());
			a.getNode().setEnd(a.getEnd());
			a.getNode().setName(a.getName());
			break;
		    case 6:;break;
		    case 7:;break;
		    default:
			System.out.println("error");
		    }
		id_hist--;
	    }
    }
    
    public void ctrl_y()
    {
	if(id_hist < actions.size())
	    {
		switch(actions.get(id_hist).getNum())
		{
		case 1:break;
		case 2:break;
		case 3:break;
		case 4:break;
		case 5:break;
		case 6:break;
		case 7:break;
		}
		id_hist++;
	    }
    }

    /***************************
     *---      TESTS        ---*
     ***************************/    	

    /* definition: test if a < b < c */
    //call by: getTrace, getNumTrace
    public boolean isBetween(int a, int b, int c)
    { 
	return (a <= b && b <= c) || (a >= b && b >= c);
    }


    /*definition: test if point is out of the screen*/
    //call by: mouseReleased
    public boolean isOut(int click_x, int click_y)
    {
	return (click_x < 0 || click_y < 0 || click_x >= 789 || click_y >= 456);
    }
    
    /***************************
     *---     GET/SET       ---*
     ***************************/
    
    //use colinears vector
    /*
      return the trace where the user has cliked
     */
    public Trace getTrace(int click_x, int click_y)
    {
	int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
	for(int i = 0; i < trace.size(); i++)
	    {
		if(trace.get(i).isLoop() && 
		   click_y >= trace.get(i).getY1() - 60 &&
		   click_y <= trace.get(i).getY1() + 10 &&
		   click_x >= trace.get(i).getX1() - 60 &&
		   click_x <= trace.get(i).getX1() + 10)
		    {
			return trace.get(i);
		    }
		else
		    {
			x1 = trace.get(i).getX2() - trace.get(i).getX1();
			y1 = trace.get(i).getY2() - trace.get(i).getY1();
			x2 = click_x - trace.get(i).getX1();
			y2 = click_y - trace.get(i).getY1();
			if(isBetween(-2000,x1*y2 - y1*x2,2000) && 
			   isBetween(trace.get(i).getX1(), click_x,trace.get(i).getX2()) && 
			   isBetween(trace.get(i).getY1(), click_y,trace.get(i).getY2())
			   )
			    return trace.get(i);
		    }
	    }
	return null;
    }

    /*
      return the number of the trace where the user has clicked
      call by: mouseClicked
     */
    public int getNumTrace(int click_x, int click_y)
    {
	int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
	//System.out.println(click_x + ", " + click_y);
	for(int i = 0; i < trace.size(); i++)
	    {
		if(trace.get(i).isLoop() && 
		   click_y >= trace.get(i).getY1() - 60 &&
		   click_y <= trace.get(i).getY1() + 10 &&
		   click_x >= trace.get(i).getX1() - 60 &&
		   click_x <= trace.get(i).getX1() + 10)
		    {
			return (i);
		    }
		else
		    {
			x1 = trace.get(i).getX2() - trace.get(i).getX1();
			y1 = trace.get(i).getY2() - trace.get(i).getY1();
			x2 = click_x - trace.get(i).getX1();
			y2 = click_y - trace.get(i).getY1();
			if(isBetween(-2000,x1*y2 - y1*x2,2000) && 
			   isBetween(trace.get(i).getX1(), click_x,trace.get(i).getX2()) && 
			   isBetween(trace.get(i).getY1(), click_y,trace.get(i).getY2()))
			    return i;
		    }
	    }
	return -1;
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

    public int getNodeId(Node n)
    {
	for(int i = 0; i < coord.size(); i++)
	    {
		if(coord.get(i) == n)
		    {
			return i;
		    }
	    }
	return -1;
    }
    
    public int getNumNode(int x, int y)
    {
	for(int i = 0; i < coord.size(); i++)
	    {
		if( y >= coord.get(i).y - 25 &&
		    y <= coord.get(i).y + 25 &&
		    x >= coord.get(i).x - 25 &&
		    x <= coord.get(i).x + 25)
		    {
			return i;
		    }
	    }
	return -1;
    }


    /***************************
     *---     ACTIONS       ---*
     ***************************/

    //create a node
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
		draw_surface.getGraphics().drawString(name, x-5, y+5);
		draw_surface.getGraphics().drawOval(x-25, y-25, 50, 50);
	    }
    }

    /*
     * trait_origin is no null
     * n is the node released
     * definition: to make a trace between two nodes
     */
    // TODO: split
    public void newTrait(Node n)
    {
	if(n == null){ return;} // if node not found
	int x_1 = trait_origin.x, y_1 = trait_origin.y, x_2 = n.x, y_2 = n.y;
	trait_origin.add_transition(n);
	n.add_transition(trait_origin);
	addTrace(n);
	//repaint();
    }

    /*
     * Remove a node and his traces from others nodes
     */
    public void delete(Node n)
    {
	int i = 0, x1 = 0, x2 = 0, y1 = 0, y2 = 0;
	for(; i < coord.size() && !coord.get(i).equals(n); i++){}
	if(i == coord.size()){ return;}
	Graphics g = draw_surface.getGraphics();
	g.setColor(draw_surface.getBackground());
	g.fillOval(coord.get(i).x-26, coord.get(i).y-26, 52, 52);
	Iterator<Node> it_node = n.transitions.iterator();
	Node s = null;
	while(it_node.hasNext())
	    {
		s = it_node.next();
		g.drawLine(coord.get(i).x, coord.get(i).y, s.x, s.y);
		for(int j = 0; j < trace.size(); j++)
		    {
			/*
			x1 = trace.get(j).getX1();
			x2 = trace.get(j).getX2();
			y1 = trace.get(j).getY1();
			y2 = trace.get(j).getY2();
			if((x1 == coord.get(i).x && y1 == coord.get(i).y && x2 == s.x && y2 == s.y) || (x1 == s.x && y1 == s.y && x2 == coord.get(i).x && y2 == coord.get(i).y))
			    {
				trace.remove(j);
			    }
			*/
			if(trace.get(j).getN1() == n || trace.get(j).getN2() == n)
			    {
				trace.remove(j);				
				//System.out.println("trace suppr");
			    }
		    }
	    }
	coord.remove(i);
	//repaint();
	return;
    }

    public void delTrace(Trace t)
    {
	if(t == null){return ;}
	if(t.getN1() == t.getN2())
	    {
		//System.out.println("not yet, todo");
	    }
	else
	    {
		t.getN1().suppr_transition(t.getN2());
		t.getN2().suppr_transition(t.getN1());
		for(int i = 0 ; i < trace.size(); i++)
		    {
			if(trace.get(i) == t)
			    {
				trace.remove(i);
				//System.out.println("trace suppr");
			    }
		    }
		//repaint();
	      
	    }
    }

    public void addTrace(Node n)
    {
	boolean exist = false;
	int x1, x2, y1, y2;
	for(int k = 0; k < trace.size(); k++)
	    {
		/*
		x1 = trace.get(k).getX1();
		x2 = trace.get(k).getX2();
		y1 = trace.get(k).getY1();
		y2 = trace.get(k).getY2();
		if((x1 == x_1 && x2 == x_2 && y1 == y_1 && y2 == y_2) || 
		   (x1 == x_2 && x2 == x_1 && y1 == y_2 && y2 == y_1))
		    {
			exist = true;
			break;
		    }
		*/
		if((trace.get(k).getN1() == trait_origin && trace.get(k).getN2() == n) ||
		   (trace.get(k).getN1() == n && trace.get(k).getN2() == trait_origin))
		    {
			exist = true;
			break;
		    }
	    }
	if(!exist){
	    trace.add(new Trace(trait_origin, n));
	    //System.out.println("new trace");
	}
    }


    /*
      to move a node
     */
    public void Move(int x, int y)
    {
	Iterator<Node> it = trait_origin.transitions.iterator();
	Graphics g = draw_surface.getGraphics();
	Node n = null;
	int old_x = trait_origin.x;
	int old_y = trait_origin.y;
	//System.out.println("move : (" + old_x + ", " + old_y + ") =>" + " (" + x + ", " + y + ")");
	newNode(x, y, trait_origin.name);	
	g.setColor(draw_surface.getBackground());
	trait_origin.x = x;
	trait_origin.y = y;
	/*
	g.drawOval(old_x-25, old_y-25, 50, 50);
	g.fillOval(old_x-25, old_y-25, 50, 50);
	while(it.hasNext())
	    {
		g.setColor(draw_surface.getBackground());
		n = it.next();
		//deleting phase
		/*
		for(int i = 0; i < trace.size(); i++)
		    {
			int x1 = trace.get(i).getX1(), 
			    x2 = trace.get(i).getX2(), 
			    y1 = trace.get(i).getY1(), 
			    y2 = trace.get(i).getY2();
			Node t = getNode(n.x, n.y);
			if(t != null && ((x1 == old_x && x2 == t.x && y1 == old_y && y2 == t.y) || (x1 == t.x && x2 == old_x && y1 == t.y && y2 == old_y)))
			    {
				trace.set(i, new Trace(trait_origin.x,trait_origin.y, n.x, n.y));
				break;
			    }
		    }
		    
		g.drawLine(old_x,old_y, n.x, n.y);
		g.drawOval(n.x-25, n.y-25, 50, 50);
		//add phase
		//System.out.println(trait_origin.name);
		g.setColor(Color.BLACK);	
		newTrait(getNode(n.x, n.y));
		//System.out.println(n);
	    }
    */
	//repaint();
	id_hist++;
	actions.add(new Action(7, trait_origin,old_x, old_y, x, y));
    }

    /***************************
     *---     CONTROLS      ---*
     ***************************/

    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){
	if(e.getKeyCode() == KeyEvent.VK_CONTROL && edit) edit = false;
	if(e.getKeyCode() == KeyEvent.VK_DELETE && suppr) suppr = false;
    }
    public void keyPressed(KeyEvent e){
	if(e.isControlDown()) edit = true;
	if(e.getKeyCode() == KeyEvent.VK_Z && edit){repaint(); ctrl_z(); repaint();}
	if(e.getKeyCode() == KeyEvent.VK_Y && edit){System.out.println("ctrl_y();");}
	if(e.getKeyCode() == KeyEvent.VK_O && edit){System.out.println("open file");}
	if(e.getKeyCode() == KeyEvent.VK_S && edit){Save();}
	if(e.getKeyCode() == KeyEvent.VK_DELETE){suppr=true;}
    }

    public void mouseClicked(MouseEvent e)
    {
	if(e.getClickCount() == 2 && edit == true)
	    {
		int num_trace = getNumTrace(e.getX(), e.getY() - 25);
	        int num_node  = getNumNode(e.getX(), e.getY() - 25);
		if(num_trace != -1)
		    if(num_node != -1)
			new Edit(true, num_node);
		    else
			new Edit(false, num_trace);
		else
		    if(num_node != -1)
			new Edit(true, num_node);
	    }
    }


    public void mouseReleased(MouseEvent e)
    {
	int dist_mouse = dist(mousePressed_x, mousePressed_y, e.getX(), e.getY()-25);
	if(isOut(e.getX(), e.getY() - 25))
	    {
		return;
	    }	
	if(suppr == true && trait_origin != null)
	    {
		Node to_del = getNode(e.getX(), e.getY() - 25);
		actions.add(new Action(2, to_del));	
		delete(to_del);
		id_hist++;
		repaint();
	    }
	else if(suppr)
	    {
		Trace to_del = getTrace(e.getX(), e.getY() - 25);
		delTrace(to_del);
		actions.add(new Action(4, to_del));
		id_hist++;
		repaint();
	    }
	else if(edit == true && trait_origin != null && dist_mouse > 2)
	    {
		//System.out.println(dist_mouse);
		Move(e.getX(), e.getY() - 25);
		repaint();
	    }
	else if(trait_origin != null && edit == false)
	    {
		newTrait(getNode(e.getX(), e.getY() - 25));
		actions.add(new Action(3, trace.get(trace.size()-1)));
		id_hist++;
		trait_origin = null;
		repaint();
	    }
	else if(edit == false)
	    {
		String name = "q" + indice++;
		int x = e.getX(), y = e.getY() - 25;
		newNode(x, y, name);
		id_hist++;
		coord.add(new Node(x, y, name));
		actions.add(new Action(1, coord.get(coord.size() - 1), x, y));
		repaint();
	    }
	if(id_hist < actions.size() - 1)
	    {
		System.out.println("id_hist < actions.size(): " + id_hist + "\t" + actions.size());
	    }
	
	for(int i = 0; i < actions.size(); i++)
	    {
		System.out.println(i + ": " +actions.get(i));
	    }
	System.out.println("--------------------------------------\n");
    }
    
    public void mousePressed(MouseEvent e){
	mousePressed_x = e.getX();
	mousePressed_y = e.getY() - 25;
	trait_origin = getNode(mousePressed_x, mousePressed_y);
    }
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
}
