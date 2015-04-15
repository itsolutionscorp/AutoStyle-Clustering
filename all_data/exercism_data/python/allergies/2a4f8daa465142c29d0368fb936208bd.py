ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']


class Allergies:

    def __init__(self, score):
        self.list = [j for i, j in enumerate(ALLERGENS) if int(bin(score >> i)[-1])]

    def is_allergic_to(self, allergen):
        return allergen in self.list
