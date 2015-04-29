# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 14:26:36 2014

@author: laegrim
"""

class Allergies(object):
    '''
    Class holds information related to a person's allergies
    '''

    def __init__(self, allergies):

        self.allergens = {
        'eggs' : 1,
        'peanuts' : 2,
        'shellfish' : 4,
        'strawberries' : 8,
        'tomatoes' : 16,
        'chocolate' : 32,
        'pollen' : 64,
        'cats' : 128}

        self.list = sorted([allergen for allergen in self.allergens if allergies & self.allergens[allergen] == self.allergens[allergen]],
                    key = lambda allergen: self.allergens[allergen])

    def is_allergic_to(self, allergen):
        return allergen in self.list
        
        
