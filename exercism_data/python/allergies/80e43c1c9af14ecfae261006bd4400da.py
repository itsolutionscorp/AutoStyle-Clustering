#!/usr/bin/env python2
# -*- coding: utf-8 -*-

"""
Accepts a value and lets the user know if they're allergice to something.
"""

# NOTE: https://github.com/exercism/xpython/issues/111

class Allergies(object):
    """Accepts a value and lets the user know
    if they're allergice to something.
    """

    __allergy_lst = ['eggs', 'peanuts', 'shellfish', 'strawberries',
                     'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, val):
        self.__val = val
        bstr = bin(self.__val+(2**len(self.__allergy_lst)))[3:][::-1]
        self.list = [x[1] for x in zip(list(bstr), self.__allergy_lst) if x[0] == '1']

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
    print 'This script is not meant to be used this way.'
