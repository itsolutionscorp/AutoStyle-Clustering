class Allergies():
    def __init__(self, score):
        self.score = score
        self.list = self.get_allergies()

    def is_allergic_to(self, item):
        return item in self.list

    def get_allergies(self):
        return [ 
                allergen for index, allergen 
                in enumerate(self._ALLERGENS) 
                if 2**index & self.score 
                ]
    
    _ALLERGENS = "eggs peanuts shellfish strawberries tomatoes chocolate pollen cats".split()