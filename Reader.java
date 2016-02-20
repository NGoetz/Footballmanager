package fussballmanager;

import java.io.*;
import java.util.Scanner;

public class Reader {


	public static Mannschaft ErzeugeMannschaft(String s) throws FileNotFoundException
	{ 
		// gibt pro Datei eine Mannschaft aus
		//unbedingt das Format der Beispieldatein übernehmen!!
		Scanner scanner = new Scanner( new File(s) );
		String name=scanner.next()+" "+scanner.next();
		//Parsen der Daten entsprechend der Konstruktoren
		Trainer t=new Trainer(scanner.next()+" "+scanner.next(), scanner.nextInt(),scanner.nextInt());
		Torwart tw=new Torwart(scanner.next()+" "+scanner.next(), scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
		Spieler []kader=new Spieler [10];
		for(int i=0;i<10;i++){
			kader [i]=new Spieler(scanner.next()+" "+scanner.next(), scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
		}
		Spieler []ersatz=new Spieler[3];
		for(int i=0;i<3;i++){
			ersatz [i]=new Spieler(scanner.next()+" "+scanner.next(), scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
		}

		scanner.close();
		Mannschaft m=new Mannschaft(name,t,tw,kader,ersatz);
		return m;
	}
}
//by Niklas Götz