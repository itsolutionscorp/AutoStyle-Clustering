#William Morris
#exercism.io
#allergies.py

import math

class Allergies:

    def __init__(self,allergy_number):
        allergy_number %= 256
        self.allergy_number = allergy_number
        self.allergen_list = ('eggs peanuts shellfish strawberries tomatoes '
                             'chocolate pollen cats').split()
        self.list = []
        while allergy_number > 0:
            max_allergy = int(math.log(allergy_number,2))
            self.list = [self.allergen_list[max_allergy]] + self.list
            allergy_number %= 2**max_allergy
        

    def is_allergic_to(self,allergen):
        return allergen in self.list
        
