#!/usr/bin/env python

class Allergies:
    def __init__(self, allergy_score):
        self.allergy_score = allergy_score
        self.allergy_dict = {'eggs': 1, 'peanuts': 2,
                             'shellfish': 4, 'strawberries': 8,
                             'tomatoes': 16, 'chocolate': 32,
                             'pollen': 64, 'cats': 128}
        self.list = []
        self._createAllergyList()

    def is_allergic_to(self, allergy):
        if self.allergy_score & self.allergy_dict[allergy]:
            return True
        else:
            return False
    

    def _createAllergyList(self):
        ##
        # If order didn't matter, this would be better.
        # Alas, the test requires specific order.
        #for k,v in self.allergy_dict.iteritems():
        #    if self.allergy_score & v:
        #        self.list.append(k)
        if self.allergy_score & self.allergy_dict['eggs']:
            self.list.append('eggs')
        if self.allergy_score & self.allergy_dict['peanuts']:
            self.list.append('peanuts')
        if self.allergy_score & self.allergy_dict['shellfish']:
            self.list.append('shellfish')
        if self.allergy_score & self.allergy_dict['strawberries']:
            self.list.append('strawberries')
        if self.allergy_score & self.allergy_dict['tomatoes']:
            self.list.append('tomatoes')
        if self.allergy_score & self.allergy_dict['chocolate']:
            self.list.append('chocolate')
        if self.allergy_score & self.allergy_dict['pollen']:
            self.list.append('pollen')
        if self.allergy_score & self.allergy_dict['cats']:
            self.list.append('cats')
