class SumOfMultiples(object):
    def __init__(self, *argv):
        if len(argv) == 0:
            self.numbers = (3,5)
        else:
            self.numbers = argv

    @staticmethod
    def multiples(base, n):
        return set(i for i in range(0, n, base))

    def to(self, n):
        return sum(set.union(*(self.multiples(base, n)
                               for base in self.numbers)))
