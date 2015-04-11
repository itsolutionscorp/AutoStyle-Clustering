ALLERGENS = 'eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split()

class Allergies:
    def __init__(self, n):
        self.list = [ALLERGENS[i] for i in range(8) if (n>>i) & 1 != 0]
    
    def is_allergic_to(self,s):
        return s in self.list
