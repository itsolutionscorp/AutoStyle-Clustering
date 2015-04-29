class Allergies:
    ALLERGENS = [('cats',128),('pollen',64),('chocolate',32),('tomatoes',16),
                 ('strawberries',8),('shellfish',4),('peanuts', 2),('eggs', 1)
                 ]

    def __init__(self, allergyScore):
        self.list = []
        allergyScore = allergyScore % 256
        for a, b in Allergies.ALLERGENS:
            if allergyScore >= b:
                self.list.insert(0, a)
                allergyScore -= b
            
        
    def is_allergic_to(self, allergen):
        allergen = allergen.lower()
        return sum(1 for x in self.list if allergen == x)
