#!/usr/bin/env python

class Allergies(object):

    allergens = ["eggs", "peanuts",
                 "shellfish", "strawberries", 
                 "tomatoes", "chocolate", 
                 "pollen","cats"]
    
    def __init__(self, score):
        self.list = self.score_breakdown(score)

    def score_breakdown(self, score):
        result = []
        for i in range(8):
            mask = 0b1 << i  # shift "bit mask" into position 
            if score & mask: # if "bitwise and" returns True
                result.append(self.allergens[i])
        return result

    def is_allergic_to(self, item):
        return item in self.list
