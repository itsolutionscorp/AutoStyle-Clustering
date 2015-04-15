class Allergies:
    allergens = [
        'eggs',
        'peanuts',
        'shellfish',
        'strawberries',
        'tomatoes',
        'chocolate',
        'pollen',
        'cats' ]

    def __init__(self, score):
        score = score % 256
        self.list = []
        for i in range(len(Allergies.allergens)):
            if (1 << i) & score != 0:
                self.list.append(Allergies.allergens[i])

    def is_allergic_to(self, thing):
        return thing in self.list
