allergens = {
    1: 'eggs',
    2: 'peanuts',
    4: 'shellfish',
    8: 'strawberries',
    16: 'tomatoes',
    32: 'chocolate',
    64: 'pollen',
    128: 'cats'
}


class Allergies:
    def __init__(self, score):
        self.list = [allergens[k] for k in sorted(allergens.keys()) if score & k == k]

    def is_allergic_to(self, what):
        return what in self.list
