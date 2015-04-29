class Allergies:
    allergenes = {
        1: 'eggs',
        2: 'peanuts',
        4: 'shellfish',
        8: 'strawberries',
        16: 'tomatoes',
        32: 'chocolate',
        64: 'pollen',
        128: 'cats'
    }

    def __init__(self, score):
        self.score = score
        self.list = self.build_list()

    def is_allergic_to(self, subject):
        return subject in self.list

    def build_list(self):
        return [self.allergenes[k] for k in sorted(self.allergenes.keys()) if k & self.score == k]
