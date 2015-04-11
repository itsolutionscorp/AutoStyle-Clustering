class Allergies(object):

    _allergens = [
        (1,   'eggs'),
        (2,   'peanuts'),
        (4,   'shellfish'),
        (8,   'strawberries'),
        (16,  'tomatoes'),
        (32,  'chocolate'),
        (64,  'pollen'),
        (128, 'cats')
    ]

    def __init__(self, allergy_score):
        self.list = [
            allergens for score, allergens in self._allergens
            if score & allergy_score
        ]

    def is_allergic_to(self, allergen):
        return allergen in self.list
