class Allergies(object):

    _SCORES = {
        'eggs': 1,
        'peanuts': 2,
        'shellfish': 4,
        'strawberries': 8,
        'tomatoes': 16,
        'chocolate': 32,
        'pollen': 64,
        'cats': 128
    }

    def __init__(self, score):
        self.score = score
        self.list = [a for a in sorted(self._SCORES, key=self._SCORES.get) if self.is_allergic_to(a)]

    def is_allergic_to(self, allergen):
        return self._SCORES[allergen] & self.score
