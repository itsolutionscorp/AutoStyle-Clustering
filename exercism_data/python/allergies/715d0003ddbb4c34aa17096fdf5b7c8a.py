from collections import OrderedDict

class Allergies:
    items = OrderedDict([
        ('eggs', 1),
        ('peanuts', 2),
        ('shellfish', 4),
        ('strawberries', 8),
        ('tomatoes', 16),
        ('chocolate', 32),
        ('pollen', 64),
        ('cats', 128)
    ])
    
    def __init__(self, score):
        self.score = score
        self.list = self.get_allergic_list()
        
    def is_allergic_to(self, item):
        if item in Allergies.items:
            return Allergies.items[item] & self.score
        else:
            return False
            
    def get_allergic_list(self):
        results = []
        for item in Allergies.items:            
            if self.is_allergic_to(item):
                results.append(item)
        return results        
