
class Allergies:

    allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate','pollen', 'cats']
    list = []
    def __init__(self, score):
        self.allergy_score = score % 256
        self.list = self.assess_allergy_score()

    def assess_allergy_score(self):
        allergy_list = []
        score = str(bin(self.allergy_score).lstrip("-0b"))[::-1]
        for i,c in enumerate(score):
            if c == '1':
                allergy_list.append(self.allergens[i])
        return allergy_list

    def is_allergic_to(self, food):
        return food in self.list







