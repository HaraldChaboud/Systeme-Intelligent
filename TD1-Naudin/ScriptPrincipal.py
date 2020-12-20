from Classes import *
import csv
from numpy import *

if __name__ == "__main__":
    characterMoral = zeros(5)
    armyMoral = zeros(5)
    num=1
    characters_list = []
    armyList = []
    TotalMoral = 0
    with open('characters.csv', newline='') as csvfile:
        fileReader = csv.reader(csvfile, delimiter=',', quotechar='|')
        for row in fileReader:
            if fileReader.line_num > 1:
                characters_list.append(Character(row[0],row[1],row[2],row[3],row[4]))



    for chars in characters_list:
        print(chars)
        armyList.append(Army(chars,random.randint(40,100)))


        
    for armee in armyList:
        print("Army " + str(num))
        print(armee)
        TotalMoral = TotalMoral + armee.getMoralMod()
        num=num+1


    print("---")
    print("Le Moral total de toutes les armées vaut:" + str(TotalMoral))

    for i in range(5):
        characterMoral[i] = characters_list[i].getMoraleValue()
        armyMoral[i] = armyList[i].getMoral()
    print("Tableaux recensant toutes les données utiles")
    print(characterMoral)
    print(armyMoral)