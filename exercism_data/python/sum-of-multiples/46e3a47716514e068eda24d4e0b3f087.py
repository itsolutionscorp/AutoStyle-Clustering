
class SumOfMultiples:
    def __init__(self, *multiples):
        self._multiples = multiples or [3, 5]

    def to(self, limit):
        return sum(n
                   for n in range(1, limit)
                   if any(n%m == 0 for m in self._multiples))
