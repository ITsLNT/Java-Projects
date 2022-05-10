
public class Recteck {
	public int laenge;
	public int breite;
	public int flaeche;
	public int umfang;

	public int setLaenge(int laenge) {
		return this.laenge = laenge;
	}

	public int getLaenge() {
		return laenge;
	}

	public int setBreite(int breite) {
		return this.breite = breite;
	}

	public int getBreite() {
		return breite;
	}

	public int flaeche() {
		flaeche = getLaenge() * getBreite();
		return flaeche;
	}

	public int umfang() {
		umfang = 2 * getLaenge() + 2 * getBreite();
		return umfang;
	}
}
