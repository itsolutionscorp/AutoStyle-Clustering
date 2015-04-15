from collections import OrderedDict


class Allergies(object):
    # some tests depend on allergen order, so we start with an ordered dict
    _allergen_map = OrderedDict([
        (1, 'eggs'),
        (2, 'peanuts'),
        (4, 'shellfish'),
        (8, 'strawberries'),
        (16, 'tomatoes'),
        (32, 'chocolate'),
        (64, 'pollen'),
        (128, 'cats')
    ])

    def __init__(self, test_result):
        self._allergens = tuple(self._allergen_map[n]
                                for n in self._allergen_map
                                if n & test_result != 0)

    @property
    def list(self):
        return list(self._allergens)

    def is_allergic_to(self, allergen):
        return allergen in self._allergens
