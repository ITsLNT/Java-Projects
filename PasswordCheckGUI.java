import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PasswordCheckGUI extends JFrame {
	JTextField password;
	JLabel proceed;
	JLabel check;
	public PasswordCheckGUI() {
		/*BufferedImage img=null;
		try {
			img=ImageIO.read(new File("C:/Users/LHalf/OneDrive/Pictures/ArcCorp_Night.WEBP"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		Image dimg=img.getScaledInstance(200, 200,Image.SCALE_SMOOTH);
		ImageIcon imageIcon=new ImageIcon(dimg);
		this.setContentPane(new JLabel(imageIcon));*/
		this.getContentPane().setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.initWindow();
	}
	protected void initWindow() {
		Font font=new Font("Times New Roman",Font.BOLD,20);
		password=new JTextField();
		password.setBounds(50, 90, 100, 20);
		proceed=new JLabel("proceed");
		proceed.setBounds(50, 110, 100, 20);
		proceed.setFont(font);
		proceed.setForeground(Color.GREEN);
		proceed.setBackground(Color.LIGHT_GRAY);
		proceed.setVisible(false);
		check=new JLabel("check");
		check.setBounds(50, 110, 60, 20);
		check.setFont(font);
		check.setForeground(Color.BLACK);
		check.setBackground(Color.LIGHT_GRAY);
		check.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent w) {
				PaswordCheck y=new PaswordCheck();
				if(y.Password(password.getText())) {
					proceed.setVisible(true);
					check.setVisible(false);				
					}
				else {
					check.setText("Try again");
					check.setForeground(Color.RED);
				}
				
			}
		});
		this.getContentPane().add(check);
		this.getContentPane().add(proceed);
		this.getContentPane().add(password);
		
		this.pack();
		
	}
	
}
