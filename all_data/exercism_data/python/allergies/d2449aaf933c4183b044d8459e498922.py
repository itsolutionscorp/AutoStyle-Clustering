class Allergies:
    allergens = ["eggs", "peanuts", "shellfish",
                 "strawberries", "tomatoes", "chocolate",
                 "pollen", "cats"]
    list = []

    def __init__(self, score):
        if score > 256:
            score = score % 256
        allergies = []
        for i, allergen in reversed(list(enumerate(self.allergens))):
            iteration = 1 if i == 0 else 2 ** i
            if score - iteration > -1:
                allergies.append(allergen)
                score -= iteration
        self.list = list(reversed(allergies))

    def is_allergic_to(self, allergen):
        if allergen in self.list:
            return True
        return False
