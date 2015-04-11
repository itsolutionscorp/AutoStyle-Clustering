class Allergies():
    def __init__(self, score):
        self.list = self.build_list(score)[::-1]
        too_much = [x for x in self.list if self.list.count(x) > 1]
        [self.list.remove(x) for x in too_much]

    def is_allergic_to(self, something):
        return self.list.count(something) > 0

    def build_list(self, score):
        allergies = {'eggs': 1, 'peanuts': 2, 'shellfish': 4, 'strawberries': 8, 'tomatoes': 16, 'chocolate': 32, 'pollen': 64, 'cats': 128}.items()
        f = [y for x, y in allergies if y <= score]

        if len(f) > 0:
            elem = sorted(f)[-1]
            if elem <= score:
                selected = [x for x, y in allergies if y == elem]
                score -= elem
                return selected + self.build_list(score)

        return []
