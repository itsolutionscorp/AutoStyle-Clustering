from collections import OrderedDict


class Allergies():
    def __init__(self, score):
        self.score = score
        self.D = OrderedDict([("eggs", 1),
                              ("peanuts", 2),
                              ("shellfish", 4),
                              ("strawberries", 8),
                              ("tomatoes", 16),
                              ("chocolate", 32),
                              ("pollen", 64),
                              ("cats", 128)])

    def is_allergic_to(self, itemx):
        return bool(self.score & self.D[itemx])

    @property
    def list(self):
        return [x
                for x in self.D
                if self.is_allergic_to(x)]
