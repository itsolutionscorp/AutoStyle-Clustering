from collections import OrderedDict

ALLERGY_SCORES = OrderedDict([("eggs", 1), ("peanuts", 2), ("shellfish", 4),
                ("strawberries", 8), ("tomatoes", 16), ("chocolate", 32),
                ("pollen", 64), ("cats", 128)])

class Allergies:
    def __init__(self, score):
        self.score = score
        self.list = []
        self.assessment()

    def is_allergic_to(self, allergen):
        return allergen in self.list

    def assessment(self):
        self.score %= 256

        rem = self.score
        for k,v in reversed(ALLERGY_SCORES.items()):
            if rem / v >= 1 :
                self.list.append(k)
                rem -= v
        self.list = self.list[::-1]
