class Allergies(object):
    allergy_values = ['eggs', 'peanuts', 'shellfish', 'strawberries',
                      'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, score):
        self.score = score
        self.list = [Allergies.allergy_values[i] for
                     i, x in enumerate(bin(self.score % 256)[::-1]) if x == '1']

    def is_allergic_to(self, allergen):
        return allergen in self.list
