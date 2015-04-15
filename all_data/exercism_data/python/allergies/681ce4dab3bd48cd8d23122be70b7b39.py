from itertools import compress


class Allergies:

    allergens = ['eggs',
                 'peanuts',
                 'shellfish',
                 'strawberries',
                 'tomatoes',
                 'chocolate',
                 'pollen',
                 'cats']

    def __init__(self, score):
        self.list = list(
            compress(
                self.allergens,
                map(int, bin(score)[-1:1:-1][:len(self.allergens)])
                )
            )

    def is_allergic_to(self, allergen):
        return allergen in self.list
