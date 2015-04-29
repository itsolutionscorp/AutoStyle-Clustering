# Allergies

class Allergies(object):
    def __init__(self, score):
        self.list = self.calculate_allergies(score) 
        
    def calculate_allergies(self, score):
        allergies = ['cats', 'pollen', 'chocolate', 'tomatoes',
                     'strawberries', 'shellfish', 'peanuts', 'eggs']          
                             
        result = []
        string_bin_score = str(bin(score))
        length_string_bin_score = len(string_bin_score) - 2
        
        if length_string_bin_score > 8:
            length_string_bin_score = 8
        
        for i in range(-1, ((-(length_string_bin_score)) -1), -1):
            if string_bin_score[i] == "1":
                result.append(allergies[i])
        
        return result
    
    def is_allergic_to(self, item):
        return item in self.list
