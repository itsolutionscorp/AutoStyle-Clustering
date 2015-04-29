
class SumOfMultiples(object):
    def __init__(self, *divisors):
        self._divisors = divisors or (3, 5)
    
    def _check(self, n):
        return any(map(lambda d: n % d == 0, self._divisors))

    def to(self, limit):
        return sum(filter(self._check, range(1, limit)))
