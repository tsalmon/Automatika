import java.util.*;

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