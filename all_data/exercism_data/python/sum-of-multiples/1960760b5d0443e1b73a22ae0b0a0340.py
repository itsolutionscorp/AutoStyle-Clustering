# -*- coding: utf-8 -*-

class SumOfMultiples(object):
    """
    """
    limit = 0
    numbers = [3, 5]
    multiples = []
    def __init__(self, *args, **kwargs):
        super(self.__class__, self).__init__()
        if args:
            self.numbers = [x for x in args]

    def to(self, limit):
        self.limit = limit
        self.multiples = []
        for number in xrange(self.limit):
            for x in self.numbers:
                if number%x==0:
                    self._add_to_multiples(number)

        return sum(self.multiples)

    def _add_to_multiples(self, number):
        if not number in self.multiples:
            self.multiples.append(number)


