__author__ = 'Cedric Zhuang'


class Allergies:
    foods = ('eggs peanuts shellfish strawberries tomatoes '
             'chocolate pollen cats').split()

    def __init__(self, score):
        self.list = [Allergies.foods[i]
                     for i in range(len(Allergies.foods))
                     if score & 2 ** i]

    def is_allergic_to(self, food):
        return food in self.list
