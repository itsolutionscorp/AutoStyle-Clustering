class Allergies:
    
    def __init__(self, code):
        self.code = code        
        self.allergens = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats"]

        self.list = [allergen for allergen in self.allergens if self.is_allergic_to(allergen)]
            
    def is_allergic_to(self, allergen):
        return self.code & (2 ** self.allergens.index(allergen))
        
