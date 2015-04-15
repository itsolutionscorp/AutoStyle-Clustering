import itertools

class Allergies:

    def __init__(self,code):
        allerg=['cats','pollen','chocolate','tomatoes','strawberries','shellfish','peanuts','eggs']

        self.list = list(map(allerg.__getitem__,reversed(list(itertools.compress(list(range(8)),list(map(int, format(code,'08b')[-8:])))))))

    def is_allergic_to(self,name):
        return name in self.list
