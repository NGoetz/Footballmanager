package fussballmanager;
/**
 * Hauptklasse des Programms. Implementiert alle Funktionen des Spielverlaufs
 * 
 * @author Niklas Götz
 * @version 1.0
 */
import java.util.Random;

public class FussballFreundschaftsspiel implements Freundschaftsspiel {
	private String nameHeimMannschaft;
	private String nameGastMannschaft;
	private int punkteHeim;
	private int punkteGast;

	public FussballFreundschaftsspiel(){
		punkteHeim=0;
		punkteGast=0; 
	}


	public String getHeimMannschaft() {

		return nameHeimMannschaft;
	}


	public String getGastMannschaft() {

		return nameGastMannschaft;
	}


	public int getHeimPunkte() {

		return punkteHeim;
	}


	public int getGastPunkte() {

		return punkteGast;
	}


	public String getErgebnisText() {

		return "Das Spiel endete \n\n"+nameHeimMannschaft+" - "+nameGastMannschaft+" "+punkteHeim+":"+punkteGast+".";
	}

	public void starteSpiel(Mannschaft m1, Mannschaft m2){ //Hauptfunktion des Spielverlaufs
		nameHeimMannschaft= m1.getName();
		nameGastMannschaft=m2.getName();
		punkteHeim=0;
		punkteGast=0;
		for(int i=0;i<10;i++){ //Reduktion der abgelaufenen Roten Karten
			if(m1.getKader()[i].getRoteKarten()!=0)
				m1.getKader() [i].setRoteKarten(m1.getKader()[i].getRoteKarten()-1);
			if(m2.getKader()[i].getRoteKarten()!=0)
				m2.getKader() [i].setRoteKarten(m2.getKader()[i].getRoteKarten()-1);
		}
		Random r=new Random();
		System.out. println ( "------------------------------------------" ) ;
		System.out. println ( "Start des Spiels zwischen" ) ;
		System.out. println( ) ;
		System.out. println(m1. getName ( ) ) ;
		System.out. println ( " Trainer: "+m1. getTrainer ( ).getName ( ) ) ;
		System.out. println( ) ;
		System.out. println( " und" ) ;
		System.out. println( ) ;
		System.out. println(m2. getName ( ) ) ;
		System.out. println( " Trainer: "+m2.getTrainer ( ).getName ( ) ) ;
		System.out. println( "------------------------------------------" ) ;
		System.out.println("Kader von "+m1.getName()+":");
		System.out.println("1. :"+m1.getTorwart().getName());
		for(int i=0; i<10; i++){
			System.out.println(i+2 +". :"+m1.getKader()[i].getName());
		}
		System.out. println ( "------------------------------------------" ) ;
		System.out.println("Kader von "+m2.getName()+":");
		System.out.println("1. :"+m2.getTorwart().getName());
		for(int i=0; i<10; i++){
			System.out.println(i+2 +". :"+m2.getKader()[i].getName());
		}
		System.out. println ( "------------------------------------------" ) ;
		boolean spiellaeuft=true;
		int spieldauer=90+r.nextInt(5); //Bestimmung der Spieldauer
		int zeit=1;
		int naechsteAktion;


		while (spiellaeuft){
			naechsteAktion=r.nextInt(15)+1;  //Bestimmung wann nächste Aktion eintritt


			if(zeit+naechsteAktion>spieldauer){
				spiellaeuft=false;
				System.out.println();
				System.out.println("Der Schiri pfeift das Spiel ab");
				if(punkteHeim==punkteGast)
					System.out.println("Die Mannschaften waren sich ebenbürtig - es steht unentschieden");
				if(punkteHeim-punkteGast>2)
					System.out.println(m2.getName()+" wurde vernichtet.");
				if(punkteGast-punkteHeim>2)
					System.out.println(m1.getName()+" wurde vom Rasen gefegt.");
				if(m1.getMotivation()<=3)
					System.out.println("Die Spieler von "+m1.getName()+" verlassen deprimiert das Spielfeld");
				if(m1.getMotivation()>=7)
					System.out.println("Die Spieler von "+m1.getName()+" verlassen stolz die Arena");
				if(m2.getMotivation()<=3)
					System.out.println("Die Spieler von "+m2.getName()+" verlassen deprimiert das Spielfeld");
				if(m2.getMotivation()>=7)
					System.out.println("Die Spieler von "+m2.getName()+" verlassen stolz die Arena");
				break;
			}

			zeit=zeit+naechsteAktion;

			if(zeit>=90){
				System.out.println();
				System.out.println("Kurz vor Schluss wird es nochmal spannend");
			}
			zeit=aktion(m1,m2,zeit);

		}
	}

