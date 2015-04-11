class SumOfMultiples(object):
    def __init__(self, *args):
        self.multiples = args or (3, 5)

    def _check(self, n):
        return any(n % i == 0 for i in self.multiples)

    def to(self, num):
        return sum(i for i in range(num) if self._check(i))
