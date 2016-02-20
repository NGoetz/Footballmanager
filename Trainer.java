package fussballmanager;

public class Trainer extends Person {
	private int erfahrung;
	public Trainer (String n, int a, int e){
		super(n,a);
		erfahrung=e;
	}
	public int getErfahrung(){
		return erfahrung;
	}
	public void setErfahrung (int n){
		erfahrung=n;
	}
}
//by Niklas Götz