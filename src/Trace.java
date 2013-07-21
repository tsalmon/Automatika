public class Trace{
    private int u1;
    private int u2;
    private int v1;
    private int v2;
    
    Trace(int a, int b, int c, int d)
    {
	u1 = a;
	u2 = b;
	v1 = c;
	v2 = d;
    }
    
    int getX1()
    {
	return u1;
    }
    
    int getY1()
    {
	return u2;
    }
    
    int getX2()
    {
	return v1;
    }
    
    int getY2()
    {
	return v2;
    }
    
    public String toString()
    {
	return "([" + u1 + ", " + u2 + "]; [" + v1 + ", " + v2 + "])";
    }
}
