import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Edit extends JFrame implements ActionListener
{
    JTextField txt = new JTextField(10);
    Edit()
    {
	setSize(300, 150);                                                                          
        JPanel pan = new JPanel();                                         
        pan.add("jtxtfld", txt);
	txt.addActionListener(this);
	add(pan);
	setDefaultCloseOperation(EXIT_ON_CLOSE);                              
        setVisible(true);               
    }

    public void actionPerformed(ActionEvent e)
    {
	if(e.getSource() == txt)
	    {
		System.out.println("ok");
	    }
    }
    
}