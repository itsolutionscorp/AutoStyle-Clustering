__author__ = 'Hinek'

class Allergies(object):
    def __init__(self, score):
        self.score = score % 256
        self.binary_score = list("{0:b}".format(self.score))
        self.ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
        self.list = self.list_allergens()

    def list_allergens(self):
        result = []
        for index, s in enumerate(reversed(self.binary_score)):
            if s == '1':
                result.append(self.ALLERGENS[index])
        return result

    def is_allergic_to(self, item):
        return item in self.list
