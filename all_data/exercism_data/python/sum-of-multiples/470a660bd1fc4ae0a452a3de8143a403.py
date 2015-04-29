class SumOfMultiples():
    def __init__(self, *bases):
        if not bases:
            bases = (3,5)
        self.bases = bases

    def to(self, limit):
        results = set()
        for multiplier in range(1,limit):
            for base in self.bases:
                if base*multiplier<limit:
                    results.add(base*multiplier)
        return sum(results)
