import collections

class Allergies(object):
    SCORES = {'eggs': 1, 'peanuts': 2, 'shellfish': 4, 'strawberries': 8,
              'tomatoes': 16, 'chocolate': 32, 'pollen': 64, 'cats': 128}
    SCORES = collections.OrderedDict(sorted(SCORES.items(), key=lambda s: s[1]))

    def __init__(self, score):
        self.score = score

    def is_allergic_to(self, food):
        return bool(self.score & self.SCORES[food])

    @property
    def list(self):
        return [food for food, score in self.SCORES.items() if
                self.is_allergic_to(food)]
