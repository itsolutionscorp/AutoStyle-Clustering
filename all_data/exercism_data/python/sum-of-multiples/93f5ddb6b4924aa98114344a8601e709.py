class SumOfMultiples():
    def __init__(self, *bases):
        if len(bases)==0:
            bases = (3,5)
        self.bases = bases

    def to(self, limit):
        results = []
        for multiplier in range(1,limit):
            for base in self.bases:
                if base*multiplier<limit:
                    results.append(base*multiplier)
        return sum(results)
