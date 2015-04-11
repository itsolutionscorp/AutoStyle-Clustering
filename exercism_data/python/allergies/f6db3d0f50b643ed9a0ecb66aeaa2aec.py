class Allergies(object):
    ALLERGENS = [ 'eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes',
                  'chocolate', 'pollen', 'cats' ]
    
    def __init__(self, score):
        self.score = score
        self.list = self._match_allergens()
        
    def is_allergic_to(self, allergen):
        return self.list.count(allergen) == 1
        
    def _match_allergens(self):
        allergen_list = []
        for i in range(len(self.ALLERGENS)):
            if self.score & 2**i == 2**i:
                allergen_list.append(self.ALLERGENS[i])
        return allergen_list
