public class Trace{
    private Node n1;
    private Node n2;
    int loop_pos = 1; //1 = NoWe; 2 = NoEa; 3 = SoWe; 4 = SoEa;
    String name = "";
    
    Trace(Node n1, Node n2)
    {
	this.n1 = n1;
	this.n2 = n2;
    }
        
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

    public boolean isLoop()
    {
	return (n1 == n2);
    }
    
    public String toString()
    {
	return "([" + n1.x + ", " + n1.y + "]; [" + n2.x + ", " + n2.y + "])";
    }
}
