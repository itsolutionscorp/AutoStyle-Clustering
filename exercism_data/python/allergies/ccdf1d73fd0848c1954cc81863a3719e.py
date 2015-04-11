# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 16:03:31 2014

@author: johann
"""

class Allergies:
    def __init__(self,score):
        self.score = score
        self.list = check_Allergies(score)
    def is_allergic_to(self,word):
        if word in self.list:
            return True
        return False
    
def check_Allergies(score):
    allergies = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
    allergy_list = []    
    for i in range(8):
        if score >> i & 1 == True:
            allergy_list.append(allergies[i])
    return allergy_list
    
    
