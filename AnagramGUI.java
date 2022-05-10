import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AnagramGUI extends JFrame{
	JTextField Eingabe1;
	JTextField Eingabe2;
	JButton checking;
	public AnagramGUI() {
		this.getContentPane().setLayout(null);
		this.setTitle("Anagram");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.initWindow();
	}
	protected void initWindow() {
		Eingabe1=new JTextField();
		Eingabe1.setBounds(6, 40, 150, 20);
		Eingabe2=new JTextField();
		Eingabe2.setBounds(6,60,150,20);
		checking=new JButton("Start");
		checking.setBounds(40,80,75,40);
		checking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] g= {Eingabe1.getText(),Eingabe2.getText()};
				Anagram.main(g);
			}
		});
		this.getContentPane().add(Eingabe1);
		this.getContentPane().add(Eingabe2);
		this.getContentPane().add(checking);
		this.pack();
	}
}
