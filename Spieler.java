package fussballmanager;
import java.util.Random;

public class Spieler extends Person {
	private int staerke;
	private int torschuss;
	private int motivation;
	private int tore;
	private int gelbeKarten;
	private int roteKarten;
	public Spieler(String n, int a, int s, int ts, int m, int g, int r){
		super(n,a);
		staerke=s;
		torschuss=ts;
		motivation=m;
		tore=0;
		gelbeKarten=g;
		roteKarten=r;
	}
	public int getStaerke(){
		return staerke;
	}
	public void setStaerke(int n){
		staerke=n;
		if(staerke>10)
			staerke=10;
		if(staerke<1)
			staerke=1;
	}
	public int getGelbeKarten(){
		return gelbeKarten;
	}
	public void setGelbeKarten(int k){
		gelbeKarten=k;
	}
	public int getRoteKarten(){
		return roteKarten;
	}
	public void setRoteKarten(int r){
		roteKarten=r;
	}
	public int getTorschuss(){
		return torschuss;
	}
	public void setTorschuss(int n){
		torschuss=n;
	}
	public int getMotivation(){
		return motivation;
	}
	public void setMotivation(int n){
		motivation=n;
		if(motivation>10)
			motivation=10;
		if(motivation<1)
			motivation=1;
	}
	public int getTore(){
		return tore;
	}
	public void setTore(int n){
		tore=n;
	}

	public void addTor(){
		tore++;
	}
	public int schiesstAufTor(){
		// Bestimmt die Staerke des Torschusses
		Random r=new Random();
		int ret=(int) Math.max(1, Math.min(10, torschuss+r.nextInt(4)-2+0.2*motivation));
		return ret;
	}
}
//by Niklas Götz

