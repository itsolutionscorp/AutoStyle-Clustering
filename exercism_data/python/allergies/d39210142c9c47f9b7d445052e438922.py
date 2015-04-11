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
# Correct sorting for stupid test where a specifically sorted list, according to this, is expected.
list_sorting = "eggs peanuts shellfish strawberries tomatoes chocolate pollen cats".split()


class Allergies():

    def __init__(self, allergy_score):
        self.list = [allergy for (bitmask, allergy) in all_allergies.items() if allergy_score & bitmask]

        # Deal with stupid test that can't handle list with different sorting.
        self.list.sort(key=lambda y: list_sorting.index(y))

    def is_allergic_to(self, allergy):
        return allergy in self.list
