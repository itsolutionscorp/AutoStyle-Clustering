#allergies.py
#poor Tom... allergic to peanut butter AND chocolate?!


class Allergies:

    allergy_list = {
        'eggs': 1,
        'peanuts': 2,
        'shellfish': 4,
        'strawberries': 8,
        'tomatoes': 16,
        'chocolate': 32,
        'pollen': 64,
        'cats': 128,
    }
    list = []
    allergy_score = 0

    def __init__(self, score):
        self.allergy_score = score
        self.list = self.get_allergies(score)

    def get_allergies(self, score):
        allergic = []
        for allergy in self.allergy_list:
            if self.allergy_list[allergy] <= score:
                score -= self.allergy_list[allergy]
                allergic.append(allergy)
        return allergic

    def is_allergic_to(self, allergy):
        if allergy in self.list:
            return True
        else:
            return False
