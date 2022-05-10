import java.util.Scanner;

public class Anagram {
	static Scanner x=new Scanner(System.in);
	static int counter=0;
	static int counterB=0;
	public static void main(String[]args) {
		Anagram y=new Anagram();
		String a=args[0];
		String b=args[1];
		y.anagram(a,b);
	}
	public void anagram(String a ,String b) {
		this.scan(a,b);
		
		
	}
	protected void scan(String a,String b) {
		
		
		if(a.length()==b.length()) {
		for(int i=0;i<a.length();i++) {
			counter=counter+a.charAt(i);
			counterB=counterB+b.charAt(i);
		}
		if(counter==counterB) {
				System.out.println("Ja,es ist ein Anagram.");
			}	}
		if(counter!=counterB||a.length()!=b.length()) System.out.println("Nein,es ist kein Anagram.");
		
	}
}
