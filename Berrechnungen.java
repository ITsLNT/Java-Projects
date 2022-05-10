import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Berrechnungen extends JFrame {
	JLabel Zylinder;
	JLabel Rechteck;
	JLabel PasswordCheck;
	public Berrechnungen() {
		BufferedImage img=null;
		try {
			img=ImageIO.read(new File("C:/Users/LHalf/OneDrive/Pictures/ScreenShot2126.jpg"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		Image dimg=img.getScaledInstance(400, 400,Image.SCALE_SMOOTH);
		ImageIcon imageIcon=new ImageIcon(dimg);
		this.setContentPane(new JLabel(imageIcon));
		this.setTitle("Berrechnungen");
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.initWindow();
	}
	protected void initWindow() {
		Zylinder=new JLabel("Zylinder");
		Rechteck=new JLabel("Rechteck");
		PasswordCheck=new JLabel("PasswordCheck");
		//
		Font font=new Font("Arial",Font.BOLD,16);
		Zylinder.setFont(font);
		Zylinder.setForeground(Color.MAGENTA);
		Zylinder.setBackground(Color.LIGHT_GRAY);
		Zylinder.setBounds(20, 30, 90, 20);
		Zylinder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ZylinderUse.main(null);
			}
		});
		Rechteck.setFont(font);
		Rechteck.setForeground(Color.MAGENTA);
		Rechteck.setBackground(Color.LIGHT_GRAY);
		Rechteck.setBounds(20, 60, 90, 20);
		Rechteck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Use.main(null);
			}
		});
		PasswordCheck.setFont(font);
		PasswordCheck.setForeground(Color.MAGENTA);
		PasswordCheck.setBackground(Color.LIGHT_GRAY);
		PasswordCheck.setBounds(20, 90, 110, 20);
		PasswordCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PasswordCheckWindow.main(null);
				
			}
		});
		this.getContentPane().add(PasswordCheck);
		this.getContentPane().add(Rechteck);
		this.getContentPane().add(Zylinder);
		this.pack();
	}
}
