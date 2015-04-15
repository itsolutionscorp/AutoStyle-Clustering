_ALLERGENS = [
    'eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate',
    'pollen', 'cats'
]

class Allergies(object):
    def __init__(self, score):
        self._score = score
        self.list = []
        for code, name in enumerate(_ALLERGENS):
            code_power = 1 << code
            if self._score & code_power != 0:
                self.list.append(name)

    def is_allergic_to(self, candidate):
        return candidate in self.list
