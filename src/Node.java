import java.util.*;
public class Node
{
    int x;
    int y;
    String name;
    LinkedList<Node> transitions = new LinkedList<Node>();
    Node(int a, int b, String n)
    {
	x   = a;
	y   = b;
	name = n;
    }
}