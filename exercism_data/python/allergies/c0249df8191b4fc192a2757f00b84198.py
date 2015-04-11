from collections import OrderedDict

class Allergies(object):
    allergies = OrderedDict([('eggs', 1), ('peanuts', 2), ('shellfish', 4),
                    ('strawberries', 8), ('tomatoes', 16), ('chocolate', 32),
                    ('pollen', 64), ('cats', 128)])
    
    def __init__(self, score):
        self.score = score
            
    def is_allergic_to(self, allergy):
        return bool(self.score & self.allergies[allergy]) 
    
    @property
    def list(self):
       return [key for key in self.allergies.keys() if self.is_allergic_to(key)]
