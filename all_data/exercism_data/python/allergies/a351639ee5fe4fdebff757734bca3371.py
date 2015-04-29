'''
Created on Sep 25, 2014

@author: adsmith
'''

from collections import OrderedDict

class Allergies(object):
    def __init__(self, allergy_idx):
        self.allergy_idx = allergy_idx
    # Need to OrderedDict this:
    allergy_lookup = OrderedDict([("eggs",         0b00000001),
                                  ("peanuts",      0b00000010),
                                  ("shellfish",    0b00000100),
                                  ("strawberries", 0b00001000),
                                  ("tomatoes",     0b00010000),
                                  ("chocolate",    0b00100000),
                                  ("pollen",       0b01000000),
                                  ("cats",         0b10000000)])
    def is_allergic_to(self,item):
        return self.allergy_idx & self.allergy_lookup[item]
    @property
    def list(self):
        return [allergy for allergy, flg in self.allergy_lookup.items() if flg & self.allergy_idx]
