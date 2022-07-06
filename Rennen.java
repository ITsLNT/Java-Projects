import java.util.ArrayList;

public class Rennen {
	private String Name;
	private int anz_T;
	private int Strecke;
	Rennschnecke neu;
	String s;
	ArrayList<Rennschnecke> a=new ArrayList<Rennschnecke>();
	public Rennen(String name,int T,int s) {
		this.Name=name;
		this.anz_T=T;
		this.Strecke=s;
		/*this.printME();
		
		System.out.println("Geben sie Name, Rasse und max Gechwindigkeit der Schnecke an");
		
		for(int i=0;i<T;i++) {
		this.addSlug();}
		while(running())
		this.printME();*/
	}
	
	public void addSlug(Rennschnecke y) {
		
		a.add(y);
	}
	public void removeSlug(String name) {
		
			if(neu.Name==name) {
				a.remove(neu);
			}
		}
	protected String printME() {
		System.out.println(this.Name+"; "+this.anz_T+"; Strecke: "+this.Strecke);
		for(Rennschnecke e:a) {
			s=s+e.toString()+"\n";
		}
		return s;
		
	}
	public boolean running() {
		for(int i=0;i<a.size();i++) {
			a.get(i).krieche();
			if(a.get(i).Akt_Weg>=this.Strecke) {
				System.out.println(a.get(i).Name+" ist der/die Sieger/in");
				return false;
			}
			else {
				
			}
			
		}
		return true;
	}
		
}

