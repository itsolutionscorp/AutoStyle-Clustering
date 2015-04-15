class Allergies:

    allergyList = {1: 'eggs',
                   2: 'peanuts',
                   4: 'shellfish',
                   8: 'strawberries',
                   16: 'tomatoes',
                   32: 'chocolate',
                   64: 'pollen',
                   128: 'cats'
                   }
    
    def __init__(self, allergyScore):
        self.allergyScore = allergyScore

        self.list = []
        
        self.sortedAllergies = Allergies.allergyList.keys()
        self.sortedAllergies.sort()
        
        for each in self.sortedAllergies:
            if each & self.allergyScore > 0:
                self.list.append(Allergies.allergyList[each])
            else:
                continue

    def is_allergic_to(self, allergen):
        self.allergen = allergen
        if self.allergen in self.list:
            return True
        else:
            return False
