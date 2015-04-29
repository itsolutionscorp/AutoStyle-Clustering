#!/usr/bin/python3 -V
# -*- coding: utf-8 -*-

class Allergies(object):
    def __init__(self, allergies_flag):
        allergies_list = [
            'eggs',
            'peanuts',
            'shellfish',
            'strawberries',
            'tomatoes',
            'chocolate',
            'pollen',
            'cats'
        ]
        flag_reverse = format(allergies_flag % 256, '08b')
        flag = flag_reverse[::-1]
        
        self.list = [x for x,y in zip(allergies_list, flag) if y == '1']

    def is_allergic_to(self, allergen):
        return allergen in self.list
