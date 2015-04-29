class SumOfMultiples:
    def __init__(self, *arg):
        if len(arg) > 0:
            self.ns = arg
        else:
            self.ns = [3,5]        
    def to(self,limit):
        return sum(i for i in range(limit) if any(i % n == 0 for n in self.ns))


