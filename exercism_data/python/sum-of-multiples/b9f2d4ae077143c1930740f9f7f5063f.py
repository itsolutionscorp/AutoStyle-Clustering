class SumOfMultiples(object):

    def __init__(self, *args):
        self.factors = args or [3, 5]

    def to(self, n):
        return sum(m for m in xrange(n) if any(not bool(m%x) for x in self.factors))
