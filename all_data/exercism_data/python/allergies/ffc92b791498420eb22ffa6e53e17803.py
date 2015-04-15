from collections import OrderedDict

class Allergies:
    score = 0
    list = []
    allergyDict = OrderedDict ([('eggs', 1), ('peanuts', 2), ('shellfish', 4),
                   ('strawberries', 8), ('tomatoes', 16), ('chocolate', 32),
                   ('pollen', 64), ('cats', 128)])

    def __init__(self, score=0):
        self.score = score
        for food in self.allergyDict:
            if self.is_allergic_to(food):
                self.list.append(food)
    
    def is_allergic_to(self, food):
        return self.score & self.allergyDict[food] > 0
