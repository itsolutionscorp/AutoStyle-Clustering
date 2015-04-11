#William Morris
#exercism.io
#sum_of_multples.py

class SumOfMultiples:

    def __init__(self,*factors):
        if not factors: factors = (3,5)
        self.factors = factors
        
    def recurs_mults(self,start,mult,limit):
        if start >= limit or mult == 0:
            return []
        else:
            return [start]+self.recurs_mults(start+mult,mult,limit)

    def to(self, limit):
        multiples = set()
        for factor in self.factors:
            multiples = multiples.union(self.recurs_mults(0,factor,limit))
        return sum(multiples)
    
                                                               
