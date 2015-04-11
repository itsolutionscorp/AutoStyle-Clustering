class SumOfMultiples(object):
    def __init__(self, *args):
        self.n = args if args else [3, 5]

    def to(self, x):
        return sum(i for i in range(min(self.n), x) if any(i % f == 0 for
            f in self.n))

