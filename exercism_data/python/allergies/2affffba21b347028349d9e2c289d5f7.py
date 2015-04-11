class Allergies:
    allergens = ["eggs", "peanuts", "shellfish",
                 "strawberries", "tomatoes", "chocolate",
                 "pollen", "cats"]
    list = []

    def __init__(self, score=0):
        self.list = []
        score = score % 256
        for i, allergen in reversed(list(enumerate(self.allergens))):
            iteration = 1 if i == 0 else 2 ** i
            if score - iteration > -1:
                self.list.insert(0, allergen)
                score -= iteration

    def is_allergic_to(self, allergen):
        if allergen in self.list:
            return True
        return False
