class Allergies():
    allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, score):
        self.score = score
        self.list = [Allergies.allergens[i] for i, _bool in enumerate(reversed('{:08b}'.format(self.score)[-8:]))
                     if _bool == '1']

    def is_allergic_to(self, allergen):
        return allergen in self.list
