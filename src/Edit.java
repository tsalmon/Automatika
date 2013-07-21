import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Edit extends JFrame 
{
    JTextField txt = new JTextField(10);
    Edit()
    {
	setSize(300, 150);                                                                          
        JPanel pan = new JPanel();                                         
        pan.add("jtxtfld", txt);
	//txt.addKeyListener(this);	
	txt.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
		    System.out.println("ok");
		}
	    });
	add(pan);
	setDefaultCloseOperation(EXIT_ON_CLOSE);                                 
	setVisible(true);               
    }
}