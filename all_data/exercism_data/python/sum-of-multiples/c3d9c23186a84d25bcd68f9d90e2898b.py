class SumOfMultiples(object):
    def __init__(self, *args):
        if len(args) == 0:
            self.factors = (3, 5)
        else:
            self.factors = tuple(args)

    def to(self, num):
        numbers = [ x for x in xrange(1, num) if any( x % f == 0 for f in self.factors ) ]
        return sum(numbers)
