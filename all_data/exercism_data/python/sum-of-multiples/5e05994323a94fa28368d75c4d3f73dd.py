class SumOfMultiples():
    
    def __init__(self, *args):
        
        defaultvals = (3, 5)
        
        if args:
            self.multiples = args
        else:
            self.multiples = defaultvals
            
    def to(self, limit):
        
       return sum([x for x in xrange(min(self.multiples),limit) 
           if any(x % multiple == 0  for multiple in self.multiples)])
