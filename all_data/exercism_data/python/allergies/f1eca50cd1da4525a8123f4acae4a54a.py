# -*- coding: utf-8 -*-
from string import zfill

class Allergies(object):
    score = 0
    ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries', 
                'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, score):
        self.score = score
        super(Allergies, self).__init__()
        
    def is_allergic_to(self, allergen):
        return allergen in self.list

    @property
    def list(self):
        allergies = zip(self._clean_score(), self.ALLERGENS)
        return [t[1] for t in allergies if int(t[0])]

    def _clean_score(self):
        """
        Returns reversed binary for self.score completed with 8 positions
        """
        bin_score = bin(self.score%256)[2:]
        return zfill(bin_score, 8)[::-1]



        



