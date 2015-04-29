# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
from collections import OrderedDict

class Allergies:
    """
    Class contains dictionary of allergens and methods to give a list based on a
    score as well as return whether a person is allergic to a spcecific allergen
    based on their score
    """
    allergy_scores = OrderedDict([("eggs", 1),
                                  ("peanuts", 2),
                                  ("shellfish", 4),
                                  ("strawberries", 8),
                                  ("tomatoes", 16),
                                  ("chocolate", 32),
                                  ("pollen", 64),
                                  ("cats", 128)])

    def __init__(self, patient_score):
        self.patient_score = patient_score

    def is_allergic_to(self, allergen):
        """
        Given an allergen returns true or false depeding on their score via
        binary/bitwise operations
        """
        return bool(self.patient_score & self.allergy_scores[allergen])

    @property
    def list(self):
        """Given a score lists items a person is allergic to"""
        return [allergen
                for allergen in self.allergy_scores
                if self.is_allergic_to(allergen)]