	public int aktion (Mannschaft m1, Mannschaft m2, int zeit){ //Entscheidungsfunktion, welche Aktion eintritt
		int bestimmer;
		Random r=new Random();
		bestimmer=r.nextInt(10);
		if(bestimmer<5){
			torschuss(m1,m2,zeit);
		}else if(bestimmer>4&&bestimmer<8)
		{
			foul(m1,m2,zeit);
		}else if (bestimmer==8)
		{
			System.out.println();
			System.out.println(zeit+". Minute: ");
			zeit=verletzt(m1,zeit);
			if(zeit==100){ //wenn verletzt die Zeit 100 ausgibt, ist eine Mannschaft nicht mehr spielfähig. Sie bekommt - um 
				//Unentschieden zu vermeiden, ein Tor zur Strafe abgezogen
				punkteHeim--;
				System.out.println("Aufgrund des Abbruchs bekommt die Mannschaft ein Tor abgezogen");
			}
		}else if (bestimmer==9){
			System.out.println();
			System.out.println(zeit+". Minute: ");
			zeit=verletzt(m2,zeit);
			if(zeit==100){
				punkteGast--;
				System.out.println("Aufgrund des Abbruchs bekommt die Mannschaft ein Tor abgezogen");
			}
		}
		return zeit;
	}
	public void torschuss (Mannschaft m1, Mannschaft m2, int zeit){
		Random r=new Random();
		//Bestimmung, welche Mannschaft die Torchance erhält
		float staerke_1=(float) (0.7f*m1.getStaerke()+0.2f*m1.getMotivation()+0.1f*m1.getTrainer().getErfahrung());
		float staerke_2=(float) (0.7f*m2.getStaerke()+0.2f*m2.getMotivation()+0.1f*m2.getTrainer().getErfahrung());
		int schuetze = r.nextInt(10);
		int beeinflusst=r.nextInt(10);  //Spieler, dessen Motivation vom Ergebnis beeinflusst wird
		int textbestimmer=r.nextInt(3); //Bestimmung des AusgabeTextes
		if ((r.nextInt(Math.round(staerke_1+staerke_2))-staerke_1)<0){ //Berechnung, welceh Staerke größer war
			Spieler s= m1.getKader()[schuetze];
			Spieler z=m1.getKader() [beeinflusst];
			if(s.getRoteKarten()==0){ //nur Spieler ohne Rote Karte können Tore schießen!
				if (torchance(s,m2.getTorwart(), zeit, m1)){  //Ergebnis der Torsituation
					punkteHeim++;
					if(punkteHeim==punkteGast)
						System.out.println("Ausgleich!");
					s.addTor();
					s.setMotivation(s.getMotivation()+3);  //Modifizierung der Motivation
					z.setMotivation(z.getMotivation()+2);
					if (textbestimmer==0)
						System.out.println("TOOOOOOR!!!!!   "+punkteHeim+":"+punkteGast+"  "+s.getName()+"(Dies ist sein "+s.getTore()+". Tor)");
					if (textbestimmer==1)
						System.out.println("Und er versenkt das Runde ins Eckige!!!   "
								+ ""+punkteHeim+":"+punkteGast+"  "+s.getName()+"(Dies ist sein "+s.getTore()+". Tor)");
					if (textbestimmer==2)
						System.out.println("Er trifft wie ein junger Gott!   "+punkteHeim+":"+punkteGast+"  "+s.getName()+"(Dies ist sein "+s.getTore()+". Tor)");
				}else{
					if(textbestimmer==0)
						System.out.println(m2.getTorwart().getName()+" pariert glanzvoll");
					if(textbestimmer==1)
						System.out.println("Leider steht "+s.getName()+" tief im Abseits");
					if (textbestimmer==2)
						System.out.println(m2.getTorwart().getName()+" stürmt auf ihn zu und wirft sich auf den Ball");
					s.setMotivation(s.getMotivation()-2); //Modifizierung der Motivation
					z.setMotivation(z.getMotivation()-1);
				}
			}else{
				System.out.println();
				//Wenn der zufällig ausgewählte Spieler eine Rote Karte hat (=nicht auf dem Spielfeld steht) erhält die Mannschaft 
				//keine Torchance, da sie durch den Spielermangel geschwächt ist
				System.out.println("Die roten Karten machen "+m1.getName()+" große Probleme");
			}
		}
		else{ //gleiches Verfahren für Mannschaft 2
			Spieler s= m2.getKader()[schuetze];
			Spieler z=m2.getKader()[beeinflusst];
			if(s.getRoteKarten()==0){
				if (torchance(s,m1.getTorwart(), zeit, m2)){
					punkteGast++;
					if(punkteHeim==punkteGast)
						System.out.println("Ausgleich!");
					s.addTor();
					s.setMotivation(s.getMotivation()+3);
					z.setMotivation(z.getMotivation()+2);
					if (textbestimmer==0)
						System.out.println("TOOOOOOR!!!!!   "+punkteHeim+":"+punkteGast+"  "+s.getName()+"(Dies ist sein "+s.getTore()+". Tor)");
					if (textbestimmer==1)
						System.out.println("Und er versenkt das Runde ins Eckige!!!   "
								+ ""+punkteHeim+":"+punkteGast+"  "+s.getName()+"(Dies ist sein "+s.getTore()+". Tor)");
					if (textbestimmer==2)
						System.out.println("Er trifft wie ein junger Gott!   "+punkteHeim+":"+punkteGast+"  "+s.getName()+"(Dies ist sein "+s.getTore()+". Tor)");
				}else{
					if(textbestimmer==0)
						System.out.println(m1.getTorwart().getName()+" pariert glanzvoll");
					if(textbestimmer==1)
						System.out.println("Leider steht "+s.getName()+" tief im Abseits");
					if (textbestimmer==2)
						System.out.println(m1.getTorwart().getName()+" stürmt auf ihn zu und wirft sich auf den Ball");
					s.setMotivation(s.getMotivation()-2);
					z.setMotivation(z.getMotivation()-1);
				}
			}else{
				System.out.println();
				System.out.println("Die roten Karten machen "+m2.getName()+" große Probleme");
			}
		}
	}


