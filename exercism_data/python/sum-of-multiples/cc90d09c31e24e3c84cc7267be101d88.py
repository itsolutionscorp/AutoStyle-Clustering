class SumOfMultiples(object):
    def __init__(self, *denominators):
        if denominators is ():
            self.denominators = (3, 5)
        else:
            self.denominators = denominators

    def to(self, maxnumber):
        return sum(i for i in range(maxnumber) if any(i % j == 0 for j in self.denominators))
