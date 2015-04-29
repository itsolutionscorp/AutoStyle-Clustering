"""
allergies - a module for allergy sufferers.
"""

class Allergies(object):
    """
    An object describing a given patients allergies.
    """

    # Class variable of all allergy types.
    allergy_type_list = ["eggs",
                         "peanuts",
                         "shellfish",
                         "strawberries",
                         "tomatoes",
                         "chocolate",
                         "pollen",
                         "cats"]

    def __init__(self, score):
        # Take advantage of the binary representation to determine which allergies are present.
        check_list = [i == '1' for i in reversed(str(bin(score))[2:])]

        # List the allergies present explicitly by name.
        self.list = [allergy_type 
                     for present, allergy_type in zip(check_list, Allergies.allergy_type_list) 
                     if present]

    def is_allergic_to(self, allergy_type):
        # Simply check if the allergy is present in the list.
        return allergy_type in self.list
