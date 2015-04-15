from itertools import chain


class SumOfMultiples(object):

    def __init__(self, *args):
        self.nums = args if args else [3, 5]

    def to(self, upper):
        ranges = [range(0, upper, n) for n in self.nums]
        return sum(set(chain(*ranges)))
