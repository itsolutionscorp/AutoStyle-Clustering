class Allergies:
    ALLERGENS = [
    'eggs',
    'peanuts',
    'shellfish',
    'strawberries',
    'tomatoes',
    'chocolate',
    'pollen',
    'cats'
    ]

    def __init__(self, score):
        self.score = score
        self.list = []
        for allergen in self.ALLERGENS:
            try:
                if self.score & (2 ** self.ALLERGENS.index(allergen)):
                    self.list.append(allergen)
            except:
                pass

    def is_allergic_to(self, allergen):
        return allergen in self.list
