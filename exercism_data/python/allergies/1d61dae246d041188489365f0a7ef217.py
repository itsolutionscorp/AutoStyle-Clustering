from collections import OrderedDict


class Allergies:
    SCORES = OrderedDict([
        ('eggs', 1),
        ('peanuts', 2),
        ('shellfish', 4),
        ('strawberries', 8),
        ('tomatoes', 16),
        ('chocolate', 32),
        ('pollen', 64),
        ('cats', 128)
    ])

    def __init__(self, score):
        self.score = score
        self.list = self.all_allergies()

    def is_allergic_to(self, allergy):
        allergy_score = Allergies.SCORES[allergy]
        return self.score & allergy_score == allergy_score

    def all_allergies(self):
        result = []
        for allergy in Allergies.SCORES:
            if self.is_allergic_to(allergy):
                result.append(allergy)
        return result
