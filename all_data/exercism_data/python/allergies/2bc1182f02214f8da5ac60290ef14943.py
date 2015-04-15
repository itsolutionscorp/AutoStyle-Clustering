allergens = [
    'eggs',
    'peanuts',
    'shellfish',
    'strawberries',
    'tomatoes',
    'chocolate',
    'pollen',
    'cats',
]


class Allergies:

    def __init__(self, score):
        self.list = []
        for i, allergen in enumerate(allergens):
            if score & 1 << i:
                self.list.append(allergen)

    def is_allergic_to(self, allergen):
        return allergen in self.list
