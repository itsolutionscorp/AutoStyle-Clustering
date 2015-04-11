class Allergies:
    allergens = ["eggs", "peanuts", "shellfish",
                 "strawberries", "tomatoes", "chocolate",
                 "pollen", "cats"]

    def __init__(self, score=0):
        self.list = []
        for n in reversed(xrange(8)):
            if score & 1 << n:
                self.list.insert(0, self.allergens[n])

    def is_allergic_to(self, allergen):
        return allergen in self.list
