public class Action
    {
	/*
	 * 1: create node
	 * 2: suppr node
	 * 3: create path
	 * 4: suppr path
	 * 5: edit node
	 * 6: edit path
	 */
	int num_action = 0;
	Node n = null;
	Trace t = null;
	boolean start = false;
	boolean end = false;
	String value = null;
	//1, 2
	Action(int num, Node n)
	{
	    num_action = num;
	    this.n = n;
	}
	//3, 4
	Action(int num, Trace t)
	{			
	    num_action = num;
	    this.t = t;
	}
	//5
	Action(int num, boolean start, boolean end, String name)
	{			
	    num_action = num;
	    this.start = start;
	    this.end = end;
	    value = name;
	}
	//6
	Action(int num, String value)
	{
	    num_action = num;
	    this.value = value;
	}
		
	public int getNum()
	{
	    return num_action;
	}
    }
