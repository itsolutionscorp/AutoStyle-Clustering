class SumOfMultiples():
    multiples=[]
        
    def __init__(self,*mul):
        if( mul):
            self.multiples=mul
        else:
            self.multiples=[3,5]
   
    def to(self,limit):
        sumMul=0
        for i in range(1,limit):
            for m in self.multiples:        
                if((i%m) == 0 ):
                    sumMul+=i
        return sumMul
                
