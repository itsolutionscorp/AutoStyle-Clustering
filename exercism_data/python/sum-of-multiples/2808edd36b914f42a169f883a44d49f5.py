__author__ = 'jimblackler'


class SumOfMultiples(object):
    def __init__(self, *multiples):
        if len(multiples):
            self.multiples = multiples
        else:
            self.multiples = [3, 5]

    def is_multiple(self, n):
        for m in self.multiples:
            if n % m == 0:
                return True
        return False

    def to(self, n):
        return sum([x for x in xrange(n) if self.is_multiple(x)])
