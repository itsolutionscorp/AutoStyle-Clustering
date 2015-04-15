class SumOfMultiples:

    factors = (3, 5)

    def to(self, n):
        return sum(i for i in range(n) if i % 3 == 0 or i % 5 == 0)
