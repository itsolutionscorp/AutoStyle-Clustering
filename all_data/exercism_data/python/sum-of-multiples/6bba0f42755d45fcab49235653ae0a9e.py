from math import *

class SumOfMultiples:
    def __init__(self, *args):
        self.intervals = sorted(list(args), reverse=True) if args else [5, 3]
        
    def to(self, max_val):
        return sum(self.sum_series(max_val, n, self.intervals[:i])
                   for i, n in enumerate(self.intervals))
        
    def sum_series(self, max_val, interval, exclude=[]):
        num = max_val/interval - (max_val % interval == 0)
        return num*(num+1)/2 * interval - \
            sum(self.sum_series(max_val, self.lcm(interval, e)) for e in exclude)
        
    def lcm(self, n1, n2):
        x, y = (n1, n2)
        
        while y:
            x, y = y, x % y
            
        return n1 * n2 / x
