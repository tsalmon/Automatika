public class Trace{
    /*private int u1;
    private int u2;
    private int v1;
    private int v2;
    */
    private Node n1;
    private Node n2;

    String name;
    
    Trace(Node n1, Node n2)
    {
	this.n1 = n1;
	this.n2 = n2;
    }
    
    /*Trace(int a, int b, int c, int d)
    {
	u1 = a;
	u2 = b;
	v1 = c;
	v2 = d;
	}*/
    
    
    int getX1()
    {
	return n1.x;
    }
    
    int getY1()
    {
	return n1.y;
    }
    
    int getX2()
    {
	return n2.x;
    }
    
    int getY2()
    {
	return n2.y;
    }
    

    Node getN1()
    {
	return n1;
    }

    Node getN2()
    {
	return n2;
    }

    public void setName(String name)
    {
	this.name = name;
    }
    
    public String toString()
    {
	return "([" + n1.x + ", " + n1.y + "]; [" + n2.x + ", " + n2.y + "])";
    }
}
