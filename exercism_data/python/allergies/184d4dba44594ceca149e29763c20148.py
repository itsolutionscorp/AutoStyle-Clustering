"""
This module holds an Allergies class that is instantiated with a number
that corresponds to a binary map of possible allergies they can have.
"""

ALLERGY_MAP = {1: "eggs", 2: "peanuts", 4: "shellfish", 8: "strawberries",
               16: "tomatoes", 32: "chocolate", 64: "pollen", 128: "cats", }


class Allergies(object):
    """
    The Allergies class has an allergy_value that is used to instantiate
    and a list of all allergies that this value would be susceptible to.
    """
    def __init__(self, allergy_value):
        self.allergy_value = allergy_value % 256
        self.list = list()
        for i in sorted(ALLERGY_MAP, reverse=True):
            if self.allergy_value >= i:
                self.allergy_value -= i
                self.list.insert(0, ALLERGY_MAP[i])

    def is_allergic_to(self, allergen):
        """
        Simply see if the allergen is included in the Allergies list.
        """
        return allergen in self.list
