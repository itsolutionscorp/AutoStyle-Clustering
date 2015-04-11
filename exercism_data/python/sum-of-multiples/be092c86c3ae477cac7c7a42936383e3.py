class SumOfMultiples:
    def __init__(self, *factors):
        if factors:
            self.factors = factors
        else:
            self.factors = [3, 5]

    def to(self, end):
        return sum([n for n in range(end) if self._ismultiple(n)])

    def _ismultiple(self, n):
        for factor in self.factors:
            if not (n % factor):
                return True
        return False
