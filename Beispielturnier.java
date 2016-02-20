package fussballmanager;
/**
 * 
 * 
 *Eine Beispielanwendung des Programms für ein einfaches Spiel
 *Zwei Mannschaftsdatein werden eingelesen und das Spiel @see FussballFreundschaftsspiel gestartet
 *Bei Fehlen der Mannschaftsdateien gibt es eine Exception
 *
 *@author Niklas Götz
 *@version 1.0
 */
import java.io.FileNotFoundException;

public class Beispielturnier {

	public static void main(String[] args) throws FileNotFoundException {
		Mannschaft m1 = Reader.ErzeugeMannschaft("m1.dat"); //Einlesen der Daten
		Mannschaft m2 = Reader.ErzeugeMannschaft("m2.dat"); 
		Mannschaft m3 = Reader.ErzeugeMannschaft("m3.dat");
		Mannschaft m4 = Reader.ErzeugeMannschaft("m4.dat");
		FussballTurnier ft=new FussballTurnier(m1,m2,m3,m4); //Aufruf des Turnierkonstrukters
		ft.richteAus(); //Starten des Turniers

	}

}
//by Niklas Götz