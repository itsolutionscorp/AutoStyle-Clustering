ALLERGY_LIST = [
    'eggs',
    'peanuts',
    'shellfish',
    'strawberries',
    'tomatoes',
    'chocolate',
    'pollen',
    'cats'
]


class Allergies(object):
    def __init__(self, value):
        self.list = self._compute_allergies(value)

    def _compute_allergies(self, value):
        return [
            ALLERGY_LIST[i] for i in range(8)
            if value & 1 << i
        ]

    def is_allergic_to(self, item):
        return item in self.list
