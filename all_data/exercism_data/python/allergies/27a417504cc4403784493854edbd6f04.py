from collections import OrderedDict

class Allergies():
    def __init__(self, n):
        self.allergiesDict = OrderedDict([("eggs", 1),
                                          ("peanuts", 2),
                                          ("shellfish", 4),
                                          ("strawberries", 8),
                                          ("tomatoes", 16),
                                          ("chocolate", 32),
                                          ("pollen", 64),
                                          ("cats", 128)])
        self.n = n
        self.list = [food for food in self.allergiesDict
                     if self.is_allergic_to(food)]

    def is_allergic_to(self, food):
        a = self.allergiesDict[food]
        return self.n & a == a
