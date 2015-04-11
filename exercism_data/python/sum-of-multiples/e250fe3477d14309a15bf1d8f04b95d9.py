class SumOfMultiples():
    def __init__(self,*arg):
        if arg is ():
            self.multis = (3,5)
        else:
            self.multis = arg
    
    def to(self,num):
        return sum(n for n in xrange(num) if 0 in (n % d for d in self.multis))
