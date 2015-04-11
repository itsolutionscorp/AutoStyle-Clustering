ALLERGENS = 'eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split()

class Allergies:
    def __init__(self, n):
        n_str = format(n,'08b')[-8:]
        self.list = [ALLERGENS[i] for i in range(8) if n_str[7-i] == '1']
    
    def is_allergic_to(self,s):
        return s in self.list
