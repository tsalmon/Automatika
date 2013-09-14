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
	boolean last_start = false;
	boolean last_end = false;
	String last_value = null;
	boolean new_start = false;
	boolean new_end = false;
	String new_value = null;

	//1, 7
	Action(int num, Node n, int old_x, int old_y, int new_x, int new_y)
	{
	    num_action = num;
	    this.num_node = num_node;
	    this.n = n;
	    this.old_pos_x = old_x;
	    this.old_pos_y = old_y;
	    this.new_pos_x = new_x;
	    this.new_pos_y = new_y;
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
	Action(int num, Node n,
	       boolean last_start, boolean last_end, String last_name, 
	       boolean new_start, boolean new_end, String new_name)
	{			
	    this.num_action = num;
	    this.n = n;
	    this.last_start = last_start;
	    this.last_end = last_end;
	    this.last_value = last_name;
	    this.new_start = new_start;
	    this.new_end = new_end;
	    this.new_value = new_name;
	}
	//6 
	Action(int num, Trace t, String last_value, String new_value)// last value
	{
	    this.num_action = num;
	    this.t = t;
	    this.last_value = last_value;
	    this.new_value = new_value;
	}
	
	public int getOldX(){return old_pos_x;}
	public int getOldY(){return old_pos_y;}
	public int getNewX(){return new_pos_x;}
	public int getNewY(){return new_pos_y;}
	public int getNum(){return num_action;}
	public Node getNode(){return (n);}
	public Trace getTrace(){return (t);}
	public boolean getOldStart(){return last_start;}	
	public boolean getOldEnd(){return last_end;}
	public String getOldName(){return last_value;}
	public boolean getNewStart(){return new_start;}	
	public boolean getNewEnd(){return new_end;}
	public String getNewName(){return new_value;}
	
	public String toString()
	{
	    String return_str = "";
	    switch(num_action)
		{
		case 1: return_str = "node  creat " + n; break;
		case 2: return_str = "node  suppr " + n; break;
		case 3: return_str = "trace creat " + t; break;
		case 4: return_str = "trace suppr " + t; break;
		case 5: return_str = "node  edit  " + n + " Ls:" + last_start + ", " + "Le:" + last_end + ", " + "Lv:" + last_value; break;
		case 6: return_str = "trace edit  " + t; break;
		case 7: return_str = "node  move  " + n + " (" + old_pos_x + ", " + old_pos_y + ")"; break; 
		}
	    return num_action + ":= " + return_str;
	}
    }
