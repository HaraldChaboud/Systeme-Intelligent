
package echiquier;



public class Echiquier {
	
private Cellule[][] echiquier;
private int taille;
private int [][]comptereine;

public Echiquier(int taille) {
	this.taille=taille;
	echiquier=new Cellule[taille][taille];
	comptereine=new int[taille][taille];
	initialiserEchiquier();
}
public void initialiserEchiquier() {
	for(int x=0;x<taille;x++) {
		for(int y=0;y<taille; y++) {
			echiquier[x][y]=new Cellule(x,y);
		}
	}
}

public void modifierCellule(int x, int y, int valeur) {
	echiquier[x][y].setTypeOccupation(valeur);
}
public void dispEchiquier() {
	 for (int i = 0; i < taille; i++) {
		 for (int j = 0; j < taille; j++) {
			 System.out.print(echiquier[i][j].getTypeOccupation()+" ");
		 }
		 System.out.print("\n");
	 }
         
}
public void dispCompte() {
	 for (int i = 0; i < taille; i++) {
		 for (int j = 0; j < taille; j++) {
			 System.out.print(comptereine[i][j]+" ");
		 }
		 System.out.print("\n");
	 }
        
}
public void placeReine(int x, int y) {
	boolean edge=false;
	int temp1=x,temp2=y;
	if(echiquier[x][y].getTypeOccupation()==0) {
		echiquier[x][y].setTypeOccupation(1);
		for(int i=0;i<taille;i++) {
			if(echiquier[i][y].getTypeOccupation()==0) {
				echiquier[i][y].setTypeOccupation(2);
			}
			if(echiquier[x][i].getTypeOccupation()==0) {
				echiquier[x][i].setTypeOccupation(2);
			}
		}
		while(!edge) {
			if(temp1<8 && temp1>=0 && temp2<8 && temp2>=0)
			{if(echiquier[temp1][temp2].getTypeOccupation()==0) {
				echiquier[temp1][temp2].setTypeOccupation(2);
			}}else {edge=true;}
			temp1++;temp2++;
		}
		edge=false;
		temp1=x;temp2=y;
		while(!edge) {
			if(temp1<8 && temp1>=0 && temp2<8 && temp2>=0)
			{if(echiquier[temp1][temp2].getTypeOccupation()==0) {
				echiquier[temp1][temp2].setTypeOccupation(2);
			}}else {edge=true;}
			temp1--;temp2++;
		}
		edge=false;
		temp1=x;temp2=y;
		while(!edge) {
			if(temp1<8 && temp1>=0 && temp2<8 && temp2>=0)
			{if(echiquier[temp1][temp2].getTypeOccupation()==0) {
				echiquier[temp1][temp2].setTypeOccupation(2);
			}}else {edge=true;}
			temp1--;temp2--;
		}
		edge=false;
		temp1=x;temp2=y;
		while(!edge) {
			if(temp1<8 && temp1>=0 && temp2<8 && temp2>=0)
			{if(echiquier[temp1][temp2].getTypeOccupation()==0) {
				echiquier[temp1][temp2].setTypeOccupation(2);
			}}else {edge=true;}
			temp1++;temp2--;
		}
	}
}
public int testReine(int x,int y) {
	int nbreine=0;
	boolean edge=false;
	int temp1=x,temp2=y;
	if(echiquier[x][y].getTypeOccupation()==0) {
		for(int i=0;i<taille;i++) {
			if(echiquier[i][y].getTypeOccupation()==0) {
				nbreine++;
			}
			if(echiquier[x][i].getTypeOccupation()==0) {
				nbreine++;
			}
		}
		while(!edge) {
			if(temp1<8 && temp1>=0 && temp2<8 && temp2>=0)
			{if(echiquier[temp1][temp2].getTypeOccupation()==0 ) {
				nbreine++;
			}}else {edge=true;}
			temp1++;temp2++;
		}
		edge=false;
		temp1=x;temp2=y;
		while(!edge) {
			if(temp1<8 && temp1>=0 && temp2<8 && temp2>=0)
			{if(echiquier[temp1][temp2].getTypeOccupation()==0) {
				nbreine++;
			}}else {edge=true;}
			temp1--;temp2++;
		}
		edge=false;
		temp1=x;temp2=y;
		while(!edge) {
			if(temp1<8 && temp1>=0 && temp2<8 && temp2>=0)
			{if(echiquier[temp1][temp2].getTypeOccupation()==0) {
				nbreine++;
			}}else {edge=true;}
			temp1--;temp2--;
		}
		edge=false;
		temp1=x;temp2=y;
		while(!edge) {
			if(temp1<8 && temp1>=0 && temp2<8 && temp2>=0)
			{if(echiquier[temp1][temp2].getTypeOccupation()==0) {
				nbreine++;
			}}else {edge=true;}
			temp1++;temp2--;
		}
	}
	if(nbreine>5) {
	nbreine=nbreine-6;}//Prend en compte les positions éxistante de la reine
	return nbreine;
}
public void remplirCompteReine() {
	for(int i=0;i<taille;i++) {
		for(int j=0;j<taille;j++) {
			comptereine[i][j]=testReine(i, j);
		}
	}
}
public void placement() {
	boolean fini=false;
	int temp1=0,temp2=0,cpt=100;
	while(!fini) {
	remplirCompteReine();
    dispCompte();
    for(int i=0;i<taille;i++)
    {
    	for(int j=0;j<taille;j++) 
    	{
    		if(comptereine[i][j]<cpt && comptereine[i][j]>0)
    		{cpt=comptereine[i][j];temp1=i;temp2=j;}
    	}
    }
    placeReine(temp1, temp2);
    System.out.println("--------");
    dispEchiquier();
    System.out.println("--------");
    cpt=100;
    fini=true;
    for(int i=0;i<taille;i++)
    {
    	for(int j=0;j<taille;j++) 
    	{
    		if(echiquier[i][j].getTypeOccupation()==0){fini=false;}
    	}
    }
}
	
}

}
