class SumOfMultiples:
    def __init__(self, *factors):
        self.factors = factors if factors else [3, 5]

    def to(self, limit):
        return sum(n
                   for n in range(min(self.factors), limit)
                   if any(n % f == 0
                          for f in self.factors))
