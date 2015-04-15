from math import log


class Allergies:
    ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries',
                 'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, score):
        self.score = score
        self.list = []
        self._build_list(score)

    def is_allergic_to(self, allergen):
        return allergen in self.list

    def _build_list(self, total):
        if total == 0:
            return
        index = int(log(total, 2))
        if index < len(self.ALLERGENS):
            self.list.insert(0, self.ALLERGENS[index])
        return self._build_list(total - 2**index)
