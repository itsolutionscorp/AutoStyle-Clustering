#!/usr/bin/python
# exercism python 14: sum of multiples

class SumOfMultiples(object):
    def __init__(self, *argv):
        self.factors = argv or [3,5]
 
    def to(self, limit):
        multiples = set()
        for factor in self.factors:
            for value in xrange(1, limit):
                if float(value) % factor == 0:
                    multiples.add(value)
        return sum(multiples)
            
