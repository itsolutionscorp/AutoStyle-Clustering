allergy_mapping = {
    1: 'eggs',
    2: 'peanuts',
    4: 'shellfish',
    8: 'strawberries',
    16: 'tomatoes',
    32: 'chocolate',
    64: 'pollen',
    128: 'cats'
}

class Allergies():

    def __init__(self, score):
        self.score = score
        self.list = self.get_allergies()

    def largest_power(self, n):
        largest_power = 0
        while 2**largest_power <= n:
            largest_power += 1
        return 2**(largest_power - 1)

    def expand_score(self):
        expansion = []
        n = self.score
        while n != 0:
            power = self.largest_power(n)
            expansion.insert(0, power)
            n = n - power
        return expansion

    def get_allergies(self):
        allergies = []
        for power in self.expand_score():
            if power in allergy_mapping:
                allergy = allergy_mapping[power]
                allergies.append(allergy)
        return allergies

    def is_allergic_to(self, item):
        return item in self.list
