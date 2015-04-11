__author__ = 'Cedric Zhuang'


def sum_of_multiples(n, factor=None):
    if not factor: factor = [3, 5]
    filter_func = FilterFunc(factor)
    return sum(filter(filter_func.filter, xrange(n)))


class FilterFunc(object):
    def __init__(self, factor):
        self.factor = filter(lambda x: x != 0, factor)

    def filter(self, n):
        return any(map(lambda x: n % x == 0, self.factor))
