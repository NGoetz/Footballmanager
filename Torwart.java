package fussballmanager;
import java.util.Random;
public class Torwart extends Spieler {
	private int reaktion;
	public Torwart (String n, int a,int s, int ts, int m, int r, int k, int rt){
		super(n,a,s,ts,m,k, rt);
		reaktion=r;
	}
	public int getReaktion(){
		return reaktion;
	}
	public void setReaktion (int n){
		reaktion=n;
	}
	public boolean haeltDenSchuss (int torschuss){
		// Bestimmt bei gegebener Staerke des Torschusses, ob der Ball gehalten wird
		Random r=new Random();
		double ret=reaktion+r.nextInt(3)-1+0.2*getMotivation();
		if (ret>=torschuss) return true;
		else return false;
	}
}
//by Niklas Götz