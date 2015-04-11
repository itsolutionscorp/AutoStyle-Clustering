class SumOfMultiples():
    
    def __init__(self,*mul):
        self.multiplesOf=mul or [3,5]


    def to(self,limit):
        multiples=[]
        for m in self.multiplesOf:
            multiples +=range(m,limit,m)
        return sum(set(multiples))
                
                
