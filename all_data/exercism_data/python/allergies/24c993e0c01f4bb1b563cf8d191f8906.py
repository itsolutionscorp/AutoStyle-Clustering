ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']


class Allergies(object):
    def __init__(self, score):
        score %= 2**len(ALLERGENS)  # ignore non allergen score parts
        self.list = []
        for exponant, allergen in reversed(list(enumerate(ALLERGENS))):
            if score >= 2**exponant:
                score -= 2**exponant
                self.list.append(allergen)
        self.list.reverse()

    def is_allergic_to(self, allergen):
        return allergen.lower() in self.list
