package fussballmanager;

public class FussballTurnier implements Turnier{
	private Mannschaft m1;
	private Mannschaft m2;
	private Mannschaft m3;
	private Mannschaft m4;

	public FussballTurnier(Mannschaft mann1, Mannschaft mann2,Mannschaft mann3,Mannschaft mann4){
		m1=mann1;
		m2=mann2;
		m3=mann3;
		m4=mann4;
	}

	public void richteAus (){
		System.out.println("Das große Turnier zwischen den Mannschaften "+m1.getName()+", "+m2.getName()+", "
				+m3.getName()+" und "+m4.getName()+" hat begonnen!");
		FussballFreundschaftsspiel f1 = new FussballFreundschaftsspiel ( );
		System.out.println("Das Duell zwischen "+m1.getName()+" und "+m2.getName());
		int k =0; //Zähler wie viele Spiele es schon gab
		while(f1.getHeimPunkte()==f1.getGastPunkte()){

			if (k>0) //Keine Ausgabe dieses Textes am Anfang, da Punkte zwar gleich, aber noch kein Spiel stattgefunden hat
				System.out.println("Aufgrund des Unentschiedens gibt es ein Rückspiel");
			k++;


			f1.starteSpiel (m1 , m2 ) ; //Start eines einzelnen Spiels

			System.out. println ( ) ;
			System.out. println( "------------------------------------------" ) ;
			System.out. println ( f1.getErgebnisText ( ) ) ;
			System.out. println ( "------------------------------------------" ) ;
		}

		FussballFreundschaftsspiel f2 = new FussballFreundschaftsspiel ( );
		System.out.println("Das Duell zwischen "+m3.getName()+" und "+m4.getName());
		k =0;
		while(f2.getHeimPunkte()==f2.getGastPunkte()){

			if (k>0)
				System.out.println("Aufgrund des Unentschiedens gibt es ein Rückspiel");
			k++;


			f2.starteSpiel (m3 , m4 ) ;

			System.out. println ( ) ;
			System.out. println( "------------------------------------------" ) ;
			System.out. println ( f2.getErgebnisText ( ) ) ;
			System.out. println ( "------------------------------------------" ) ;
		}
		Mannschaft mfinale1;
		Mannschaft mfinale2;
		if(f1.getHeimPunkte()>f1.getGastPunkte()){
			mfinale1=m1; //Bestimmung der Finalisten
		}else{
			mfinale1=m2;
		}
		if(f2.getHeimPunkte()>f2.getGastPunkte()){
			mfinale2=m3;
		}else{
			mfinale2=m4;
		}
		FussballFreundschaftsspiel f3 = new FussballFreundschaftsspiel ( );
		System.out.println("Das Finale zwischen "+mfinale1.getName()+" und "+mfinale2.getName());
		k =0;
		while(f3.getHeimPunkte()==f3.getGastPunkte()){

			if (k>0)
				System.out.println("Aufgrund des Unentschiedens gibt es ein Rückspiel");
			k++;


			f3.starteSpiel (mfinale1 , mfinale2 ) ;

			System.out. println ( ) ;
			System.out. println( "------------------------------------------" ) ;
			System.out. println ( f3.getErgebnisText ( ) ) ;
			System.out. println ( "------------------------------------------" ) ;
		}
		if(f3.getHeimPunkte()>f3.getGastPunkte())
			System.out.println(mfinale1.getName()+" hat das Turnier gewonnen!");
		else
			System.out.println(mfinale2.getName()+" hat das Turnier gewonnen!");
	}
}
//by Niklas Götz