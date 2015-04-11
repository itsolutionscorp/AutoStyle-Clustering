class SumOfMultiples(object):
    def __init__(self, *mults):
        if not mults:
            mults = (3, 5)
        self.mults = mults

    def to(self, up):
        return sum(n for n in xrange(up) if any(n % i == 0 for i in self.mults))
