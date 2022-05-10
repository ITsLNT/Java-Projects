import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class Programme extends JFrame {
	JLabel Browser;
	JLabel YouTube;

	public Programme() {
		this.setTitle("Programme");
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:/Users/LHalf/OneDrive/Pictures/ScreenShot2126.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(400, 400, Image.SCALE_SMOOTH);

		ImageIcon imageIcon = new ImageIcon(dimg);
		this.setContentPane(new JLabel(imageIcon));
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.initWindow();
	}

	protected void initWindow() {
		Browser = new JLabel("Browser");
		YouTube = new JLabel("YouTube");

		Font font = new Font("Arial", Font.BOLD, 16);
		Browser.setFont(font);
		Browser.setForeground(Color.magenta);
		Browser.setBackground(Color.LIGHT_GRAY);
		Browser.setBounds(30, 20, 100, 10);
		Browser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					Runtime.getRuntime().exec("cmd /c start www.google.com");
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}

			}
		});
		YouTube.setFont(font);
		YouTube.setForeground(Color.magenta);
		YouTube.setBackground(Color.LIGHT_GRAY);
		YouTube.setBounds(30, 40, 100, 10);
		YouTube.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Runtime.getRuntime().exec("cmd /c start www.youtube.com");
				}catch(Exception e1) {
					e1.printStackTrace();				}
			}
		});

		this.getContentPane().add(Browser);
		this.getContentPane().add(YouTube);
		this.pack();
	}
}
