import math

ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']


def find_largest_allergen(score):
    return int(math.log(score, 2))


class Allergies:
    def __init__(self, allergen_score):
        self.allergen_score = allergen_score
        self.list = []
        self.compute_allergies()

    def is_allergic_to(self, allergen):
        if allergen in self.list:
            return True
        return False

    def compute_allergies(self):
        temp_score = self.allergen_score
        while temp_score > 0:
            allergen_score = find_largest_allergen(temp_score)
            if allergen_score < len(ALLERGENS):
                self.list = [ALLERGENS[allergen_score]] + self.list
            temp_score -= math.pow(2, allergen_score)
