import itertools

class Allergies:

    def __init__(self,code):
        allerg={0:'cats',1:'pollen',2:'chocolate',3:'tomatoes',4:'strawberries',5:'shellfish',6:'peanuts',7:'eggs'}

        self.list = list(map(allerg.__getitem__,reversed(list(itertools.compress(list(range(8)),list(map(int, format(code,'08b')[-8:])))))))

    def is_allergic_to(self,name):
        return name in self.list
