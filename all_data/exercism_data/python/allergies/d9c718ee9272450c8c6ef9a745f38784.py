import itertools


class Allergies:

    _allergy_scores = [
        ('eggs', 1),
        ('peanuts', 2),
        ('shellfish', 4),
        ('strawberries', 8),
        ('tomatoes', 16),
        ('chocolate', 32),
        ('pollen', 64),
        ('cats', 128),
    ]

    def __init__(self, score):
        self.score = score

    def is_allergic_to(self, allergen):
        return allergen in self.list

    @property
    def list(self):
        return [k for k, v in self._allergy_scores
                if self.score | v == self.score]
