public class Action
    {
	/*
	 * 1: create node
	 * 2: suppr node
	 * 3: create path
	 * 4: suppr path
	 * 5: edit node
	 * 6: edit path
	 * 7; move node
	 */
	int num_action = 0;
	int num_node = 0;
	int num_trace = 0;
	int old_pos_x;
	int old_pos_y;
	int new_pos_x;
	int new_pos_y;
	Node n = null; 
	Trace t = null;
	boolean start = false;
	boolean end = false;
	String value = null;
	//1, 7
	Action(int num, Node n, int x, int y)
	{
	    num_action = num;
	    this.num_node = num_node;
	    this.n = n;
	    this.old_pos_x = x;
	    this.old_pos_y = y;
	}
	// 2
	Action(int num, Node n)
	{
	    num_action = num;
	    this.num_node = num_node;
	    this.n = n;
	}
	//3, 4
	Action(int num, Trace t)
	{			
	    num_action = num;
	    this.num_trace = num_trace;
	    this.t = t;
	}
	//5
	Action(int num, Node n,boolean start, boolean end, String name)
	{			
	    this.num_action = num;
	    this.n = n;
	    this.start = start;
	    this.end = end;
	    value = name;
	}
	//6 
	Action(int num, Trace t, String value)// last value
	{
	    this.num_action = num;
	    this.t = t;
	    this.value = value;
	}
	
	public int getOldX(){return old_pos_x;}
	public int getOldY(){return old_pos_y;}
	public int getNum(){return num_action;}
	public Node getNode(){return (n);}
	public Trace getTrace(){return (t);}
	public boolean getStart(){return start;}	
	public boolean getEnd(){return end;}
	public String getName(){return value;}
	
	public String toString()
	{
	    String return_str = "";
	    switch(num_action)
		{
		case 1: return_str = "node  creat " + n; break;
		case 2: return_str = "node  suppr " + n; break;
		case 3: return_str = "trace creat " + t; break;
		case 4: return_str = "trace suppr " + t; break;
		case 5: return_str = "node  edit  " + n + " s:" + start + ", " + "e:" + end + ", " + "v:" + value; break;
		case 6: return_str = "trace edit  " + t; break;
		case 7: return_str = "node  move  " + n + " (" + old_pos_x + ", " + old_pos_y + ")"; break; 
		}
	    return num_action + ":= " + return_str;
	}
    }
