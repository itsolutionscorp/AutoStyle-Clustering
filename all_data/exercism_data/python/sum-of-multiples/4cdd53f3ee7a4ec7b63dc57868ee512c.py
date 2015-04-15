from itertools import izip_longest

class SumOfMultiples(object):

    def __init__(self, *factors):
        self.factors = factors or (3, 5)

    def multiples(self, limit):
        for n in xrange(limit):
            if any(n % x == 0 for x in self.factors):
                yield n

    def to(self, limit):
        return sum(self.multiples(limit))
