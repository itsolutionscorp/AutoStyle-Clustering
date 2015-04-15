allergens = ["eggs",     "peanuts",   "shellfish", "strawberries",
             "tomatoes", "chocolate", "pollen",    "cats"]

bitmasks = dict(zip(allergens, [2**x for x in range(0, 8)]))

class Allergies:

    def __init__(self, id):
        self.id = id
        self.list = [a for a in allergens if self.is_allergic_to(a)]

    def is_allergic_to(self, allergen):
        return self.id & bitmasks[allergen]
