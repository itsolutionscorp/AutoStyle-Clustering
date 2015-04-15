class Allergies:
    items = dict(zip([2**i for i in range(8)],
                     ['eggs', 'peanuts', 'shellfish', 'strawberries',
                      'tomatoes', 'chocolate', 'pollen', 'cats']))

    def __init__(self, score):
        self.score = score
        self.list = []

        while score > 256:
            score -= 256

        for n in [2**i for i in reversed(range(8))]:
            if score - n >= 0:
                self.list.insert(0, self.items[n])
                score -= n

    def is_allergic_to(self, item):
        return item in self.list
