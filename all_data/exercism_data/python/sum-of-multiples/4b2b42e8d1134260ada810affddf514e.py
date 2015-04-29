class SumOfMultiples(object):
    def __init__(self, *args):
        if not args:
            args = (3, 5)
        self.factors = args

    def to(self, n):
        # adds a number twice. Hacky solution: transform list to a set.
        m = [i for i in xrange(1, n) for x in self.factors if not i % x or not i % x]
        return sum(set(m))
