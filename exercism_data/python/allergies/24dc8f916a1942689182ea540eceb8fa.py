class Allergies(object):

    allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes',
                 'chocolate', 'pollen', 'cats']

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
        self.list = [self.allergens[i] for i in range(8)
                     if self._score & (1 << i)]
