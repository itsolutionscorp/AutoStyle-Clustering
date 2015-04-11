#William Morris
#exercism.io
#sum_of_multples.py

class SumOfMultiples:

    def __init__(self,m1=3,m2=5,m3=0):
        self.m1 = m1
        self.m2 = m2
        self.m3 = m3
        
    def recurs_mults(self,start,mult,limit):
        if start >= limit or mult == 0:
            return []
        else:
            return [start]+self.recurs_mults(start+mult,mult,limit)

    def to(self, limit):
        m1_list = self.recurs_mults(0,self.m1,limit)
        m2_list = [num for num in self.recurs_mults(0,self.m2,limit) if
                   num not in m1_list]
        m3_list = [num for num in self.recurs_mults(0,self.m3,limit) if
                   num not in m1_list and num not in m2_list]
        return sum(m1_list + m2_list + m3_list)
    
                                                               
