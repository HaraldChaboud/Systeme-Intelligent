class Character:
    def __init__(self, name, firstName, age, profession, moraleValue):
        self.name = name
        self.firstName = firstName
        self.age = int(age)
        self.profession = profession
        self.moraleValue = float(moraleValue)
    #Getters
    def getName(self):
        return self.name
    
    def getFirstname(self):
        return self.firstname

    def getAge(self):
        return self.age

    def getProfession(self):
        return self.profession

    def getMoraleValue(self):
        return self.moraleValue
    
    #Setters
    def setName(self,name):
        self.name = name
    
    def setFirstName(self,firstName):
        self.firstName = firstName

    def setAge(self,age):
        self.age = age

    def setProfession(self,profession):
        self.profession = profession

    def setMoraleValue(self,moraleValue):
        self.moraleValue = moraleValue
   
    def __repr__(self):
        return "{}  {}  {}  {}  {}".format(self.firstName,self.name,self.age,self.profession,self.moraleValue)



class Army:
    def __init__(self, general, moral):
        self.general = general
        self.moral = moral
    def getGeneral(self):
        return self.general

    def getMoral(self):
        return self.moral
    def setGeneral(self, general):
        self.general = general

    def setMoral(self, moral):
        self.moral = moral

    def __repr__(self):
        return "{}  {}  {} -> {}".format(self.general.firstName,self.general.name,self.moral,self.getMoralMod())

    def getMoralMod(self):
        moralMod = self.moral * self.getGeneral().getMoraleValue()
        return moralMod
