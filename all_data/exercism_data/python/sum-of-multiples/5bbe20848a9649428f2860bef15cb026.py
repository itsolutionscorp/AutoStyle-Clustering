class SumOfMultiples:

    def __init__(self,*arg):
        if arg:
            self.mult=arg
        else:
            self.mult=(3,5)
    
    def to(self,limit):

        multi=self.mult
        sumlist=[]

        for m in multi:
            maxno=int((limit-1)/m)  #highest factor where the multiple is below the limit
            sumlist += list(map(lambda x: x*m,range(maxno+1))) #apend a list of all multiples for the given multiple m up to maxno

        return sum(set(sumlist))
