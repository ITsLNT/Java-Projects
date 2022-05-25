
public class StudentDatabase {
	Student[] studentDatabase;
	String vorname;
	String name;

	public StudentDatabase(int groesse) {
		studentDatabase = new Student[groesse];
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
		System.out.println(studentDatabase[0]);

		System.out.println("Geben sie ihr Alter an");
		int alter = Student.x.nextInt();
		
		System.out.println("Geben sie ihren Studiengang ein");
		String studiengang = Student.x.next();
		for(int i=1;i<studentDatabase.length;i++){
			studentDatabase[i]=new Student();
		}
		studentDatabase[0] = new Student(a[1],a[0],0,studiengang,alter,0);
	}

	public void printMe() {
		for (int i = 0; i < studentDatabase.length; i++) {
			System.out.print("Schüler "+(i+1)+": ");
			studentDatabase[i].printMe();
		}
	}
}
