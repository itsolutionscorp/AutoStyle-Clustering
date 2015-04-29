class Allergies:
    allergens = ('eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats')
    
    def __init__(self, code):
        self.list = [ self.allergens[i] for i in xrange(8) if code & 2 ** i ]
    
    def is_allergic_to(self, allergen):
        return allergen in self.list
    
