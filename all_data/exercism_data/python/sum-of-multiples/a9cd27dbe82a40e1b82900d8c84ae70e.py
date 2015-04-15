import itertools


def bounded(iterable, n):
    return itertools.takewhile(lambda x: x < n, iterable)


def multiples_of(factor, up_to):
    return bounded(itertools.count(factor, step=factor), up_to)


def unique(iterables):
    return set(itertools.chain.from_iterable(iterables))


class SumOfMultiples(object):
    def __init__(self, *factors):
        self.factors = factors if factors else (3, 5)

    def to(self, n):
        return sum(unique(multiples_of(f, up_to=n) for f in self.factors))
