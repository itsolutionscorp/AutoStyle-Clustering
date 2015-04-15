import itertools

class Allergies:

    def __init__(self,code):
        allerg=['cats','pollen','chocolate','tomatoes','strawberries','shellfish','peanuts','eggs']

        self.list = list(reversed(list(itertools.compress(allerg,list(map(int, format(code,'08b')[-8:]))))))

    def is_allergic_to(self,name):
        return name in self.list
