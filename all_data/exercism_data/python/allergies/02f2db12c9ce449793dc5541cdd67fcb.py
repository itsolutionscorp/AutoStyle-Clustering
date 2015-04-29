class Allergies:
    """ Based on a given 'Allergy Score', provides information on specific allergens.
    """

    ALLERGENS = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, score):
        self.score = score
        self.list = []
        binstr = bin(score)[::-1][:-2]
        for i, value in enumerate(binstr):
            if value == '1':
                try:
                    self.list.append(self.ALLERGENS[i])
                except IndexError:
                    break

    def is_allergic_to(self, allergen):
        return allergen in self.list
