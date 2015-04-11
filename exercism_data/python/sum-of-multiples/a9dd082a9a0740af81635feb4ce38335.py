class SumOfMultiples:

    factors = (3, 5)

    def __init__(self, *args):
        if (len(args)):
            self.factors = args

    def to(self, n):
        return sum(i for i in range(n) if any(i % f == 0 for f in self.factors))
