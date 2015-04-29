SCORES = {
    1: 'eggs',
    2: 'peanuts',
    4: 'shellfish',
    8: 'strawberries',
    16: 'tomatoes',
    32: 'chocolate',
    64: 'pollen',
    128: 'cats',
    }

class Allergies(object):
    def __init__(self, score):
        self.list = self._allergies(score)

    def is_allergic_to(self, allergy):
        return allergy in self.list

    @classmethod
    def _allergies(cls, score):
        return [allergy for val, allergy in sorted(SCORES.iteritems())
                if val & score]
