class SumOfMultiples:
    def __init__(self, *factors):
        self.factors = ([3, 5] if len(factors) == 0 else list(factors))

    def to(self, upper):
        sum_ = 0
        for x in range(0, upper):
            for f in self.factors:
                if x % f == 0:
                    sum_ += x
                    break
        return sum_
