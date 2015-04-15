class SumOfMultiples:
    def __init__(self,*sums):
        if sums:
            self.sums = sums
        else:
            self.sums = [3,5,None]
            
    def to(self,num):
        count = 0
        for a in range(0,num,self.sums[0]):
            count += a
        for b in range(0,num,self.sums[1]):
            count += b
        try:
            for c in range(0,num,self.sums[2]):
                count += c
        except:
            pass
        for d in range(0,num):
            if d % self.sums[0] == 0 and d % self.sums[1] == 0:
                count -= d
        return count
