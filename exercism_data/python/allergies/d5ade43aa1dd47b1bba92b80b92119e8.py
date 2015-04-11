class Allergies(object):
    
    
    ALLERGEN_LIST = ["cats", "pollen", "chocolate", "tomatoes", "strawberries", "shellfish", "peanuts", "eggs"]

    def __init__(self,allergy_score):
    
        self.allergy_score = allergy_score
        self.binary_allergy_score = format(allergy_score, "08b")
        
        self.list = [allergy for bit,allergy in zip(self.binary_allergy_score[-8:], Allergies.ALLERGEN_LIST) if bit == "1"]
        self.list.reverse()

    def is_allergic_to(self,allergens):
 
        return allergens in self.list
    
