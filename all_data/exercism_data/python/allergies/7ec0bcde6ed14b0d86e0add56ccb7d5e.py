from collections import OrderedDict

class Allergies:
    "Inputs: Allergy score"

    def __init__(self, score):
        self.score = score
        self._allergydictionary = {
            'eggs': 1,
            'peanuts': 1<<1,
            'shellfish': 1<<2,
            'strawberries': 1<<3,
            'tomatoes': 1<<4,
            'chocolate': 1<<5,
            'pollen': 1<<6,
            'cats': 1<<7
        }
        self.ordereddictionary = OrderedDict(sorted
                                (self._allergydictionary.items(),
                                    key=lambda t: t[1]))
        self.list = [x for x in self.ordereddictionary if
                    self.is_allergic_to(x)]



    def is_allergic_to(self, thing):
        return self.score & self._allergydictionary[thing] != 0



