from math import log

class Allergies:
    
    all_allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, allergy_score):
        self.list = []
        score = allergy_score % 256
        while score > 0:
            index = int(log(score, 2))
            self.list.insert(0, Allergies.all_allergens[index])
            score -= 2**index

    def is_allergic_to(self, allergen):
        return allergen in self.list
