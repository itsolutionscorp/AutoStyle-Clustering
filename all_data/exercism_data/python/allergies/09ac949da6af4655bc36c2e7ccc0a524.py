ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']


class Allergies(object):
    def __init__(self, score):
        self.list = [allergen for exponant, allergen in enumerate(ALLERGENS) if score & 2**exponant > 0]

    def is_allergic_to(self, allergen):
        return allergen.lower() in self.list
