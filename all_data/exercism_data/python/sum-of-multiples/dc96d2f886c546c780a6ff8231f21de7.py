class SumOfMultiples():
    def __init__(self, *argv):
        if len(argv) == 0:
            self.divs = [3, 5]
        else:
            self.divs = argv

    def to(self, n):
        s = 0
        for i in xrange(n):
            for j in self.divs:
                if i % j == 0:
                    s += i
                    break
        return s

