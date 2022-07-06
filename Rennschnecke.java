import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Rennschnecke {
	String Name;
	String Rasse;
	int Max_Geschwindigkeit;
	int Akt_Weg=0;
	JLabel slug;
	public static void main(String[] args) {
		System.out.println("Legen sie Teilnehmerzahl und Streckenlänge fest");
		
		Rennen y1=new Rennen("Bestes Rennen",Student.x.nextInt(),Student.x.nextInt());
		y1.running();
		
	}
	
	public Rennschnecke(String name,String rasse,int max){
		this.Name=name;
		this.Rasse=rasse;
		this.Max_Geschwindigkeit=max;
		
	}
	public void krieche() {
		this.Akt_Weg= this.Akt_Weg+(int)(Math.random()*this.Max_Geschwindigkeit);
	}
	public String toString() {
		String rückgabe="Name: "+this.Name+"  Rasse: "+this.Rasse+"  Maximalgeschwindigkeit: "+this.Max_Geschwindigkeit+"  zurückgelegter Weg: "+this.Akt_Weg;
		return rückgabe;
	}
	public void toImage(File e,int x,int y) {
		BufferedImage img=null;
		try {
			img=ImageIO.read(e);
		}catch(IOException e1) {
			e1.printStackTrace();
		}
		Image dimg=img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		ImageIcon imageIcon=new ImageIcon(dimg);
		this.slug=new JLabel(imageIcon);
		
	}
	public JLabel getImage() {
		return this.slug;
	}
}
