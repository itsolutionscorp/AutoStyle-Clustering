from collections import OrderedDict
class Allergies(object):
    list = []
    allergies = OrderedDict([('eggs', 1),
                            ('peanuts', 2),
                            ('shellfish', 4),
                            ('strawberries', 8),
                            ('tomatoes', 16),
                            ('chocolate', 32),
                            ('pollen', 64),
                            ('cats', 128)])

    def __init__(self, score):
        self.score = score

    def is_allergic_to(self, allergen):
        return bool(self.score & self.allergies[allergen])

    @property
    def list(self):
        return [allergen
                for allergen in self.allergies
                if self.is_allergic_to(allergen)]
