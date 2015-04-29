class Allergies(object):
    ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes',
                 'chocolate', 'pollen', 'cats']

    def __init__(self, allergies):
        self.allergies = allergies

    def is_allergic_to(self, allergen):
        allergen_mask = 2 ** Allergies.ALLERGENS.index(allergen)
        return self.allergies & allergen_mask > 0

    @property
    def list(self):
        return [Allergies.ALLERGENS[i] for i in range(8)
                if self.allergies & 2 ** i > 0]
