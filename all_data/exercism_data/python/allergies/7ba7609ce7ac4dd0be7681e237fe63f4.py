class Allergies(object):
    ITEMS_AND_SCORES = {
        "eggs": 1,
        "peanuts": 2,
        "shellfish": 4,
        "strawberries": 8,
        "tomatoes": 16,
        "chocolate": 32,
        "pollen": 64,
        "cats": 128
    }

    def __init__(self, score):
        self.score = score

    def is_allergic_to(self, item):
        if self.score & Allergies.ITEMS_AND_SCORES[item] != 0:
            return True

    @property
    def list(self):
        allergic_to = []
        for allergy, score in sorted(self.ITEMS_AND_SCORES.items(), key=lambda t: t[1]):
            if self.is_allergic_to(allergy):
                allergic_to.append(allergy)
        return allergic_to
