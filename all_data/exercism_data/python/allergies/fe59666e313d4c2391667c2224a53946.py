#!/usr/bin/env python3

allergens = ['eggs',
             'peanuts',
             'shellfish',
             'strawberries',
             'tomatoes',
             'chocolate',
             'pollen',
             'cats']

class Allergies:

    def __init__(self, score):
        self.score = str(bin(score).split('b')[1].zfill(8))[-8:]
        self.list = []
        for i in range(8):
            if self.score[7 - i] == '1':
                self.list.append(allergens[i])

    def is_allergic_to(self, allergen):
        if allergen in self.list:
            return True
        else:
            return False
