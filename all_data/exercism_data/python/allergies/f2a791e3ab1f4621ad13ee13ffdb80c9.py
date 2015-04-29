class Allergies(object):
    def __init__(self,i):
        self.list = []
        
        if i > 256: i = i % 256
        
        possibilities = [('eggs',1),
                         ('peanuts',2),
                         ('shellfish',4),
                         ('strawberries',8),
                         ('tomatoes',16),
                         ('chocolate',32),
                         ('pollen',64),
                         ('cats',128)]
        
        for item in reversed(possibilities):
            if i // item[1]:
                i -= item[1]
                self.list.insert(0,item[0]) 

    def is_allergic_to(self, s):
        return s in self.list
