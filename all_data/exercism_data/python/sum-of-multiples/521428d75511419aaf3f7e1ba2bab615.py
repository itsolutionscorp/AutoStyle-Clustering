#!/usr/bin/env python

class SumOfMultiples(object):
    
    def __init__(self, *args):
        if not args:
            args = (3, 5)
        self.nums = (args)
    
    def to(self, n):
        multiples = {i for i in xrange(n) if any([i % j == 0 for j in self.nums])}
        return sum(multiples)
