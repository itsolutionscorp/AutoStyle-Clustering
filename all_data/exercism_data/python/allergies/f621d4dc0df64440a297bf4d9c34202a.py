#!/usr/bin/env python

class Allergies:
    ALLERGY_TUP = ((1, 'eggs'), (2, 'peanuts'), (4, 'shellfish'),
                   (8, 'strawberries'), (16, 'tomatoes'), (32, 'chocolate'),
                   (64, 'pollen'), (128, 'cats'))
    def __init__(self, allergy_score):
        self.allergy_score = allergy_score
        self.list = []
        self._createAllergyList()

    def is_allergic_to(self, allergy):
        if allergy in self.list:
            return True
        else:
            return False
    

    def _createAllergyList(self):
        for k,v in self.ALLERGY_TUP:
            if self.allergy_score & k:
                self.list.append(v)
