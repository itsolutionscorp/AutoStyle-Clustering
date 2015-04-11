from collections import OrderedDict


class Allergies:
    allergy = OrderedDict([("eggs", 1),
                          ("peanuts", 2),
                          ("shellfish", 4),
                          ("strawberries", 8),
                          ("tomatoes", 16),
                          ("chocolate", 32),
                          ("pollen", 64),
                          ("cats", 128)])

    def __init__(self, score):
        self.score=score
        self.list=[allergen for allergen in self.allergy if self.is_allergic_to(allergen)]

    def is_allergic_to(self, allergen):
        return self.score & self.allergy[allergen]
