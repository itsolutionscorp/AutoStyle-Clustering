__author__ = 'Cedric Zhuang'

foods = ('eggs peanuts shellfish strawberries tomatoes '
         'chocolate pollen cats').split()


class Allergies:
    def __init__(self, score):
        self.list = [foods[i] for i in range(len(foods)) if score & 2 ** i]

    def is_allergic_to(self, food):
        return food in self.list
