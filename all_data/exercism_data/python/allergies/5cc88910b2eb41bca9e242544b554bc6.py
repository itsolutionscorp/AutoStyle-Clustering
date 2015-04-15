class Allergies(object):
    TESTS = [
        'eggs',
        'peanuts',
        'shellfish',
        'strawberries',
        'tomatoes',
        'chocolate',
        'pollen',
        'cats'
    ]

    def __init__(self, score):
        self.score = score
        self.list = self._list()

    def is_allergic_to(self, allergen):
        return self.score & self._allergen_value(allergen)

    def _list(self):
        allergens = []
        value = self.score
        for allergen in self.TESTS:
            if value % 2 == 1:
                allergens.append(allergen)
            value /= 2
        return allergens

    def _allergen_value(self, allergen):
        try:
            return 1 << self.TESTS.index(allergen)
        except ValueError:
            return None
