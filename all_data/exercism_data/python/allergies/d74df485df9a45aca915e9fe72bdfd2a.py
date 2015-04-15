#!/usr/bin/python

class Allergies:
    def __init__(self, allergy_score):
        self.allergy_score = allergy_score % 256
        self.allergy_index = [('cats', 128), ('pollen', 64), ('chocolate', 32),
                              ('tomatoes', 16), ('strawberries', 8), 
                              ('shellfish', 4), ('peanuts', 2), ('eggs', 1)]
        self.list = []
        self.build_allergy_list()

    def is_allergic_to(self, food_string):
        is_allergic = False
        for item in self.list:
            if item == food_string:
                is_allergic = True
        return is_allergic

    def build_allergy_list(self):
        allergy_score = self.allergy_score
        for allergy_tuple in self.allergy_index:
            if allergy_score >= allergy_tuple[1]:
                allergy_score -= allergy_tuple[1]
                self.list.insert(0,allergy_tuple[0])
        return

