
class Allergies:

    allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate','pollen', 'cats']
    list = []
    def __init__(self, score):
        self.allergy_score = score % 256
        self.list = self.assess_allergy_score()

    def assess_allergy_score(self):
        allergy_list = []
        # binary conversion of the score adds an eighth bit on the end
        # since we're only checking for 7 different allergens , we remove the last character
        score = str(bin(self.allergy_score))[::-1]
        for i,c in enumerate(score):
            if c == '1':
                allergy_list.append(self.allergens[i])
        return allergy_list

    def is_allergic_to(self, food):
        return food in self.list







