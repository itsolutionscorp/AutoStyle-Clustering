class Allergies:
    SCORES = {
            128: 'cats',
            64: 'pollen',
            32: 'chocolate',
            16: 'tomatoes',
            8: 'strawberries',
            4: 'shellfish',
            2: 'peanuts',
            1: 'eggs'
    }

    def __init__(self, score):
        self.score = self._normalize_score(score)
        self.list = self._determine_allergies()

    def is_allergic_to(self, substance):
        return substance in self.list



    def _determine_allergies(self):
        allergies = list()
        remainder = self.score
        for score, allergy in reversed(sorted(self.SCORES.items())):
            if score <= remainder:
                allergies.append(allergy)
                remainder %= score
        return list(reversed(allergies))

    def _normalize_score(self, score):
        while score > 255:
            score -= 256
        return score
