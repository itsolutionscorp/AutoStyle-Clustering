from itertools import combinations

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
    
    #Pull the keys from allergyList and sort them
    keys = filter(lambda x: x, allergyList)
    keys.sort()

    #This will contain all possible combinations of 2-8 keys in allergyList
    allCombos = []
        
    #Populate allCombos with all combinations of 2-8 keys
    for members in range(2,9):
        allCombos.append(combinations(keys, members))

    #Generate a dictionary that contains the sums of all combinations of 2-8 keys
     #with their respective combinations as values
    lookupDictionary = {}
    for i in allCombos:
        for j in i:
            lookupDictionary[sum(j)] = j

    def __init__(self, allergyScore):
        
        self.allergyScore = allergyScore
    
        #This will be the final list that contains patient allergies
        self.list = []
        #Deals with numbers outside of 255 range.
        if self.allergyScore > 255:
            self.list.append(Allergies.allergyList[self.allergyScore - 256])
        #if the allergyScore is already in keys, append the corresponding allergy value
        #to the allergy list
        elif self.allergyScore in Allergies.keys:
            self.list.append(Allergies.allergyList[self.allergyScore])
        #if allergyScore is 0, make the allergy list empty
        elif self.allergyScore == 0:
            self.list = []
        else:
            self.hits = Allergies.lookupDictionary[self.allergyScore]
            for each in self.hits:
                self.list.append(Allergies.allergyList[each])    

    def is_allergic_to(self, allergen):
        self.allergen = allergen
        if self.allergen in self.list:
            return True
        else:
            return False
