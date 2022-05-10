import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TicTacToe extends JFrame {
	static int i=0;
	static String x="Bilder/X_Icon";
	static String y="Bilder/Kreis_Icon";
	Button upperLeft;
	Button upperMiddle;
	Button upperRight;
	Button Left;
	Button Middle;
	Button Right;
	Button lowerLeft;
	Button lowerMiddle;
	Button lowerRight;
	static int counterUL=0;
	static int counterUM=0;
	static int counterUR=0;
	static int counterL=0;
	static int counterM=0;
	static int counterR=0;
	static int counterLR=0;
	static int counterLM=0;
	static int counterLL=0;
	public TicTacToe() {
		this.setTitle("TicTacToe");
		BufferedImage img= null;
		try {
			img=ImageIO.read(new File("C:/Users/LHalf/OneDrive/Pictures/origin_400i.jpeg"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		Image dimg=img.getScaledInstance(245,245, Image.SCALE_SMOOTH);
		ImageIcon imageIcon=new ImageIcon(dimg);
		setContentPane(new JLabel(imageIcon));
		this.getContentPane().setLayout(null);
		WindowListener listener = new WindowAdapter() {
			 public void windowClosing(WindowEvent w) {
				 dispose();
			 }};
		this.addWindowListener(listener);	 
		this.setBackground(Color.blue);
		this.initWindow();
	}
	protected void initWindow() {
		upperLeft=new Button(null);
		upperMiddle=new Button(null);
		upperRight=new Button(null);
		Left=new Button(null);
		Middle=new Button(null);
		Right=new Button(null);
		lowerLeft=new Button(null);
		lowerMiddle=new Button(null);
		lowerRight=new Button(null);
		upperLeft.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				buttonChangeClickedUL(counterUL);
				counterUL++;
			}
		});
		upperMiddle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				buttonChangeClickedUM(counterUM);
				counterUM++;
			}
		});
		upperRight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				buttonChangeClickedUR(counterUR);
				counterUR++;
			}
		});
		Left.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				buttonChangeClickedL(counterL);
				counterL++;
			}
		});
		Middle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				buttonChangeClickedM(counterM);
				counterM++;
			}
		});
		Right.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				buttonChangeClickedR(counterR);
				counterR++;
			}
		});
		
		lowerLeft.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				buttonChangeClickedLL(counterLL);
				counterLL++;
			}
		});
		lowerMiddle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				buttonChangeClickedLM(counterLM);
				counterLM++;
			}
		});
		lowerRight.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {
				buttonChangeClickedLR(counterLR);
				counterLR++;
			}
		});
		
		
		
		upperLeft.setBounds(5, 20, 60, 60);
		upperMiddle.setBounds(65, 20, 60, 60);
		upperRight.setBounds(125, 20, 60, 60);
		Left.setBounds(5,80,60,60);
		Middle.setBounds(65, 80, 60, 60);
		Right.setBounds(125, 80, 60, 60);
		lowerLeft.setBounds(5, 140, 60, 60);
		lowerMiddle.setBounds(65,140, 60, 60);
		lowerRight.setBounds(125, 140, 60, 60);
		//this.getContentPane().add(Title);
		this.getContentPane().add(upperLeft);
		this.getContentPane().add(upperMiddle);
		this.getContentPane().add(upperRight);
		this.getContentPane().add(Left);
		this.getContentPane().add(Middle);
		this.getContentPane().add(Right);
		this.getContentPane().add(lowerLeft);
		this.getContentPane().add(lowerMiddle);
		this.getContentPane().add(lowerRight);
		this.pack();
		}

	public void buttonChangeClickedUL(int i) {
		
		if (i == 0) {
			upperLeft.setLabel("X");
			
		}
		if (i == 1) {
			upperLeft.setLabel("O");
			
		}

	}
	public void buttonChangeClickedUM(int i) {
		
		if (i == 0) {
			upperMiddle.setLabel("X");
			
		}
		if (i == 1) {
			upperMiddle.setLabel("O");
			
		}

	}
	public void buttonChangeClickedUR(int i) {
		
		if (i == 0) {
			upperRight.setLabel("X");
			
		}
		if (i == 1) {
			upperRight.setLabel("O");
			
		}

	}
	public void buttonChangeClickedL(int i) {
		
		if (i == 0) {
			Left.setLabel("X");
			
		}
		if (i == 1) {
			Left.setLabel("O");
			
		}

	}
	public void buttonChangeClickedM(int i) {
		
		if (i == 0) {
			Middle.setLabel("X");
			
		}
		if (i == 1) {
			Middle.setLabel("O");
			
		}
		

	}
	public void buttonChangeClickedR(int i) {
		
		if (i == 0) {
			Right.setLabel("X");
			
		}
		if (i == 1) {
			Right.setLabel("O");
			
		}
		

	}
	public void buttonChangeClickedLL(int i) {
		
		if (i == 0) {
			lowerLeft.setLabel("X");
			
		}
		if (i == 1) {
			lowerLeft.setLabel("O");
			
		}

	}
	public void buttonChangeClickedLM(int i) {
		
		if (i == 0) {
			lowerMiddle.setLabel("X");
			
		}
		if (i == 1) {
			lowerMiddle.setLabel("O");
			
		}

	}
	public void buttonChangeClickedLR(int i) {
		
		if (i == 0) {
			lowerRight.setLabel("X");
			
		}
		if (i == 1) {
			lowerRight.setLabel("O");
			
		}

	}

	}

