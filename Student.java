import java.util.Scanner;

public class Student {
	public static Scanner x=new Scanner(System.in);
	private String Name;
	private String Vorname;
	private int Matrikelnummer;
	private String Studiengang;
	private int Alter;
	public static int anzStudenten=0;
	public static Student[] database=new Student[100];
	public Student(String name,String vorname,int matrikelnummer,String studiengang,int alter,int studienjahr){
		anzStudenten++;
		setName(name,vorname);
		setMatrikelnummer(matrikelnummer,studienjahr);
		setStudiengang(studiengang);
		setAlter(alter);
	}
	public void setName(String name,String vorname){
		this.Vorname=vorname;
		this.Name=name;
	}
	public String getName(){
		String insName=Name+", "+Vorname;
		return insName;
	}
	public int getMatrikelnummer() {
		return Matrikelnummer;
	}
	public void setMatrikelnummer(int matrikelnummer,int studienjahr) {
		if(studienjahr==0){
		Matrikelnummer =(int)Math.random()*10000;}
		else{
			Matrikelnummer=matrikelnummer;
		}
	}
	public String getStudiengang() {
		return Studiengang;
	}
	public void setStudiengang(String studiengang) {
		Studiengang = studiengang;
	}
	public int getAlter() {
		return Alter;
	}
	public void setAlter(int alter) {
		Alter = alter;
	}
	public void printMe(){
		System.out.println(getName());
		System.out.println(getMatrikelnummer());
		System.out.println(getStudiengang());
		System.out.println(getAlter());
	}
	
}
