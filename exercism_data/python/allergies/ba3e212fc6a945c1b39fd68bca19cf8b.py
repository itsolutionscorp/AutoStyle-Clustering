class Allergies:
    tested = [
        'eggs',
        'peanuts',
        'shellfish',
        'strawberries',
        'tomatoes',
        'chocolate',
        'pollen',
        'cats'
    ]
    def __init__(self, score):
        self.score = score
    def is_allergic_to(self, agn):
        return bool(self.score>>Allergies.tested.index(agn)&1)
    def names(self):
        return [Allergies.tested[i]
            for i in range(len(Allergies.tested)) if self.score>>i&1]
    list = property(names)
