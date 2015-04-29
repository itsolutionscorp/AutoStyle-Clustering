class Allergies:
    
    def __init__(self, code):
        self.code = code        
        
    @property
    def list(self):
        allergens = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats"]
        return [allergen for allergen 
            in allergens 
            if self.code & (2 ** allergens.index(allergen))]
            
    def is_allergic_to(self, allergen):
        return allergen in self.list
        
