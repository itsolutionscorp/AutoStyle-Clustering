class SumOfMultiples:
    def __init__(self, *args):
        self.factors = args if args else [3,5]
    
    def to(self, n):
        s = set()
        for p in self.factors:
            s.update([i for i in range(p,n,p)])
        return sum(s)
