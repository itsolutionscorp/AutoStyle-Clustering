from math import sqrt


class Allergies:
    def __init__(self, number):
        self.codes = {
            1: 'eggs', 2: 'peanuts', 4: 'shellfish', 8: 'strawberries',
            16: 'tomatoes', 32: 'chocolate', 64: 'pollen', 128: 'cats'}
        self.list = self.allergies(number)

    def allergies(self, number):
        a = [2 ** i for i in xrange(max(8, int(sqrt(number))))]
        b = []
        for i in a[::-1]:
            if number >= i:
                if i in self.codes:
                    b.append((self.codes[i], i))
                number -= i
        b.sort(key=lambda x: x[1])
        return [i[0] for i in b]

    def is_allergic_to(self, allergen):
        return allergen in self.list
