class Allergies():
    
    _names = 'eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split()
    
    def __init__(self, allergies):
        def has_allergy(i):
            return allergies & 1<<i
        
        self.list = [name for i,name in enumerate(Allergies._names) if has_allergy(i)] 
 
    def is_allergic_to(self, item):
        return item in self.list
