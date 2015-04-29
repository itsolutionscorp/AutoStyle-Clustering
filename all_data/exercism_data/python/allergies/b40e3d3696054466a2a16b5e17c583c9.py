all_allergies = [
    (1, 'eggs'),
    (2, 'peanuts'),
    (4, 'shellfish'),
    (8, 'strawberries'),
    (16, 'tomatoes'),
    (32, 'chocolate'),
    (64, 'pollen'),
    (128, 'cats')
]


class Allergies():

    def __init__(self, allergy_score):
        self.list = [allergy for (allergy_mask, allergy) in all_allergies if allergy_score & allergy_mask]

    def is_allergic_to(self, allergy):
        return allergy in self.list
