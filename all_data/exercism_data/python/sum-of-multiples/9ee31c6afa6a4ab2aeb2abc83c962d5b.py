class SumOfMultiples:

    factors = (3, 5)

    def to(self, n):
        return sum(i for i in range(n) if all(i % f == 0 for f in self.factors))
