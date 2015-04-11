class Allergies(object):

    _allergies = [
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

    def is_allergic_to(self, allergy):
        return allergy in self.list

    @property
    def score(self):
        return self._score

    @score.setter
    def score(self, value):
        self._score = value
        self.list = [allergy for index, allergy in enumerate(self._allergies)
                     if value & 1 << index]
