#!/usr/bin/env python

class SumOfMultiples:
    def __init__(self, *args):
        self.denominators = []
        for arg in args:
            self.denominators.append(arg)
        if len(self.denominators) == 0:
            self.denominators = [3,5]

    def to(self, sumto):
        full_list = range(1, sumto)
        multiples = []
        for den in self.denominators:
            for i in full_list:
                if not i % den:
                    if i in multiples:
                        continue
                    else:
                        multiples.append(i)

        return sum(multiples)
