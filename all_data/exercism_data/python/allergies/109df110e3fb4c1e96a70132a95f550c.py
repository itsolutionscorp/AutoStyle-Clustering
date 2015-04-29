
class Allergies:

    allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate','pollen', 'cats']
    list = []
    def __init__(self, score):
        self.allergy_score = score % 256
        self.list = self.assess_allergy_score()

    def assess_allergy_score(self):
        return [self.allergens[i] for i, v in enumerate(bin(self.allergy_score)[:1:-1]) if int(v)]

    def is_allergic_to(self, food):
        return food in self.list









