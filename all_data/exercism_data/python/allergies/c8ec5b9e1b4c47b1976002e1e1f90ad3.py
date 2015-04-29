__author__ = 'Cedric Zhuang'

foods = ('eggs peanuts shellfish strawberries tomatoes '
         'chocolate pollen cats').split()


class Allergies:
    def __init__(self, score):
        self.allergies = []
        for v in range(len(foods)):
            if score & 2 ** v:
                self.allergies.append(foods[v])

    @property
    def list(self):
        return self.allergies

    def is_allergic_to(self, food):
        return food in self.allergies
