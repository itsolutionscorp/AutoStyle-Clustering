class SumOfMultiples:
    
    def __init__(self, *args):
        if not args:
            #default
            self.mult = [3, 5]
        else:
            self.mult = list(args)
    
    def to(self, n):
        acc = []
        for m in self.mult:
            acc += [ x for x in range(1, n) if not x % m ]
        return sum(list(set(acc)))
