import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ZylinderWindow extends JFrame{
	JLabel Radius;
	JTextField radius;
	JLabel Hoehe;
	JTextField hoehe;
	JLabel umfang;
	JTextField Umfang;
	JLabel flaeche;
	JTextField Flaeche;
	JLabel volumen;
	JTextField Volumen;
	JButton berrechnen;
	Font font;
	public ZylinderWindow() {
		this.setTitle("Zylinder");
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.initWindow();
	}
	protected void initWindow() {
		//Instanzieren
		font=new Font("Arial",Font.BOLD,16);
		Radius=new JLabel("Radius");
		radius=new JTextField();
		Hoehe=new JLabel("Höhe");
		hoehe=new JTextField();
		umfang=new JLabel("Umfang");
		Umfang=new JTextField();
		flaeche=new JLabel("Fläche");
		Flaeche=new JTextField();
		volumen=new JLabel("Volumen");
		Volumen=new JTextField();
		berrechnen=new JButton("Berrechnen");
		//Bounds/Werte
		Radius.setFont(font);
		Radius.setForeground(Color.BLUE);
		Radius.setBounds(5, 10, 90, 15);
		radius.setBounds(5, 30, 100, 20);
		Hoehe.setFont(font);
		Hoehe.setForeground(Color.BLUE);
		Hoehe.setBounds(5, 50, 90, 15);
		hoehe.setBounds(5, 65, 100, 20);
		umfang.setFont(font);
		umfang.setForeground(Color.BLUE);
		umfang.setBounds(5, 85, 90, 15);
		Umfang.setBounds(5, 100, 100, 20);
		flaeche.setFont(font);
		flaeche.setForeground(Color.BLUE);
		flaeche.setBounds(5, 120, 90, 15);
		Flaeche.setBounds(5, 135, 100, 20);
		volumen.setFont(font);
		volumen.setForeground(Color.BLUE);
		volumen.setBounds(5, 155, 90, 15);
		Volumen.setBounds(5, 170, 100, 20);
		berrechnen.setBounds(30, 190, 60, 20);
		berrechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked();
			}
		});
		hoehe.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode()==10)berrechnen.doClick();
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10)berrechnen.doClick();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});
		radius.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode()==10)berrechnen.doClick();
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10)berrechnen.doClick();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});
		//Zum Fenster hinzufügen
		this.getContentPane().add(Radius);
		this.getContentPane().add(radius);
		this.getContentPane().add(Flaeche);
		this.getContentPane().add(Hoehe);
		this.getContentPane().add(Umfang);
		this.getContentPane().add(Volumen);
		this.getContentPane().add(berrechnen);
		this.getContentPane().add(flaeche);
		this.getContentPane().add(hoehe);
		this.getContentPane().add(radius);
		this.getContentPane().add(umfang);
		this.getContentPane().add(volumen);
		this.pack();
	}
	public void buttonClicked() {
		double radius=0;
		double hoehe=0;
		
		try {
			radius=Double.parseDouble(this.radius.getText());
			hoehe=Double.parseDouble(this.hoehe.getText());
		}catch(NumberFormatException e){
			radius--;
			hoehe--;
		}
		if(radius>0&&hoehe>0) {
			
			Use zylinder=new Use();
			double umfang=zylinder.Zylinder_Use(radius, hoehe, 1);
			double flaeche=zylinder.Zylinder_Use(radius, hoehe, 2);
			double volumen=zylinder.Zylinder_Use(radius, hoehe, 3);
			NumberFormat nf=NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			String ausgabe1=nf.format(umfang)+" cm²";
			String ausgabe2=nf.format(flaeche)+" cm²";
			String ausgabe3=nf.format(volumen)+" cm³";
			Umfang.setText(ausgabe1);
			Flaeche.setText(ausgabe2);
			Volumen.setText(ausgabe3);
		}else {
			Umfang.setText("Die Eingabe ist ungültig");
		}
	}
	
}
