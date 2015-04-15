import numpy as np

class Allergies(object):
    
    def __init__(self, n):
        
        self.allergens = np.array(['eggs', 'peanuts', 'shellfish', 
                                   'strawberries', 'tomatoes', 'chocolate', 
                                   'pollen', 'cats'])
        self.m = len(self.allergens) 
        
        self.list = self.__get_allergics(n)
    
    def __get_allergics(self, n):
        
        n = n % 2**self.m    # the number should be greater than equal to 0 
                             # and less than 2**8
        
        bit_flag = bin(n)[2:][::-1]
        
        allergics = []
        for i, b in enumerate(bit_flag):
            if b == '1':
                allergics.append(self.allergens[i])

        return allergics 
            
    def is_allergic_to(self, allergen):
        
        return allergen in self.list
