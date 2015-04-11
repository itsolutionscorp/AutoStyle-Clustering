class Allergies(object):
    """ Allergy test results. """
    allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries',
                 'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, score):
        self.score = score
        self.list = []
        for i, a in enumerate(self.allergens):
            if ((self.score >> i) % 2 == 1):
                self.list.append(a)

    def is_allergic_to(self, item):
        return item in self.list
