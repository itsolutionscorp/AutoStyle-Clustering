from collections import OrderedDict

ALLERGY_SCORES = OrderedDict()

ALLERGY_SCORES["eggs"] = 1
ALLERGY_SCORES["peanuts"] = 2
ALLERGY_SCORES["shellfish"] = 4
ALLERGY_SCORES["strawberries"]= 8
ALLERGY_SCORES["tomatoes"] = 16
ALLERGY_SCORES["chocolate"] = 32
ALLERGY_SCORES["pollen"] = 64
ALLERGY_SCORES["cats"] = 128


class Allergies:
    def __init__(self, score):
        self.score = score
        self.list = []
        self.assessment()

    def is_allergic_to(self, allergen):
        return allergen in self.list

    def assessment(self):
        if self.score > 255:
            self.list = ["eggs"]
            return

        rem = self.score
        for k,v in reversed(ALLERGY_SCORES.items()):
            if rem / v >= 1 :
                self.list.append(k)
                rem -= v
        self.list = self.list[::-1]
