ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries',
             'tomatoes', 'chocolate', 'pollen', 'cats']


class Allergies:
    def __init__(self, score):
        self.score = score
        self.list = [a for a in ALLERGENS if self.is_allergic_to(a)]

    def is_allergic_to(self, allergen):
        return bool(self.score & 2**ALLERGENS.index(allergen))
