#!/usr/bin/env python3
""" module allergies of squares for exercism.io programming training"""

scores = 'cats 128, pollen 64, chocolate 32, tomatoes 16, strawberries 8, shellfish 4, peanuts 2, eggs 1'.split(', ')


class Allergies():
    def __init__(self, score):
        if score > 255: score -= 256 # this one is more a guess to what the test wants
        self.score = score
        self.list = self.score_to_list(score, scores)


    def score_to_list(self, score, scores):
        allergy_list = []
        for i in scores:            
            i_allergen, i_score = i.split()
            if score >= int(i_score):
                score -= int(i_score)
                allergy_list.insert(0, i_allergen)
        return allergy_list
            
    
    def is_allergic_to(self, allergen):
        return allergen in self.list
