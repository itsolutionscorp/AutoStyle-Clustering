class SumOfMultiples(object):

    def __init__(self, *args):
        self.factors = args if args else [3, 5]

    def to(self, n):
        return sum(m for m in xrange(n) if self._isMultiple(m))

    def _isMultiple(self, m):
        '''True if n is a multiple of any of the factors'''
        return reduce(lambda x,y: x or not bool(m%y), self.factors, False)
