from random import *
import numpy as numpy


class Perceptron:
    def __init__(self,nbInputs,epochs,learningRate):
        self.nbInputs = nbInputs 
        self.epochs = epochs
        self.learningRate = learningRate
        self.Poids = numpy.zeros(nbInputs)
        
    #On predit le resultat des deux entrées
    def prediction(self,Input1,Input2):
        result = self.Poids[0]*1 + self.Poids[1]*Input1 + self.Poids[2]*Input2

        #Fonction d'activation
        if result <= 0:
            return 0
        else:
            return 1


    #Entrainement du perceptron
    def entrainement(self,inputs,expectedOutputs):
        for k in range(0,self.epochs,1):
            for i in range(0,len(inputs),1):
                output = self.prediction(inputs[i][0], inputs[i][1])
                

                #Calcul de l'erreur
                erreur = 0.5*((output-expectedOutputs[i])**2)
             
                #on ajuste les valeurs
                for numPoids in range(1,self.nbInputs,1):
                    self.Poids[numPoids] = self.Poids[numPoids] + self.learningRate * ( expectedOutputs[i] - output ) * inputs[i][numPoids-1]


                #Loi de Widrow-Hoff
                self.Poids[0] = self.Poids[0] + self.learningRate * ( expectedOutputs[i] - output  )



                #Affichage de l'itération actuelle
                print("Epoch n°" + str(k) + " Valeur prédite " + str(output) + " Erreur " + str(erreur))
                print("Poids0  " + str(self.Poids[0]) + " Poids1  " + str(self.Poids[1]) + "  Poids2  " + str(self.Poids[2])) 


        #Ecriture dans le CSV       
        f= open('PoidsPerceptron.csv',"w")
        f.write(str(self.Poids[0]) + "\n" + str(self.Poids[1]) + "\n" + str(self.Poids[2]))
        f.close()


    #Fonction de chargement des poids
    def chargerPoids(self):
        f= open('poidsPerceptron.csv',"r")
        a = f.readlines()
        self.Poids[0] = a[0]
        self.Poids[1] = a[1]
        self.Poids[2] = a[2]
        f.close()
                 
