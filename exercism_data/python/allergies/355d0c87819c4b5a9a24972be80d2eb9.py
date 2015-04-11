from collections import OrderedDict

class Allergies(object):
    ALLERGENS = OrderedDict()
    ALLERGENS[1]   = 'eggs'
    ALLERGENS[2]   = 'peanuts'
    ALLERGENS[4]   = 'shellfish'
    ALLERGENS[8]   = 'strawberries'
    ALLERGENS[16]  = 'tomatoes'
    ALLERGENS[32]  = 'chocolate'
    ALLERGENS[64]  = 'pollen'
    ALLERGENS[128] = 'cats'

    def __init__(self, score):
        self.list = [allergen for i,allergen in self.ALLERGENS.items()
                     if score|i == score]

    def is_allergic_to(self, item):
        return item in self.list

    
