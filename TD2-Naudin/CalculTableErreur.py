from numpy import *
import matplotlib.pyplot as plot

if __name__ == "__main__":
    Input=array([[0,0],[0,1],[1,0],[1,1]])
    ExpectedOutput=array([0,0,0,1])
    TabErreur=zeros((11,11))


    #on va parcourir les poids
    for Poids0 in range(0,11):
        for Poids1 in range(0,11):
            listeErreur=[]
            ErreurTot = 0

            #On va tester la sortie avec les poids actuels
            for i in range(0,4):
                output=(Poids0-5)*Input[i][0] + (Poids1-5)*Input[i][1]
                
                
                #fonction d'activation
                if output<=0 :
                    output=0
                else:
                    output=1


                #Calcul de l'erreur
                listeErreur.append(0.5*(output-ExpectedOutput[i])*(output-ExpectedOutput[i]))

            #Ajoute les quatres erreurs ensemble
            for temp in listeErreur:
                ErreurTot += temp

            #On place l'erreur dans le tableau final
            TabErreur[Poids0][Poids1] = ErreurTot

    print(TabErreur)
    plot.imshow(TabErreur)
    plot.show()