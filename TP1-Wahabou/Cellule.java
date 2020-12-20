package echiquier;

public class Cellule {
private int x;
private int y;
private int typeOccupation;

final static int LIBRE = 0;
final static int REINE = 0;
final static int MENACEE = 0;
public Cellule (int x,int y) {
	this.x=x;
	this.y=y;
	this.typeOccupation = LIBRE;
}

public void setTypeOccupation(int valeur) {
	if(valeur<3 && valeur>=0) {
	this.typeOccupation=valeur;}
}

public int getTypeOccupation() {
	return this.typeOccupation;
}


}