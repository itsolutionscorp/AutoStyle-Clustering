class SumOfMultiples:
    def __init__(self, *args):
        self.factors = [3,5]
        if args:
            self.factors = list(args)
    
    def to(self, n):
        s = set()
        for p in self.factors:
            s.update([i for i in range(p,n,p)])
        return sum(s)
