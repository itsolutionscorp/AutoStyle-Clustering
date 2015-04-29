# -*- coding: utf-8 -*-

# Allergies

#Write a program that, given a person's allergy score, can tell them whether or 
##not they're allergic to a given item, and their full list of allergies.
#
#An allergy test produces a single numeric score which contains the
#information about all the allergies the person has (that they were
#tested for).
#
#The list of items (and their value) that were tested are:
#
# * eggs (1)
# * peanuts (2)
# * shellfish (4)
# * strawberries (8)
# * tomatoes (16)
# * chocolate (32)
# * pollen (64)
# * cats (128)
#
#So if Tom is allergic to peanuts and chocolate, he gets a score of 34.
#
#Now, given just that score of 34, your program should be able to say:
#
# - Whether Tom is allergic to any one of those allergens listed above.
# - All the allergens Tom is allergic to.

class Allergies:

    def __init__(self, score):
        self.list = []
        
        #ignore non-listergen numbers
        score = score % 256
        
        if score >= 128:
            self.list.append('cats')
            score -= 128
        if score >= 64:
            self.list.append('pollen') 
            score -= 64
        if score >= 32:
            self.list.append('chocolate')
            score -= 32
        if score >= 16:
            self.list.append('tomatoes')
            score -= 16
        if score >= 8:
            self.list.append('strawberries')
            score -= 8
        if score >= 4:
            self.list.append('shellfish')
            score -= 4
        if score >= 2:
            self.list.append('peanuts')
            score -= 2
        if score >= 1:
            self.list.append('eggs')

        self.list.reverse()        
        
    def is_allergic_to(self, item):
        if item in self.list:
            allergic = True
        else:
            allergic = False

        return allergic
        
         
        