	public void foul (Mannschaft m1, Mannschaft m2, int zeit){
		Random r=new Random();
		int betrueger=r.nextInt(10); //Spieler der foult
		int beeinflusst=r.nextInt(10); //Spieler dessen Motivation beeinflusst wird
		int entscheidung=r.nextInt(7); //Entscheidung des Schiris
		int verletzung=r.nextInt(5); //Bestimmung ob das Foul jemanden verletzt hat
		int textbestimmer=r.nextInt(3); //Wahl des Ausgabetexts
		if((r.nextInt(Math.round((float)m1.getMotivation()+(float)m2.getMotivation()))-m1.getMotivation())<0){ 
			//Foulwahrscheinlichkeit abhängig von Motivation der Spieler
			Spieler s=m1.getKader() [betrueger];
			Spieler z=m1.getKader()[beeinflusst];
			Spieler k=m2.getKader() [beeinflusst];
			if(s.getRoteKarten()==0){ //nur Spieler ohne Rote Karten können foulen!
				System.out.println();
				System.out.println(zeit+". Minute: ");
				System.out.println(s.getName()+" vom "+m1.getName()+" foult!");
				if( entscheidung==0){
					if(textbestimmer==0)
						System.out.println("Der Schiri hats nicht gesehen!");
					if (textbestimmer==1)
						System.out.println("Der Schiri war gerade mit einer Bratwurst beschäftigt");
					if (textbestimmer==2)
						System.out.println("Der Schiri übersieht das wohlwollend");
				}

				else if (entscheidung==1||entscheidung==2||entscheidung==3)
				{
					if(verletzung<1){
						if (textbestimmer==0)
							System.out.println("Er hat einen gegnerischen Spieler ein Bein gestellt!");
						if (textbestimmer==1)
							System.out.println("Er hat einen anderen Spieler umgeworfen!");
						if (textbestimmer==2)
							System.out.println("Er hat einem anderen Spieler überrannt!");
						verletzt(m2, zeit); //Aufruf der Verletzungsfunktion
					}
					s.setGelbeKarten(s.getGelbeKarten()+1); //Spieler erhält gelbe Karte und verliert Motivation
					s.setMotivation(s.getMotivation()-1);
					System.out.println("Der Schiedsrichter kennt keine Gnade und gibt "+s.getName()+" eine gelbe Karte. "
							+s.getName()+" hat nun "+s.getGelbeKarten()+" gelbe Karten.");
					if(s.getGelbeKarten()==3){ //Spieler fliegt wegen zu vielen gelben Karten raus!
						s.setGelbeKarten(0);
						s.setRoteKarten(2);
						s.setMotivation(s.getMotivation()-1);
						z.setMotivation(z.getMotivation()-1);
						System.out.println("Wegen zu vieler Fouls muss "+s.getName()+" das Spiel verlassen");
					}
				}else if (entscheidung==4||entscheidung==5){
					//Bei Freistoß wird eine Torsituation ausgelöst - Implementierung analog zu oben
					System.out.println("Der Schiedsrichter vergibt einen Freistoss für "+m2.getName());
					int schuetze=r.nextInt(10);
					Spieler sp= m2.getKader()[schuetze];
					if (torchance(sp,m1.getTorwart(), zeit, m2)){
						punkteGast++;
						if(punkteHeim==punkteGast)
							System.out.println("Ausgleich!");
						sp.addTor();
						sp.setMotivation(s.getMotivation()+3);
						k.setMotivation(z.getMotivation()+2);
						System.out.println("Der Torwart hatte keine Chance gegen diesen Freistoss! "
								+punkteHeim+":"+punkteGast+"  "+sp.getName()+"(Dies ist sein "+sp.getTore()+". Tor)");
					}else{
						System.out.println(m1.getTorwart().getName()+" hält den Freistoss gekonnt");
						sp.setMotivation(s.getMotivation()-2);
						k.setMotivation(z.getMotivation()-1);
					}
				}else if(entscheidung==6){
					if(verletzung<2){
						System.out.println("Er hat einen gegnerischen Spieler brutalst umgemäht!");
						verletzt(m2, zeit);
					}
					s.setRoteKarten(2);
					s.setMotivation(s.getMotivation()-2);
					z.setMotivation(z.getMotivation()-1);
					System.out.println("Der Schiedsrichter ist stinksauer und gibt "+s.getName()+" eine rote Karte für diese Untat");
				}
			}
		}else{//Analoges für Mannschaft 2
			Spieler s=m2.getKader() [betrueger];
			Spieler z=m2.getKader()[beeinflusst];
			Spieler k=m1.getKader()[beeinflusst];
			if(s.getRoteKarten()==0){
				System.out.println();
				System.out.println(zeit+". Minute: ");
				System.out.println(s.getName()+" vom "+m2.getName()+" foult!");
				if(entscheidung==0)
					System.out.println("Der Schiri hats nicht gesehen!");
				else if (entscheidung==1||entscheidung==2||entscheidung==3)
				{
					if(verletzung<1){
						if (textbestimmer==0)
							System.out.println("Er hat einen gegnerischen Spieler ein Bein gestellt!");
						if (textbestimmer==1)
							System.out.println("Er hat einen anderen Spieler umgeworfen!");
						if (textbestimmer==2)
							System.out.println("Er hat einem anderen Spieler überrannt!");
						verletzt(m1, zeit);
					}
					s.setGelbeKarten(s.getGelbeKarten()+1);
					s.setMotivation(s.getMotivation()-1);
					System.out.println("Der Schiedsrichter kennt keine Gnade und gibt "+s.getName()+" eine gelbe Karte. "
							+s.getName()+" hat nun "+s.getGelbeKarten()+" gelbe Karten.");
					if(s.getGelbeKarten()==3){
						s.setGelbeKarten(0);
						s.setRoteKarten(2);
						s.setMotivation(s.getMotivation()-1);
						z.setMotivation(z.getMotivation()-1);
						System.out.println("Wegen zu vieler Fouls muss "+s.getName()+" das Spiel verlassen");
					}
				}else if (entscheidung==4||entscheidung==5){
					System.out.println("Der Schiedsrichter vergibt einen Freistoss für "+m1.getName());
					int schuetze=r.nextInt(10);
					Spieler sp= m1.getKader()[schuetze];
					if (torchance(sp,m2.getTorwart(), zeit, m1)){
						punkteHeim++;
						if(punkteHeim==punkteGast)
							System.out.println("Ausgleich!");
						sp.addTor();
						sp.setMotivation(s.getMotivation()+3);
						k.setMotivation(k.getMotivation()+2);;
						System.out.println("Der Torwart hatte keine Chance gegen diesen Freistoss! "
								+punkteHeim+":"+punkteGast+"  "+sp.getName()+"(Dies ist sein "+sp.getTore()+". Tor)");
					}else{
						System.out.println(m2.getTorwart().getName()+" hält den Freistoss gekonnt");
						sp.setMotivation(s.getMotivation()-2);
						k.setMotivation(k.getMotivation()-1);}
				}else if(entscheidung==6){
					if(verletzung<2){
						System.out.println("Er hat einen gegnerischen Spieler brutalst umgemäht!");
						verletzt(m1, zeit);
					}
					s.setRoteKarten(2);
					s.setMotivation(s.getMotivation()-2);
					z.setMotivation(z.getMotivation()-1);
					System.out.println("Der Schiedsrichter ist stinksauer und gibt "+s.getName()+" eine rote Karte für diese Untat");
				}

			}
		}
	}
	public int verletzt ( Mannschaft m, int zeit){
		Random r=new Random();
		int bestimmerwer=r.nextInt(10); //Wer wird verletzt?
		int bestimmerschwere=r.nextInt(10); //Wie schwer?
		int beeinflusst=r.nextInt(10); //Motivationsbeeinlflussung
		int textbestimmer=r.nextInt(3);
		Spieler s=m.getKader()[bestimmerwer];
		Spieler b=m.getKader()[beeinflusst];
		if (textbestimmer==0)
			System.out.println(s.getName()+" vom "+m.getName()+" geht mit schmerzverzerrtem Gesicht zu Boden");
		if (textbestimmer==1)
			System.out.println(s.getName()+" vom "+m.getName()+" gibt einen grauenhaften Schrei von sich");
		if (textbestimmer==2)
			System.out.println(s.getName()+" vom "+m.getName()+" bricht verwundet zusammen");
		if(bestimmerschwere>1){
			s.setStaerke(s.getStaerke()-1); //Verlust von Staerke durch Verwundung
			s.setMotivation(s.getMotivation()-2);
			b.setMotivation(b.getMotivation()-1);
			if (textbestimmer==0)
				System.out.println(s.getName()+" tackert sich die Wunde zu und spielt weiter");
			if(textbestimmer==1)
				System.out.println(s.getName()+" humpelt weiter");
			if(textbestimmer==2)
				System.out.println(s.getName()+" will trotz einer blutenden Wunde weiterspielen");
		}else{
			if (m.getVerletzt()+1>3){ //Mannschaft hat keine Ersatzspieler mehr zum Wechseln
				System.out.println("Aufgrund zu vieler Verletzter beim "+m.getName()+" wird das Spiel abgebrochen.");
				zeit=100;
			}
			if(textbestimmer==0)
				System.out.println(s.getName()+" muss aufgrund eines schweren Schienbeinbruchs das Spiel verlassen ");
			if(textbestimmer==1)
				System.out.println(s.getName()+" wird aufgrund eines Bänderrisses ausgewechselt");
			if(textbestimmer==2)
				System.out.println(s.getName()+" muss sich aufgrund einer Knöchelfraktur auf eine lange Pause einstellen");
			Spieler Wechsel=m.getKader()[bestimmerwer]; //Spieler wird durch Spieler aus Ersatzkader ersetzt
			Spieler e= m.getKader()[bestimmerwer]=m.getErsatz()[m.getVerletzt()];
			System.out.println(e.getName()+" nimmt seinen Platz ein");
			m.getErsatz()[m.getVerletzt()]=Wechsel;
			m.setVerletzt(m.getVerletzt()+1);
			b.setMotivation(b.getMotivation()-2);

		}

		return zeit;
	}
	public boolean torchance (Spieler s, Torwart t, int Zeit, Mannschaft m){ //Auswertung und Ausgabe der Torschusssituation
		boolean tor=!t.haeltDenSchuss(s.schiesstAufTor()); //Uebernhame der Information, ob der Torschuss erfolgreich war
		Random r=new Random();
		int textbestimmer=r.nextInt(3); //Bestimmung des Ausgabetextes
		if(!tor) //Veraenderung der Motivation bei erfolgreichem/fehlgeschlagenem Abfang des Balls
			t.setMotivation(t.getMotivation()+1);
		else
			t.setMotivation(t.getMotivation()-1);
		System.out.println();
		System.out.println(Zeit+". Minute: ");
		System.out.println("Chance fuer "+m.getName()+"...");
		if (textbestimmer==0)
			System.out.println(s.getName()+" bereitet sich auf einen Schuss aufs Tor vor.");
		if (textbestimmer==1)
			System.out.println(s.getName()+" zielt auf das Tor");
		if (textbestimmer==2)
			System.out.println(s.getName()+" durchbricht die Verteidigung und stürmt auf das Tor");
		return tor;
	}
}
//by Niklas Götz