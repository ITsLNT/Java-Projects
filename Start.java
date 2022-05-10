import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Start extends JFrame {
	
static Scanner xy=new Scanner(System.in);
private javax.swing.border.Border border = javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED);
	public static void main(String[] args) {BufferedImage img= null;
		try {
			img=ImageIO.read(new File("C:/Users/LHalf/OneDrive/Pictures/ScreenShot2126.jpg"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		Image dimg=img.getScaledInstance(400,400, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon=new ImageIcon(dimg);
		int x=400;
		int y=400;
		Start window=new Start(x,y,imageIcon);
		window.setBounds(x, y, x, y);
		window.setVisible(true);
		
		

	}
	
	public Start(int x,int y,ImageIcon imageIcon) {
		this.setTitle("Lobby");
		
		this.setContentPane(new JLabel(imageIcon));
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		this.initWindow();
	}
	protected void initWindow() {
		Font font=new Font("Times New Roman",Font.BOLD,16);
		JLabel TicTacToe=new JLabel("TicTacToe");
		TicTacToe.setBounds(30, 50, 100, 20);
		TicTacToe.setBorder(border);
		TicTacToe.setFont(font);
		TicTacToe.setForeground(Color.MAGENTA);
		TicTacToe.setBackground(Color.BLACK);
		this.getContentPane().add(TicTacToe);
		TicTacToe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TicTacToe_Window.main(null);
				 
			}
		});
		
		JLabel Berrechnungen=new JLabel("Berrechnungen");
		Berrechnungen.setFont(font);
		Berrechnungen.setForeground(Color.MAGENTA);
		Berrechnungen.setBackground(Color.LIGHT_GRAY);
		Berrechnungen.setBounds(30, 100, 120, 10);
		this.getContentPane().add(Berrechnungen);
		Berrechnungen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Berrechnungen b=new Berrechnungen();
				b.setSize(400, 400);
				b.setVisible(true);
			}
		});
		JLabel Programme=new JLabel("Programme");
		Programme.setFont(font);
		Programme.setForeground(Color.magenta);
		Programme.setBackground(Color.LIGHT_GRAY);
		Programme.setBounds(30, 120, 120, 10);
		this.getContentPane().add(Programme);
		Programme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Programme window=new Programme();
				window.setSize(400, 400);
				window.setVisible(true);
			}
		});
	}
	

}
