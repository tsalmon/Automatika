import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Launcher extends JFrame implements ActionListener
{
    static JFrame f = null;
    JButton Automaton = new JButton("Automaton");
    JButton LinkedList = new JButton("LinkedList");
    JButton Graph = new JButton("Graph");
    
    Launcher()
    {
	JPanel pan = new JPanel();
       	setSize(789, 456);                                                                                         
	pan.add(Automaton);
	pan.add(LinkedList);
	pan.add(Graph);
	add(pan);
	Automaton.addActionListener(this);
	LinkedList.addActionListener(this);
	Graph.addActionListener(this);
	setDefaultCloseOperation(EXIT_ON_CLOSE);                                                                    
	setVisible(true);  	
    }

    public void actionPerformed(ActionEvent e)
    {
	setVisible(false);
	if(e.getSource() == Automaton)
	    {
		f = new Automatika(1);
	    }
	else if(e.getSource() == LinkedList)
	    {
		f = new Automatika(2);		
	    }
	else if(e.getSource() == Graph)
	    {
		f = new Automatika(3);
	    }
    }
    
    public static void help_launcher()
    {
	System.out.println("Automatika's arguments:\n"
			   + "\t-g: Graph\n"
			   + "\t-a: Automaton\n"
			   + "\t-o: LinkedList\n");	
    }
    
   public static void main(String [] args)
    {
	new Automatika(2);
	/*
	if(args.length == 1)
	    {
		if(args[0].equals("-g"))//graphe non oriente                               
		    f = new Automatika(1);   
		else if(args[0].equals("-o"))//graphe oriente
		    f = new Automatika(2);
		else if(args[0].equals("-a"))
		    f = new Automatika(3);
		else
		    help_launcher();
	    }  
	else if(args.length == 0)
	    f = new Launcher();
	else
	    help_launcher();
    */
    }
}
