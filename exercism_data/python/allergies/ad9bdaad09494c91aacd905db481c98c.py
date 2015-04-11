class Allergies(object):
    allergens = [
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
        self.list = [allergen for allergen in self.allergens if self.is_allergic_to(allergen)]

    def is_allergic_to(self, alergen):
        return bool( 1 << self.allergens.index(alergen) & self.score)
