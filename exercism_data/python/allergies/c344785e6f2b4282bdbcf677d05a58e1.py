"""
allergies.py:
"""
from collections import OrderedDict

DATA = OrderedDict([
    ('eggs', 1),
    ('peanuts', 2),
    ('shellfish', 4),
    ('strawberries', 8),
    ('tomatoes', 16),
    ('chocolate', 32),
    ('pollen', 64),
    ('cats', 128),
])


class Allergies(object):
    """
    An allergies object.
    """
    def __init__(self, score):
        self.score = score
        self.allergies_data = DATA
        self.food_lst = []

    def is_allergic_to(self, allergy):
        """
        Return True or False depending on whether the allergy score is not
        zero and matches score from data.
            allergy: the food in question
        """
        return bool(self.score & self.allergies_data[allergy])

    @property
    def list(self):
        """
        Return a list of foods to which the person is allergic.
        """
        for k, v in self.allergies_data.items():
            if self.is_allergic_to(k):
                self.food_lst.append(k)
        return self.food_lst
