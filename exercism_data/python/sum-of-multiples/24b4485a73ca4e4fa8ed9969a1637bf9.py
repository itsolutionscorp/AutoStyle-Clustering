class SumOfMultiples:
    multiples = [3,5]

    def __init__(self, *args):
        if len(args):
            self.multiples = args

    def to(self, n):
        valuestosum = set()
        for multiple in self.multiples:
            valuestosum = valuestosum.union(set(filter(lambda x: not x%multiple, xrange(1, n))))
        return sum(valuestosum)
