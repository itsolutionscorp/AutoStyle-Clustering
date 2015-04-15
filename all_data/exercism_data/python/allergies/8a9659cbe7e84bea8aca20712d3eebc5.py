class Allergies():
    _ALLERGENS = ("eggs peanuts shellfish strawberries " 
                    "tomatoes chocolate pollen cats").split()

    def __init__(self, score):
        self.score = score

    def is_allergic_to(self, item):
        return item in self.list

    @property 
    def list(self):
        return [ 
                allergen for index, allergen 
                in enumerate(self._ALLERGENS) 
                if 2**index & self.score 
                ]
