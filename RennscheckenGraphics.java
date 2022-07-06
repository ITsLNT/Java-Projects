import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class RennscheckenGraphics extends JFrame {
	ArrayList<Rennschnecke> a = new ArrayList<Rennschnecke>();
	ArrayList<JLabel> label = new ArrayList<JLabel>();
	int i = 0;
	private int Strecke = 400;
	static int sCount = 0;
	int y = 0;
	int y2 = 0;
	JTextField ein;
	JButton next;
	JLabel slug1;
	JLabel slug2;
	JLabel slug3;

	public static void main(String[] args) {
		RennscheckenGraphics win = new RennscheckenGraphics();
		win.setSize(600, 600);
		win.setVisible(true);
	}

	public RennscheckenGraphics() {
		this.setTitle("Schneckenrennen");
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:/Users/LHalf/OneDrive/Pictures/Rasen.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		this.setContentPane(new JLabel(imageIcon));
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.initWindow();
	}

	protected void initWindow() {
		ein = new JTextField();
		ein.setBounds(5, 10, 100, 20);
		next = new JButton("next");
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonClicked();
			}
		});
		this.getContentPane().add(next);
		ein.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == 10) {

					next.doClick();

				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == 10) {

					next.doClick();

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

		});
		Font font = new Font("Arial", Font.BOLD, 10);
		JLabel legend = new JLabel("1.Slug is green; 2.Slug is gray; 3.Slug is blue");
		legend.setBounds(300, 20, 250, 15);
		legend.setFont(font);
		this.getContentPane().add(legend);
		this.getContentPane().add(ein);

		this.pack();
	}

	public boolean areRunning() {
		for (int i = 0; i < a.size(); i++) {
			a.get(i).krieche();
			System.out.println(a.get(i).Akt_Weg);
			if (a.get(i).Akt_Weg >= getStrecke()) {
				return false;
			}
		}
		return true;
	}

	public int getStrecke() {
		return this.Strecke;
	}

	public void setStrecke(int s) {
		if (sCount == 0) {
			this.Strecke = s;
		}

	}

	/*
	 * public void doIt() { do { for (int i = 0; i < a.size(); i++) {
	 * a.get(i).getImage().setBounds(a.get(i).Akt_Weg, y + 30, 20, 30);
	 * this.getContentPane().add(a.get(i).getImage()); } } while (areRunning()); }
	 */

	public void setGraphic() {
		while (areRunning()) {
			for (int i = 0; i <= a.size(); i++) {
				this.y = y + 30;
				Graphics graphics = this.getGraphics();
				switch (i) {
				case 0:
					graphics.setColor(Color.green);
					break;
				case 1:
					graphics.setColor(Color.GRAY);
					break;
				case 2:
					graphics.setColor(Color.BLUE);
					break;
				}
				graphics.drawLine(0, y, a.get(i).Akt_Weg, y);

			}
		}

	}

	public void paint(Graphics g, Rennschnecke e) {
		if (e.Akt_Weg < Strecke / 2)
			g.setColor(Color.ORANGE);
		else if (e.Akt_Weg > Strecke / 2)
			((Graphics) g).setColor(Color.yellow);
		else if (e.Akt_Weg == Strecke)
			((Graphics) g).setColor(Color.green);
		((Graphics) g).drawRect(e.Akt_Weg, y + 30, 10, 29);

	}

	public void buttonClicked() {

		String[] c = ein.getText().split(" ");

		if (i <= 3) {
			a.add(new Rennschnecke(c[0], c[1], Integer.valueOf(c[2])));
			ein.setText("");
			i++;
		}
		if (i++ == 4)
			this.setGraphic();
		/*
		 * if (i++ > 4) this.doIt();
		 */

	}
}
