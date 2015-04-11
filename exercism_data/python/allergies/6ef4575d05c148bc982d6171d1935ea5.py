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
    item_list = {1 : "eggs" ,2 : "peanuts",4:  "shellfish" ,8: "strawberries",16: "tomatoes" ,32:"chocolate",64: "pollen", 128: "cats"}
    allergy_list = []    
    values_list = sorted(item_list.keys(),reverse=True)
    for value in values_list:
        if value <= score:
            allergy_list.insert(0,item_list[value])   
            score -= value
    return allergy_list
    
    
