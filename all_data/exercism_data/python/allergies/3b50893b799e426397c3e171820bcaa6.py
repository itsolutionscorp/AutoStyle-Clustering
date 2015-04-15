class Allergies:
    
    allergens = [('eggs', 1), ('peanuts', 2), ('shellfish', 4), ('strawberries', 8), ('tomatoes', 16), ('chocolate', 32), ('pollen', 64), ('cats', 128)]
    
    def __init__(self, score):
        # list contains name of all allergens identified by the score
        self.list = [x[0] for x in Allergies.allergens if x[1]&score]
        
    def is_allergic_to(self, allergen):
        return allergen in self.list
    
