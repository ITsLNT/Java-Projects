import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RechteckWindow extends JFrame {
	JTextField laenge;
	JLabel laeng;
	JTextField breite;
	JLabel breit;
	JTextField Umfang;
	JLabel umfang;
	JTextField Flaeche;
	JLabel flaeche;
	JButton button;
	public RechteckWindow() {
		this.setTitle("Rechteck");
		this.getContentPane().setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.initWindow();
	}
	protected void initWindow() {
		Font font=new Font("Arial",Font.BOLD,16);
		//Instanzieren
		laenge=new JTextField();
		laeng=new JLabel("Länge");
		breite=new JTextField();
		breit=new JLabel("Breite");
		Umfang=new JTextField();
		umfang=new JLabel("Umfang");
		Flaeche=new JTextField();
		flaeche=new JLabel("Fläche");
		button=new JButton("Berrechnen");
		//Attribute festlegen
		laeng.setFont(font);
		laeng.setForeground(Color.BLUE);
		breit.setFont(font);
		breit.setForeground(Color.BLUE);
		umfang.setFont(font);
		umfang.setForeground(Color.BLUE);
		flaeche.setFont(font);
		flaeche.setForeground(Color.BLUE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked();
			}
		});
		//Bounds setzen
		laenge.setBounds(5, 50, 150, 30);
		laeng.setBounds(5, 30, 50, 20);
		breite.setBounds(5, 100, 150, 30);
		breit.setBounds(5, 80, 50, 20);
		Umfang.setBounds(5,150,150,30);
		umfang.setBounds(5,130,60,20);
		Flaeche.setBounds(5,200,150,30);
		flaeche.setBounds(5,180,50,20);
		button.setBounds(40,240,120,30);
		//zum Fenster hinzufügen
		this.getContentPane().add(Flaeche);
		this.getContentPane().add(Umfang);
		this.getContentPane().add(breit);
		this.getContentPane().add(breite);
		this.getContentPane().add(button);
		this.getContentPane().add(flaeche);
		this.getContentPane().add(laeng);
		this.getContentPane().add(laenge);
		this.getContentPane().add(umfang);
		breite.addKeyListener(new KeyListener() {

			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getKeyCode()==10) {
					button.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==10) {
					button.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.pack();
		
	}
	protected void buttonClicked() {
		
		int laenge=0;
		int breite=0;
		laenge=(int)Double.parseDouble(this.laenge.getText());
		breite=(int)Double.parseDouble(this.breite.getText());
		if(laenge>0&&breite>0) {
			Use rechteck=new Use();
			int umfang=rechteck.Rechteck_Use(laenge, breite, 1);
			int flaeche=rechteck.Rechteck_Use(laenge, breite, 2);
			String ausgabe1=String.valueOf(umfang);
			String ausgabe2=String.valueOf(flaeche);
			Umfang.setText(ausgabe1);
			Flaeche.setText(ausgabe2);
			
		}
		else {
			Flaeche.setText("Eingabe ist nicht gültig");
		}
	}
}
