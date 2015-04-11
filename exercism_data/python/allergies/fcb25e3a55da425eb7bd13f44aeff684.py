#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Submission file for the python allergies exercise.
#
# v1:


class Allergies(object):
    """
    Store and calculate allergies depending on a person's allergy score.
    """

    _allergens = list(zip('eggs peanuts shellfish strawberries '
                          'tomatoes chocolate pollen cats'.split(),
                          [2**x for x in range(8)]))

    def is_allergic_to(self, allergen):
        """
        Tell if a person if allergic to the given allergen.
        """
        return allergen in self.list

    def __init__(self, score):
        """
        Initialise an allergy storage with the given allergy score.
        """
        self.score = score
        self.list = [candidate[0] for candidate in self._allergens
                     if candidate[1] & self.score]
