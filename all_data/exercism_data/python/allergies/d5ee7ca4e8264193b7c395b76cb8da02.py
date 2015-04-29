#!/usr/bin/env python3
#-*- coding: utf-8 -*-

__author__ = "Greg"

class Allergies(object):
    
    allergens = {1:   "eggs",
                 2:   "peanuts",
                 4:   "shellfish",
                 8:   "strawberries",
                 16:  "tomatoes",
                 32:  "chocolate",
                 64:  "pollen",
                 128: "cats"}

    def __init__(self, allergy_test_value):
        self.allergy_test_value = allergy_test_value
        self.list = []
    
        sum_allergens = sum(self.allergens.keys())
        test_value = self.allergy_test_value
       
        if test_value > sum_allergens:
            test_value = self.allergy_test_value - sum_allergens
        
        for y in range(len(self.allergens),-1,-1):
            print("y = " + str(y))
            if test_value >= 2**y:
                self.list.insert(0, self.allergens[2**y])
                test_value -= 2**y

    
    def is_allergic_to(self, allergen):
        if allergen in self.list:
            return True
        else:
            return False

print(Allergies(256).list)
