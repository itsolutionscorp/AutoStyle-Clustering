class Allergies:

    allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, score):
        self.list = []

        for i, allergen in enumerate(self.allergens):
            if score & 2 ** i:
                self.list.append(allergen)

    def is_allergic_to(self, allergen):
        return allergen in self.list
