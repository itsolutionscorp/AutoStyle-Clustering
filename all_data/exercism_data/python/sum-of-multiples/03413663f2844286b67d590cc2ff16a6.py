import functools

class SumOfMultiples:
    def __init__(self, *multiples):
        self.multiples = list(multiples) or [3, 5]

    def to(self, n):
       multiples = filter(self._is_multiple, range(1, n))
       return functools.reduce((lambda x, y: x + y), multiples, 0)

    def _is_multiple(self, n):
        return any([ n % m == 0 for m in self.multiples])
