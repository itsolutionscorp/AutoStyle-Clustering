import itertools

class Allergies:
    __ALLERGIES = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
    def __init__(self, code):
        self.list = list(itertools.compress(Allergies.__ALLERGIES, map(int, reversed(bin(code)[2:]))))

    def is_allergic_to(self, allergen):
        return allergen in self.list
