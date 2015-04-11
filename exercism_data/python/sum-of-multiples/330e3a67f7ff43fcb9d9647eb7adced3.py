class SumOfMultiples:
    def __init__(self, *factors):
        if not factors:
            factors = (3, 5)
        self.factors = factors

    def to(self, end):
        return sum((n for n in range(end) if
                    any((not (n % factor) for factor in self.factors))))
