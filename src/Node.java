import java.util.Set;
import java.util.HashSet;

public class Node
{
    int x = 0;
    int y = 0;
    String name;
    boolean start = false;
    boolean end = false;
    Set<Node> transitions = new HashSet<Node>();
    Node(int a, int b, String n)
    {
	x = a;
	y = b;
	name = n;
    }

    public int getX()
    {
	return x;
    }
    
    public int getY()
    {
	return y;
    }

    public void add_transition(Node q)
    {
	transitions.add(q);
    }

    public void suppr_transition(Node n)
    {
	transitions.remove(n);
    }

    public String print_transitions()
    {
	return transitions.toString();
    }

    public boolean isStart()
    {
	return start;
    }

    public boolean isEnd()
    {
	return end;
    }

    public void setStart(boolean start)
    {
	this.start = start;
    }

    public void setEnd(boolean end)
    {
	this.end = end;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public String getName()
    {
	return (name);
    }    

    public boolean getStart()
    {
	return (start);
    }

    public boolean getEnd()
    {
	return (end);
    }

    public String toString()
    {
	return name +" (" + x + ", " + y + ")";
    }
}
