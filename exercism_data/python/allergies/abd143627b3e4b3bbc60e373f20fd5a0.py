class Allergies(object):
    def __init__(self, allergy_score):
        allergens = [
            'eggs',
            'peanuts',
            'shellfish',
            'strawberries',
            'tomatoes',
            'chocolate',
            'pollen',
            'cats'
        ]
        self.list = []

        binary_repr = list(bin(allergy_score)[2:])
        binary_repr.reverse()
        pairs = zip(binary_repr, allergens)

        for pair in pairs:
            if pair[0] == '1':
                self.list.append(pair[1])

    def is_allergic_to(self, allergen):
        if allergen in self.list:
            return True
