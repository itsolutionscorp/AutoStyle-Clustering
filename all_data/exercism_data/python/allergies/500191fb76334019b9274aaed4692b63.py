from collections import OrderedDict


class Allergies(object):
    def __init__(self, score):
        self.score = score
        # has to be an OrderedDict because we need the list to be sorted
        self.allergen_values = OrderedDict([
            ('eggs', 1),
            ('peanuts', 2),
            ('shellfish', 4),
            ('strawberries', 8),
            ('tomatoes', 16),
            ('chocolate', 32),
            ('pollen', 64),
            ('cats', 128)
        ])
        self.list = [a for a in self.allergen_values if self.allergen_values[a] & self.score]

    def is_allergic_to(self, allergen):
        return self.allergen_values[allergen] & self.score
