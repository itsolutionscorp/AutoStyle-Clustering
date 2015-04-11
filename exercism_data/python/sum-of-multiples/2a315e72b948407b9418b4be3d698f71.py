class SumOfMultiples(object):

    def __init__(self, *values):
        if values == ():
            values = (3, 5)
        self.values = values

    def to(self, upper_bound):
        return sum(set([i for i in range(1, upper_bound) for j in self.values if i % j == 0]))
