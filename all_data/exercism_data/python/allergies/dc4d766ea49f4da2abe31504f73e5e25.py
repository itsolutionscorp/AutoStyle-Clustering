# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 18:36:39 2014
"""


class Allergies(object):

    allergy_list = [('cats', 128),
                    ('pollen', 64),
                    ('chocolate', 32),
                    ('tomatoes', 16),
                    ('strawberries', 8),
                    ('shellfish', 4),
                    ('peanuts', 2),
                    ('eggs', 1)]

    def Find_Allergies(self, input_num):
        if input_num == 0:
            return []
        else:
            for p in self.allergy_list:
                if p[1] <= input_num:
                    return self.Find_Allergies(input_num - p[1]) + [p[0]]

    def __init__(self, code):
        if code >= 256:
            code = code - 256
        self.list = Allergies.Find_Allergies(self, code)

    def is_allergic_to(self, allergy_to_check):
        return allergy_to_check in self.list
