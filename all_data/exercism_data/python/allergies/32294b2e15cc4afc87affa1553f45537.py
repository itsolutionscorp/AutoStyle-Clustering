#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Allergies
"""

__version__ = "0.0.2"
__all__ = ["__version__", "Allergies"]


ALLERGENS = ["eggs","peanuts","shellfish","strawberries","tomatoes","chocolate","pollen","cats"]


class Allergies(object):
    def __init__(self, n):
        self.list = [ALLERGENS[i] for i in xrange(len(ALLERGENS)) if 2**i & n]

    def is_allergic_to(self, allergen):
        return allergen in self.list
