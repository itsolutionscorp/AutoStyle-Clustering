from operator import mul
class SumOfMultiples:
    def __init__(self, *multiples):
        if not multiples:
            self.mult = (3,5)
        else:
            self.mult = multiples
            
            
    def to(self,n):
        rng = range(1,n)
        mult = self.mult
        all = sum([x for x in rng for y in mult if x%y == 0])
        common = sum([x for x in rng if x%(reduce(mul,mult,1))==0])
        return all - common
        
    
