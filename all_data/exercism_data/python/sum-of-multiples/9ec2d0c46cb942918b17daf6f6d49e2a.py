class SumOfMultiples(object):

    def __init__(self, *factors):
        self.factors = factors or (3, 5)

    def to(self, limit):
        multiples = set()
        for factor in self.factors:
            multiples.update(range(factor, limit, factor))
        return sum(multiples)
