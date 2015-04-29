import numpy as np

class Allergies(object):
    
    def __init__(self, n):
        
        self.allergens = np.array(['eggs', 'peanuts', 'shellfish', 
                                   'strawberries', 'tomatoes', 'chocolate', 
                                   'pollen', 'cats'][::-1])
        self.ids = 2 ** np.arange(8)[::-1]
        
        self.list = self.__get_allegics(n)
    
    def __get_allegics(self, n):

        allegic = []
        
        n = n % 2**8    # the number should be greater than equal to 0 
                        # and less than 2**8  
        
        for i, id in enumerate(self.ids):
            if n / id > 0:
                allegic.append(i) 
            n = n % id
    
        return list(self.allergens[allegic][::-1])
            
    def is_allergic_to(self, allergen):
        
        return allergen in self.list
