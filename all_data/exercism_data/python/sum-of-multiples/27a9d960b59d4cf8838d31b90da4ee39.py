class SumOfMultiples(object):
    def __init__(self, n1=3, n2=5, *nn):
        self.factors = [n1, n2] + list(nn)
    
    def to(self,n):
        return sum(x for x in range(1,n) if self._is_multiple(x))
    
    def _is_multiple(self, n):
        return any((n % x == 0) for x in self.factors)
