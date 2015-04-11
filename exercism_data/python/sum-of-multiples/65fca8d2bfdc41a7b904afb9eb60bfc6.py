import unittest

class SumOfMultiples(object):
    def __init__(self, *args):
        if not args:
            args = (3, 5)
        self.args = args

    def to(self, limit):
        multiples = set()
        for number in self.args:
            multiples.update(xrange(number, limit, number))
        return sum(multiples)
