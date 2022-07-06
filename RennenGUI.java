import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class RennenGUI extends JFrame {
	
	JTextBlock print;
	JTextField ein;
	JButton go;
	int goCount = 0;
	Rennen neu;
	int Teilnehmer;
	public RennenGUI() {
		this.setTitle("Rennen");
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.initWindow();
	}

	protected void initWindow() {
		print = new JTextBlock(new JLabel("Legen sie Teilnehmerzahl, Streckenlänge und Name des Rennens fest. In dieser Reihenfolge!!"));
		ein = new JTextField();
		go = new JButton("go");
		Font font=new Font("Arial",Font.BOLD,8);
		print.setFont(font);
		print.setBounds(5, 20, 400, 20);
		ein.setBounds(5, 60, 400, 20);
		go.setBounds(50, 100, 60, 20);
		ein.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				if(e.getKeyCode()==10)
				go.doClick();

			}

			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==10)
				go.doClick();

			}

			@Override
			public void keyReleased(KeyEvent e) {
				

			}

		});
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked(goCount);
				goCount++;
			}
		});
		this.getContentPane().add(ein);
		this.getContentPane().add(go);
		this.getContentPane().add(print);
	}

	protected void buttonClicked(int count) {
		switch (count) {
		case 1:
			String s=ein.getText();
			String[] a=s.split(" ");
			Teilnehmer=Integer.valueOf(a[0]);
			neu=new Rennen(a[2],Integer.valueOf(a[0]),Integer.valueOf(a[1]));
			ein.setText("");
			print.addField(new JLabel("Geben sie Name, Rasse und max Gechwindigkeit der Schnecke an"));
			break;
		case 2:
			if(count-1<Teilnehmer) {
			s=ein.getText();
			
		    String[] b=s.split(" ");
		    System.out.println(b.length);
			String name=b[0];
			String rasse=b[1];
			int vmax=Integer.valueOf(b[2]);
			neu.addSlug(new Rennschnecke(name,rasse,vmax));
			ein.setText("");}
			
			
			print.addField(new JLabel(neu.printME()));
			break;
		case 3:
			if(count-1<Teilnehmer) {
			s=ein.getText();
			String[] b;
		    b=s.split(" ");
		    System.out.println(b.length);
			String name=b[0];
			String rasse=b[1];
			int vmax=Integer.valueOf(b[2]);
			neu.addSlug(new Rennschnecke(name,rasse,vmax));
			ein.setText("");
			}
			
			print.addField(new JLabel(neu.printME()));
			break;
		case 4:
			if(count-1<Teilnehmer) {
				s=ein.getText();
				String[] b=s.split(" ");
				String name=b[0];
				String rasse=b[1];
				int vmax=Integer.valueOf(b[2]);
				neu.addSlug(new Rennschnecke(name,rasse,vmax));
				ein.setText("");
				print.addField(new JLabel(neu.printME()));
			}
		default:
			if(count-1==Teilnehmer)
			while(neu.running())
			print.setText(neu.printME());
			break;
			
		}
	}
}
