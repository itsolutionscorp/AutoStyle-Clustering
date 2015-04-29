__author__ = 'emiller42'


class Allergies:

    # class level dict to store possible allergy values
    allergy_dict = {1: 'eggs',
                    2: 'peanuts',
                    4: 'shellfish',
                    8: 'strawberries',
                    16: 'tomatoes',
                    32: 'chocolate',
                    64: 'pollen',
                    128: 'cats'}

    def __init__(self, allergy_value):

        # list to contain active allergies
        self.allergies = []

        # use bitwise comparisons to check for allergies
        # add any matches to the list
        for allergy_num in sorted(Allergies.allergy_dict):
            if allergy_value & allergy_num:
                self.allergies.append(Allergies.allergy_dict[allergy_num])

    def is_allergic_to(self, allergy):
        return allergy in self.allergies

    @property
    def list(self):
        return self.allergies
