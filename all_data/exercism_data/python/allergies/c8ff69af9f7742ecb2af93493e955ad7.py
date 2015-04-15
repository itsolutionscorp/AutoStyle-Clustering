class Allergies:
    def __init__(self, score):
        allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
        self.list = [allergens[i] for i in range(len(allergens)) if int(2**i) & score]
        
    def is_allergic_to(self, allergen):
        return allergen in self.list
    
