
class Allergies:
    _allergens = ('eggs peanuts shellfish strawberries tomatoes '
             'chocolate pollen cats').split()
    
    def __init__(self, score):
        self._score = score
        self.list = [allergen
                     for power, allergen in enumerate(self._allergens)
                     if score & 2**power]

    def is_allergic_to(self, allergen):
        return allergen in self.list
