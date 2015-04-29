class Allergies(object):
    ALLERGY_KEY = (
        'eggs',
        'peanuts',
        'shellfish',
        'strawberries',
        'tomatoes',
        'chocolate',
        'pollen',
        'cats'
    )

    def __init__(self, mask):
        self.mask = mask
        self.list = [a for a, v in zip(self.ALLERGY_KEY,
                                       bin(mask)[:1:-1]) if int(v)]

    def is_allergic_to(self, allergen):
        return allergen in set(self.list)
