import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Campus {
	static String vorname;
	static String name;
	static int alter;
	static String studiengang;
	static byte studienjahr;
	static int matrikelnummer;
	static String ein;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Gib deinen Namen ein");
		name = Student.x.nextLine();

		String[] a = new String[2];
		if (name.contains(" ")) {
			a = name.split(" ");
			vorname = a[0];
			name = a[1];
		} else {
			vorname = Student.x.next();
		}
		System.out.println("Geben sie ihr Alter an");
		alter = Student.x.nextInt();
		System.out.println("Geben sie ihren Studiengang ein");
		studiengang = Student.x.next();
		System.out.println("Geben sie an in welchem Semester sie sind, wenn sie gerade anfangen dann sind es 0");
		studienjahr = Student.x.nextByte();
		System.out.println("Nun ihre Matrikelnummer, falls sie noch keine haben drücken sie enter");
		ein = Student.x.next();
		KeyListener w = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					matrikelnummer = 0;
				} else {
					for (int i = 0; i < ein.length(); i++) {
						if (Character.isDigit(ein.charAt(i)))
							matrikelnummer = matrikelnummer + ein.charAt(i);
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == 10) {
					matrikelnummer = 0;
				} else {
					for (int i = 0; i < ein.length(); i++) {
						if (Character.isDigit(ein.charAt(i)))
							matrikelnummer = matrikelnummer + ein.charAt(i);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		};

		Student s1 = new Student(name, vorname, matrikelnummer, studiengang, alter, studienjahr);
		s1.printMe();
	}

}
