class Allergies:
    def __init__(self, n):
        allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
        bits = bin(n)
        tests = min(len(bits)-2, len(allergens))
        self.list = [allergens[i] for i in range(tests) if bits[-(i+1)] == '1']
            
    def is_allergic_to(self, allergen):
        return allergen in self.list
    
