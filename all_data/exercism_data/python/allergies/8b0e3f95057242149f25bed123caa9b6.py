import numpy as np

class Allergies(object):
    
    def __init__(self, n):
        
        self.allergens = np.array(['eggs', 'peanuts', 'shellfish', 
                                   'strawberries', 'tomatoes', 'chocolate', 
                                   'pollen', 'cats'])
        self.m = len(self.allergens) 
        
        self.ids = 2 ** np.arange(8)
        
        self.list = self.__get_allergics(n)
    
    def __get_allergics(self, n):

        allegic = []
        
        n = n % 2**self.m    # the number should be greater than equal to 0 
                             # and less than 2**8  
        
        for i in range(self.m)[::-1]:
            id = self.ids[i]
            if n / id > 0:
                allegic[:0] = [i]     # prepend
            n = n % id
    
        return list(self.allergens[allegic])
            
    def is_allergic_to(self, allergen):
        
        return allergen in self.list
