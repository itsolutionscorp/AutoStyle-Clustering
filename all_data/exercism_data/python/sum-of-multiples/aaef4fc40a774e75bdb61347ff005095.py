class SumOfMultiples(object):

    def __init__(self, factor1=3, factor2=5, *factors):
        self.factors = (factor1, factor2) + factors

    def multiples(self, limit):
        for factor in self.factors:
            for multiple in range(factor, limit, factor):
                yield multiple

    def to(self, limit):
        return sum(self.multiples(limit))
