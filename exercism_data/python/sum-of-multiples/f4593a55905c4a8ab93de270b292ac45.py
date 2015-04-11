class SumOfMultiples:

    def __init__(self, *factors):
        self._factors = factors or (3, 5)

    def to(self, to_number):
        multiples = set()
        for factor in self._factors:
            multiples.update(xrange(factor, to_number, factor))
        return sum(multiples)
