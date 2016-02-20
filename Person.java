package fussballmanager;

public class Person {

	private String name;
	private int alter;
	public Person (String n, int a){
		name=n;
		alter=a;
	}

	public String getName(){
		return name;
	}
	public void setName(String n){
		name=n;
	}
	public int getAlter(){
		return alter;
	}
	public void setAlter(int n){
		alter=n;
	}
}
//by Niklas Götz