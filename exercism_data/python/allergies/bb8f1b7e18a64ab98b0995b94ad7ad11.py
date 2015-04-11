class Allergies(object):
    
    type_allergies = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats"]
    
    def __init__(self, score):
        self.score = score
        self.list = [a for ind,a in enumerate(self.type_allergies) if score & 2**ind]
    
    def is_allergic_to(self,all):
        return all in self.list
