class Allergies(object):

    allergen_list = [
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
        self.score = score
    
    def is_allergic_to(self, allergen):
        return self.score & (1 << self.allergen_list.index(allergen))

    @property
    def list(self):
        return [allergen for allergen in self.allergen_list
                if self.is_allergic_to(allergen)]
