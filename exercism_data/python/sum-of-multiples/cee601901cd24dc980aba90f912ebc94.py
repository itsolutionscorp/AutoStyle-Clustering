# -*- coding: utf-8 -*-

class SumOfMultiples(object):
    """
    """
    limit = 0
    numbers = [3, 5]
    def __init__(self, *args, **kwargs):
        super(self.__class__, self).__init__()
        self.numbers = args or self.numbers

    def to(self, limit):
        self.limit = limit
        return sum(x for x in xrange(limit)
                   if any(x % b == 0 for b in self.numbers)
                   )



