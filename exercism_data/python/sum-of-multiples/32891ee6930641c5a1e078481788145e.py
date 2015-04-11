
class SumOfMultiples():
    
    def  __init__(self,*args):
        if len(args) > 0:
            self.bases = list(args)
        else:
            self.bases = [3,5]
            
    def to(self, num):
        multiples = []
        for x in self.bases:
            n=1
            while (x*n) < num:
                if (x*n) not in multiples:
                    multiples.append(x*n)
                n += 1 
                     
        return sum(multiples) 
        
