
public class Zylinder {

	private double radius;
	private double hoehe;

	public double getRadius() {
		return radius;
	}

	public double getHoehe() {
		return hoehe;
	}
	public double setRadius(double radius) {
		return this.radius=radius;
	}
	public double setHoehe(double hoehe) {
		return this.hoehe=hoehe;
	}
	public double berechneUmfang() {
		double umfang=2*Math.PI*getRadius()*getHoehe();
		return umfang;
	}
	public double berechneFlaeche() {
		double flaeche=2*(Math.PI*Math.pow(getRadius(), 2))+berechneUmfang();
		return flaeche;
	}
	public double berechneVolumen() {
		double volumen=Math.PI*Math.pow(getRadius(), 2)*getHoehe();
		return volumen;
	}
}
