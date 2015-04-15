class Allergies:
    def __init__(self, score, all_allergens=None):
        if all_allergens is None:
            all_allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries',
                             'tomatoes', 'chocolate', 'pollen', 'cats']

        self.list = [allergen
                     for i, allergen in enumerate(all_allergens)
                     if score & (1 << i)]

    def is_allergic_to(self, allergen):
        return allergen in self.list
