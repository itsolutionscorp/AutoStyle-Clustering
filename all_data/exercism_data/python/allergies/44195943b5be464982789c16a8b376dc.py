from collections import OrderedDict


class Allergies():

    allergy_dict = OrderedDict([
        (1, "eggs"),
        (2, "peanuts"),
        (4, "shellfish"),
        (8, "strawberries"),
        (16, "tomatoes"),
        (32, "chocolate"),
        (64, "pollen"),
        (128,  "cats"),
    ])

    def __init__(self, n):
        self.list = [allergy for (i, allergy) in self.allergy_dict.items() if n & i]

    def is_allergic_to(self, food):
        return food in self.list
