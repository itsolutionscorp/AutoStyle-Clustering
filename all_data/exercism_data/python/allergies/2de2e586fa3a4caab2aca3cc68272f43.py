from collections import OrderedDict


class Allergies:
    scores = [
        "eggs",
        "peanuts",
        "shellfish",
        "strawberries",
        "tomatoes",
        "chocolate",
        "pollen",
        "cats"
    ]

    def __init__(self, score):
        self.total_score = score
        
    def is_allergic_to(self, allergen):
        return self.total_score & 1 << self.scores.index(allergen)

    
    @property
    def list(self):
        allergen_list = [allergen
                for allergen in self.scores
                if self.is_allergic_to(allergen)]
        return allergen_list

        
