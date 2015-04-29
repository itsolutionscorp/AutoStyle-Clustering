__author__ = 'emiller42'


class Allergies:
    def __init__(self, allergy_value):

        # list to contain active allergies
        self.allergies = []

        # use bitwise comparisons to check for allergies
        # add any matches to the list
        if allergy_value & 1:
            self.allergies.append('eggs')
        if allergy_value & 2:
            self.allergies.append('peanuts')
        if allergy_value & 4:
            self.allergies.append('shellfish')
        if allergy_value & 8:
            self.allergies.append('strawberries')
        if allergy_value & 16:
            self.allergies.append('tomatoes')
        if allergy_value & 32:
            self.allergies.append('chocolate')
        if allergy_value & 64:
            self.allergies.append('pollen')
        if allergy_value & 128:
            self.allergies.append('cats')

    def is_allergic_to(self, allergy):
        return allergy in self.allergies

    @property
    def list(self):
        return self.allergies
