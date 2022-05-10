import java.util.Scanner;

public class Use {
	static Scanner x = new Scanner(System.in);
	static Zylinder zylinder;
	static Recteck Rechteck;

	public static void main(String[] args) {
		RechteckWindow window = new RechteckWindow();
		window.setSize(270, 330);
		window.setVisible(true);

	}

	public double Zylinder_Use(double radius, double hoehe, int auswahl) {
		zylinder = new Zylinder();
		zylinder.setRadius(radius);
		zylinder.setHoehe(hoehe);
		switch (auswahl) {
		case 1:
			return zylinder.berechneUmfang();
		case 2:
			return zylinder.berechneFlaeche();
		case 3:
			return zylinder.berechneVolumen();
		default:
			break;
		}
		
		return 0;
	}

	public int Rechteck_Use(int laenge, int breite, int auswahl) {
		Rechteck = new Recteck();
		Rechteck.setLaenge(laenge);
		Rechteck.setBreite(breite);
		switch (auswahl) {
		case 1:
			return Rechteck.umfang();
		case 2:
			return Rechteck.flaeche();
		default:
			break;
		}
		return 0;
	}

}
