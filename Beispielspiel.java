package fussballmanager;
/**
 *Eine Beispielanwendung des Programms für ein einfaches Spiel
 *Zwei Mannschaftsdatein werden eingelesen und das Spiel @see FussballFreundschaftsspiel gestartet
 *Bei Fehlen der Mannschaftsdateien gibt es eine Exception
 *
 *@author Niklas Götz
 *@version 1.0
 */
import java.io.FileNotFoundException;


public class Beispielspiel {
	public static void main ( String [ ] args ) throws FileNotFoundException{


		Mannschaft m1 = Reader.ErzeugeMannschaft("m1.dat"); //Einlesen der Daten
		Mannschaft m2 = Reader.ErzeugeMannschaft("m2.dat");
		FussballFreundschaftsspiel f1 = new FussballFreundschaftsspiel ( );


		f1.starteSpiel (m1 , m2 ) ; //Start des Spiels

		System.out. println ( ) ;
		System.out. println( "------------------------------------------" ) ;
		System.out. println ( f1.getErgebnisText ( ) ) ; //Ausgabe des Ergebnisses
		System.out. println ( "------------------------------------------" ) ;
	}
}
//by Niklas Götz