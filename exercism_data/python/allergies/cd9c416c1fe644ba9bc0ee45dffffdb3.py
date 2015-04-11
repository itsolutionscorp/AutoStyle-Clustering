ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']


class Allergies:

    def __init__(self, score):
        self.list = [j for i, j in enumerate(ALLERGENS) if score & 2**i]

    def is_allergic_to(self, allergen):
        return allergen in self.list
