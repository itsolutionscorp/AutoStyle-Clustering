# -*- coding: utf-8 -*-

import itertools

class Allergies:
    """Represents all the allergies one can have given an allergy score."""

    names = ("eggs peanuts shellfish strawberries "
             "tomatoes chocolate pollen cats").split()

    codes = dict(zip(names, itertools.count()))

    def __init__(self, score):
        self._score = score
        
        self.list = [name for name in Allergies.names
                        if self.is_allergic_to(name)]

    def is_allergic_to(self, allergy):
        """
        is_allergic_to(self, str) -> bool

        Return True if the given allgery is in the list of allergies.
        """

        return (self._score >> Allergies.codes[allergy]) & 1
