import keras.backend as Keras
from keras.models import Sequential
from keras.layers import Dense
from keras.optimizers import SGD
from keras import utils
from numpy import *

#Définition des tables de base pour l'entrée et la sortie
inputs = zeros((10,7))
outputs = zeros((10,10))




if __name__ == "__main__":
    #Tableau d'apprentissage que nous allons sauvegarder dans un CSV
    inputs[0] = [1,1,1,1,1,1,0]
    inputs[1] = [0,1,1,0,0,0,0]
    inputs[2] = [1,1,0,1,1,0,1]
    inputs[3] = [1,1,1,1,0,0,1]
    inputs[4] = [0,1,1,0,0,1,1]
    inputs[5] = [1,0,1,1,0,1,1]
    inputs[6] = [1,0,1,1,1,1,0]
    inputs[7] = [1,1,1,0,0,0,0]
    inputs[8] = [1,1,1,1,1,1,1]
    inputs[9] = [1,1,1,1,0,1,1]
    #Tableau 'teaching' que nous allons également enregistrer dans un CSV
    outputs[0] = [1,0,0,0,0,0,0,0,0,0]
    outputs[1] = [0,1,0,0,0,0,0,0,0,0]
    outputs[2] = [0,0,1,0,0,0,0,0,0,0]
    outputs[3] = [0,0,0,1,0,0,0,0,0,0]
    outputs[4] = [0,0,0,0,1,0,0,0,0,0]
    outputs[5] = [0,0,0,0,0,1,0,0,0,0]
    outputs[6] = [0,0,0,0,0,0,1,0,0,0]
    outputs[7] = [0,0,0,0,0,0,0,1,0,0]
    outputs[8] = [0,0,0,0,0,0,0,0,1,0]
    outputs[9] = [0,0,0,0,0,0,0,0,0,1]


    #Fichier CSV dans lequel on va mettre quels segments on attend pour chaque chiffre
    f= open("trainingdata.csv","w")
    for i in range(0,len(inputs)):
        for j in range(0, len(inputs[i])):
            f.write(str(inputs[i][j]) + " ")
        f.write("\n")
    f.close()
    #Fichier CSV dans lequel on va mettre quel répartition des probabilités on va attendre pour chaque chiffre
    f= open("teachingdata.csv","w")
    for i in range(0,len(outputs)):
        for j in range(0, len(outputs[i])):
            f.write(str(outputs[i][j])  + " ")
        f.write("\n")
    f.close()
    #Demandé dans l'énoncé sauf que l'on a 10 chiffres et pas 9 donc on doit changer la valeur
    inputs = reshape(inputs,(10,1,7))
    outputs = reshape(outputs,(10,1,10))

    #Paramètre pour le learning rate
    sgd = SGD(lr=0.01) 
    #Modèle séquentiel
    model = Sequential()
    #Ajout de nos quatres couches de neurones avec le bon nombre d'inputs
    model.add(Dense(10, input_dim = 7, activation='relu'))
    model.add(Dense(64, input_dim = 10, activation='relu'))
    model.add(Dense(64, input_dim = 64, activation='relu'))
    model.add(Dense(10, input_dim = 64, activation='softmax'))
    model.summary()
    model.compile(optimizer=sgd, loss='categorical_crossentropy')
    #On va mettre le nombre d'épochs à 10000 pour essayer d'avoir une bonne précision sur nos données
    model.fit(inputs, outputs, epochs=10000)

    #nos 5 tests avec des valeurs légèrement faussées sur des valeurs de 7 segments
    print(model.predict([[[0,1,0,0,0,0,0]]]))
    print(model.predict([[[1,1,1,1,1,0,1]]]))
    print(model.predict([[[1,1,1,0,0,1,1]]]))
    print(model.predict([[[1,0,0,1,1,1,1]]]))
    print(model.predict([[[0,1,1,1,1,1,1]]]))

    
