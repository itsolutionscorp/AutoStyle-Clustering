#-------------------------------------------------------------------------------
# Name:        Mcface3000
# Purpose:      Mega Awesome Badass Level 3000
#-------------------------------------------------------------------------------

allergen_list2 = [128, 64, 32, 16, 8, 4, 2, 1]
allergen_list = {1: 'eggs', 2: 'peanuts', 4: 'shellfish', 8: 'strawberries', 16: 'tomatoes',
                32: 'chocolate', 64: 'pollen', 128: 'cats'}
answer = []

class Allergies(object):
    def __init__(self, allergy_score):
        self.list = []
        for i in allergen_list2:
            if allergy_score > 255:
                allergy_score = allergy_score % 2
            if allergy_score - i > -1:
                allergy_score = allergy_score - i
                self.list.append(allergen_list[i])
        self.list.reverse()
    def is_allergic_to(self, allergens):
        return allergens in self.list
