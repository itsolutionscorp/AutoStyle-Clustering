ALLERGIES = {
    'eggs': 1,
    'peanuts': 2,
    'shellfish': 4,
    'strawberries': 8,
    'tomatoes': 16,
    'chocolate': 32,
    'pollen': 64,
    'cats': 128
}

class Allergies(object):

    def __init__(self, score):
        self.score = score
        self.allergies = []

    def is_allergic_to(self, allergy_to_test):
        if self.score - ALLERGIES[allergy_to_test] >= 0:
            return True
        else:
            return False


        return allergy_to_test in self.allergies

    @property
    def list(self):
        for allergy in sorted(ALLERGIES.keys(), key=lambda key: ALLERGIES[key]):
            if self.is_allergic_to(allergy):
                self.allergies.append(allergy)
                self.score -= ALLERGIES[allergy]
                print '*****'
                print self.score
                print '*****'

                if self.score > 255 or self.score <= 1:
                    return self.allergies
        return sorted(self.allergies, key=lambda allergy: ALLERGIES[allergy])
