class SumOfMultiples:
    def __init__(self, *args):
        self.factors = args or [3, 5]
        if any(factor <= 1 for factor in self.factors):
            raise ValueError("Invalid factor")

    def to(self, n):
        s = set()
        for p in self.factors:
            s.update(i for i in range(p, n, p))
        return sum(s)
