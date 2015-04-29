class Allergies(object):

    ITEMS = {
        'eggs': 1,
        'peanuts': 2,
        'shellfish': 4,
        'strawberries': 8,
        'tomatoes': 16,
        'chocolate': 32,
        'pollen': 64,
        'cats': 128
    }

    def __init__(self, score):
        self.score = score % 256
        self.list = []

        for allergy in sorted(self.ITEMS, key=lambda s: self.ITEMS.get(s), reverse=True):
            allergy_score = self.ITEMS[allergy]
            if allergy_score <= self.score:
                self.list.append(allergy)
                self.score -= allergy_score
        self.list = sorted(self.list, key=lambda s: self.ITEMS.get(s))

    def is_allergic_to(self, allergy):
        return allergy in self.list
