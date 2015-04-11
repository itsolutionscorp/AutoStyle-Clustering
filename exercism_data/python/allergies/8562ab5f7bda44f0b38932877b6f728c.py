def find_allergies(score):
    """Returns a list of allergies based on the score"""
    allergenKey = { 1:  'eggs', 2: 'peanuts', 4: 'shellfish', 8:  'strawberries',
                       16: 'tomatoes', 32 : 'chocolate', 64 :  'pollen' , 128 : 'cats' }
    allergens = [2 ** exponent for exponent in range(8)]
    myAllergies = []
    while (score > 0) and (allergens):
        highScore = allergens.pop(-1)
        if score >= highScore:
            myAllergies += [allergenKey[highScore]]
            score = score - highScore
    return myAllergies

class Allergies:
    """Models a person's allergic condition"""

    def __init__(self, score):
        self._score = score
        self.list = find_allergies(score)

    def is_allergic_to(self, allergen):
        return allergen in self.list
