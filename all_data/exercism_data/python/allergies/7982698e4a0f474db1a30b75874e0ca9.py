class Allergies:
    allergens = { 1: 'eggs',
                  2: 'peanuts',
                  4: 'shellfish',
                  8: 'strawberries',
                  16: 'tomatoes',
                  32: 'chocolate',
                  64: 'pollen',
                  128: 'cats' }

    def __init__(self, score):
        self.list = self.getAllergies(score)


    def getAllergies(self, score):
        # Convert score to binary
        score = bin(score)

        i = len(score) - 1
        allergy = 1
        allergies = []

        # Go through the binary string from end until binary end 'b' or
        # largest key in allergens dictionary is reached
        while (score[i] != 'b' and allergy <= max(self.allergens.keys())):
            if score[i] == '1':
                allergies.append(self.allergens[allergy])
            allergy *= 2        # next key in allergens
            i -= 1
        return(allergies)

    def is_allergic_to(self, allergen):
        if allergen in self.list:
            return(True)
        else:
            return(False) 




    
            
