class Allergies(object):

    def __init__(self, allergy_score):

        self._allergens = list()

        for i, item in enumerate(['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']):
            if allergy_score >> i & 0x01:
                self._allergens.append(item)

    def is_allergic_to(self, allergen):
        return allergen in self._allergens

    @property
    def list(self):
        return self._allergens
