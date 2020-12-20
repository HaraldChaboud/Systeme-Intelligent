from numpy import *
from Perceptron import *

if __name__ == "__main__":
    Inputs=array([[0,0],[0,1],[1,0],[1,1]])
    Outputs=array([0,0,0,1])
    Perceptron=Perceptron(3,10,0.1)
    Perceptron.entrainement(Inputs,Outputs)
    #Possibilité de charger les poids avec la fonction -> P.chargerPoids()
    result=zeros(4)

    #On stocke la valeur des sorties dans un tableau pour le comparer à la sortie voulue.
    for i in range(4):
        result[i]=Perceptron.prediction(Inputs[i][0],Inputs[i][1])
    
    #Comparaison du résultat obtenu et attendu
    comparison=result == Outputs
    print(result)
    #affichage de la réussite ou de l'échec de l'entrainement
    if comparison.all():
 
        print("Entrainement réussi")
    else:
        print("Echec de l'entrainement")
