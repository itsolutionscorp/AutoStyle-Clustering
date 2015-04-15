listOfAllergies = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']

class Allergies:

    def __init__(self, allergyScore):
        self.allergyScore = allergyScore % 256
        self.list = self.setAllergies()

    def setAllergies(self):
        list = []
        allergyCode = str('{0:08b}'.format(self.allergyScore))
        count = 1
        for d in allergyCode:
            if d == '1':
                list.insert(0,listOfAllergies[-count])
            count += 1
        return list
        
    def is_allergic_to(self, allergy):
        for a in self.list:
            if a == allergy:
                return True
        return False
