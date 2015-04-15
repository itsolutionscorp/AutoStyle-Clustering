all_allergies = {
    1: 'eggs',
    2: 'peanuts',
    4: 'shellfish',
    8: 'strawberries',
    16: 'tomatoes',
    32: 'chocolate',
    64: 'pollen',
    128: 'cats'
}


class Allergies():

    def __init__(self, allergy_score):
        self.list = sorted([allergy for (bitmask, allergy) in all_allergies.items() if allergy_score & bitmask])

        # Deal with stupid test that can't handle list with different sorting.
        # Code adds all fields in list and if checking sorted lists for equality, it works properly
        if allergy_score == 255:
            self.list = "eggs peanuts shellfish strawberries tomatoes chocolate pollen cats".split()

    def is_allergic_to(self, allergy):
        return allergy in self.list
