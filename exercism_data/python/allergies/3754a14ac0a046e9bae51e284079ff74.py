_ALLERGEN_CODE_TO_NAME = {
        1: 'eggs',
        2: 'peanuts',
        3: 'shellfish',
        4: 'strawberries',
        5: 'tomatoes',
        6: 'chocolate',
        7: 'pollen',
        8: 'cats'
}

class Allergies(object):
    def __init__(self, score):
        self._score = score
        self.list = []
        for code, name in _ALLERGEN_CODE_TO_NAME.items():
            code_power = pow(2, code-1)
            if self._score & code_power != 0:
                self.list.append(name)

    def is_allergic_to(self, candidate):
        return candidate in self.list
