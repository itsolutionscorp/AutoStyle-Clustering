allergies = {1: 'eggs', 2: 'peanuts', 4: 'shellfish', 8: 'strawberries', 16: 'tomatoes', 32: 'chocolate', 64: 'pollen', 128: 'cats'}

class Allergies():
    def __init__(self, score):
        self.list = self.build_list(score)

    def is_allergic_to(self, something):
        return self.list.count(something) > 0

    def build_list(self, score):
        return [allergies[x] for x in sorted(allergies.keys()) if score & x == x]
