#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Allergies
"""

__version__ = "0.0.1"
__all__ = ["__version__", "Allergies"]


ALLERGENS = {
    "eggs": 1,
    "peanuts": 2,
    "shellfish": 4,
    "strawberries": 8,
    "tomatoes": 16,
    "chocolate": 32,
    "pollen": 64,
    "cats": 128
}

class Allergies(object):
    def __init__(self, n):
        self.n = n
        self._make_list()

    def _make_list(self):
        self.list = []
        for allergen in ALLERGENS:
            if ALLERGENS[allergen] & self.n:
                self.list.append(allergen)

    def is_allergic_to(self, allergen):
        return allergen in ALLERGENS and \
            ALLERGENS[allergen] & self.n
