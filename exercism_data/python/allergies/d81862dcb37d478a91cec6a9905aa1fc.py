class Allergies:
    ALLERGENS = [('eggs', 1),('peanuts', 2),('shellfish',4),('strawberries',8),
                 ('tomatoes',16),('chocolate',32),('pollen',64),('cats',128)
                 ]

    def __init__(self, allergyScore):
        self.list = []
        for a, b in Allergies.ALLERGENS:
            if allergyScore & b:
                self.list.append(a)
            
        
    def is_allergic_to(self, allergen):
        allergen = allergen.lower()
        return allergen in self.list
