class Allergies:
    all_allergies = ['eggs',
                    'peanuts',
                    'shellfish',
                    'strawberries',
                    'tomatoes',
                    'chocolate',
                    'pollen',
                    'cats']

    def __init__(self, allergy_score):
        self.list = []

        while allergy_score >= 256:
            allergy_score -= 256

        for i in reversed(xrange(0,8)):
            if allergy_score >= 2**i:
                self.list.append(self.all_allergies[i])
                allergy_score -= 2 ** i
        self.list = self.list[::-1]

    def is_allergic_to(self, allergen):
        return allergen in self.list
