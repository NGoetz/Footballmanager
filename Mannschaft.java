package fussballmanager;

public class Mannschaft {
	private String name;
	private Trainer trainer;
	private Torwart torwart;
	private Spieler[] kader;
	private Spieler[] ersatz;
	private int verletzt;
	public Mannschaft (String n, Trainer t, Torwart to, Spieler[] k, Spieler[] e){
		name=n;
		trainer=t;
		torwart=to;
		kader=k;
		verletzt=0; //Anzahl der Verletzen entscheidet über Spielfähigkeit
		ersatz=e; //ersatzkader
	}
	public int getVerletzt(){
		return verletzt;
	}
	public void setVerletzt(int  v){
		verletzt=v;
	}
	public String getName(){
		return name;
	}
	public void setName(String n){
		name=n;
	}
	public Trainer getTrainer(){
		return trainer;
	}
	public void setTrainer(Trainer t){
		trainer=t;
	}
	public Torwart getTorwart(){
		return torwart;
	}
	public void setTorwart(Torwart to){
		torwart=to;
	}
	public Spieler[] getKader(){
		return kader;
	}
	public void setKader(Spieler [] k){
		kader=k;
	}
	public Spieler[] getErsatz(){
		return ersatz;
	}
	public void setErsatz(Spieler [] k){
		ersatz=k;
	}
	public double getStaerke(){ //berechnet die durchschnittliche Staerke aller Spieler, die aktiv auf dem Feld sind (keine Rote Karte!)
		int summ=torwart.getStaerke();
		for (int i=0; i<10; i++){
			if(kader[i].getRoteKarten()==0)
				summ+=kader[i].getStaerke();
		}
		return (double) summ/11; //durch elf, damit durch Rote Karten geschwächte Mannschaften benachteiligt werden
	}
	public double getMotivation(){ //analog
		int summ=torwart.getMotivation();
		for (int i=0; i<10; i++){
			if(kader[i].getRoteKarten()==0)
				summ+=kader[i].getMotivation();
		}
		return (double) summ/11;
	}
}
//by Niklas Götz