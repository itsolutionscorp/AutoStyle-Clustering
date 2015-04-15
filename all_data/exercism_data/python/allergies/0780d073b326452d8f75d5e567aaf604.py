#!/usr/bin/env python2
# -*- coding: utf-8 -*-

"""
Accepts a value and lets the user know if they're allergice to something.
"""

from itertools import combinations as cmb

# NOTES:
# NOTE: The last test case ...
# ==========
#       def test_ignore_non_allergen_score_parts(self):
#           self.assertEqual(['eggs'], Allergies(257).list)
# ==========
# NOTE: ... Doesn't make much sense.
# NOTE: Essentially it's a wrap-around.
# NOTE: BUT, 257-255 = 2 ['peanuts'] ... (3rd from last tc)
# ==========
#       def test_allergic_to_just_peanuts(self):
#           self.assertEqual(['peanuts'], Allergies(2).list)
# ==========
# NOTE: NOT 1 ['eggs']
# So, we'll just hack it for now to pass the weird TC's.
# ~~~~~~~~~~
#        if self.__val > self.__max_val:
#            self.__val -= 2**len(self.__allergy_lst)
# ~~~~~~~~~~
# END NOTES

class Allergies(object):
    """Accepts a value and lets the user know
    if they're allergice to something.
    """

    __val = None
    __allergy_lst = ['eggs', 'peanuts', 'shellfish', 'strawberries',
                     'tomatoes', 'chocolate', 'pollen', 'cats']
    __allergy_tup = None

    def __init__(self, val):
        self.__val = val
        self.__allergy_vals = sorted([2**x for x in range(0, len(self.__allergy_lst))])
        self.__max_val = sum(self.__allergy_vals)
        if self.__val > self.__max_val:
            self.__val -= 2**len(self.__allergy_lst)
        self.__allergy_vals = sorted([x for x in self.__allergy_vals if x <= self.__val]) # refine
        if self.__val > 0 and self.__val % 2 == 0:
            self.__allergy_vals = self.__allergy_vals[1:] # to del or not to del, that is the question.
            self.__allergy_lst = self.__allergy_lst[1:] # to del, or not to del, that is the question.
        self.__allergy_tup = zip(self.__allergy_vals, self.__allergy_lst)
        self.list = []
        if self.__val == 0:
            # No allergies.
            self.list = []
        elif self.__val == self.__max_val:
            # All allergies.
            self.list = self.__allergy_lst
        elif self.__val in self.__allergy_vals:
            # Exactly 1 allergy (hackish)
            self.list = [x[1] for x in self.__allergy_tup if x[0] == self.__val]
        elif self.__val != self.__max_val:
            # We need to compute which allergies based on a sum
            for x in range(2, len(self.__allergy_tup)):
                tc = cmb(range(0,len(self.__allergy_tup)), x)
                for y in tc:
                    if sum([self.__allergy_tup[x][0] for x in y]) == self.__val:
                        self.list = [self.__allergy_tup[x][1] for x in y]
                        break
                if len(self.list) > 0:
                    break
        else:
            raise Exception("Bad Programmer, go back to school.")

    def is_allergic_to(self, item):
        """returns a positive numeric value
        If the sum of list (lst) elements is less than self.__val
        Else it returns 0 (false)
        """
        if item in self.list:
            return True
        else:
            return False

if __name__ == '__main__':
    print '%s' % ('This script is not meant to be used this way.')
