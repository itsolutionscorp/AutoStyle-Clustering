from collections import OrderedDict

ALLERGY_SCORES = OrderedDict([('eggs', 1), ('peanuts', 2), ('shellfish', 4), ('strawberries', 8), ('tomatoes', 16),
                              ('chocolate', 32), ('pollen', 64), ('cats', 128)])

class Allergies:
    def __init__(self, score):
        self.score = score
        self.list = [k for k,v in ALLERGY_SCORES.items() if v | score == score]

    def is_allergic_to(self, allergy):
        return (self.score | ALLERGY_SCORES[allergy]) == self.score
