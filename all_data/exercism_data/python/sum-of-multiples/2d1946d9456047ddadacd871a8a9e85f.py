class SumOfMultiples:

    def __init__(self, *args):
        if not args:
            self.multiples = (3, 5)
        else:
            self.multiples = args

    def to(self, n):
        return sum([i for i in range(1, n) if
                   any([i % m == 0 for m in self.multiples])])
