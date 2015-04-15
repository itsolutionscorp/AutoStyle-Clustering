# -*- coding: utf-8 -*-
"""
Created on Fri Sep 26 11:17:04 2014

@author: Blair
"""

# eggs (1)
# peanuts (2)
# shellfish (4)
# strawberries (8)
# tomatoes (16)
# chocolate (32)
# pollen (64)
# cats (128)

class Allergies:
    
    def __init__(self, allergyScore):
        self.score = allergyScore
        self.list = self.allergyList(allergyScore)
    
    def is_allergic_to(self, item):
        if item.lower() in self.list:
            return True
        else:
            return False
        
    def allergyList(self, score):
        allergies = []
        if score & 1 == 1:
            allergies.append("eggs")
        if score & 2 == 2:
            allergies.append("peanuts")
        if score & 4 == 4:
            allergies.append("shellfish")
        if score & 8 == 8:
            allergies.append("strawberries")
        if score & 16 == 16:
            allergies.append("tomatoes")
        if score & 32 == 32:
            allergies.append("chocolate")
        if score & 64 == 64:
            allergies.append("pollen")
        if score & 128 == 128:
            allergies.append("cats")
                
        return allergies
        
print Allergies(255).list
