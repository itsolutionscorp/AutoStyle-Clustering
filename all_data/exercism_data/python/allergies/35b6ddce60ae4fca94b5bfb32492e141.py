class Allergies:

    allergens = "eggs peanuts shellfish strawberries tomatoes chocolate pollen cats".split()

    def __init__(self, score):
        self.score = score
        self.list = []

        i = 0

        while (2 ** i) <= score and i < len(self.allergens):
            if (self.score & (2 ** i)) > 0:
                self.list.append(self.allergens[i])
            i += 1

    def is_allergic_to(self, food):
        return food in self.list
