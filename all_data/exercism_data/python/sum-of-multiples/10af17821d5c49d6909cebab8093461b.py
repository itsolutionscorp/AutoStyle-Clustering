from itertools import izip_longest

class SumOfMultiples(object):

    def __init__(self, *factors):
        self.factors = factors or (3, 5)

    def multiples(self, limit):
        for n in xrange(limit):
            for factor in self.factors:
                if n % factor == 0:
                    yield n
                    break

    def to(self, limit):
        return sum(self.multiples(limit))
