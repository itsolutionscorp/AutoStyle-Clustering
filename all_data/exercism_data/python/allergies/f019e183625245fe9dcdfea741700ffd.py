#!/usr/bin/env python3

from itertools import compress

class Allergies:
    '''
    Takes an allergy score and creates a list of allergens
    is_allergic_to(allergen) will tests whether a specific allergen is
    in the list of allergens
    '''
    def __init__(self, score=0):
        self.score = score

        allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes',
                     'chocolate', 'pollen', 'cats']

        reversed_bin_score = bin(self.score)[-1:1:-1]
        score_to_binary = map(int, reversed_bin_score)

        self.list = list(compress(allergens, score_to_binary))

    def is_allergic_to(self, allergen):
        return allergen in self.list
