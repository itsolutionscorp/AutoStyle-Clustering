class Allergies(object):
    

    def __init__(self,allergy_score):
    
        self.allergy_score = allergy_score
        self.binary_allergy_score = "{0:08b}".format(allergy_score)
        self.ALLERGEN_LIST = [(1,"eggs"),(2,"peanuts"),(4,"shellfish"),(8,"strawberries"),(16,"tomatoes"),(32,"chocolate"),(64,"pollen"),(128,"cats")]
        
        self.list = self.list_creator()

    def is_allergic_to(self,allergens):
 
        return allergens in self.list
    
    def list_creator(self):
        
        allergen_list = []
        binary_allergy_score_copy = self.binary_allergy_score
        for index in range(8):
            if binary_allergy_score_copy[-1] == "1":
                allergen_list.append(self.ALLERGEN_LIST[index][1])
            binary_allergy_score_copy = binary_allergy_score_copy[:-1]
        return allergen_list
