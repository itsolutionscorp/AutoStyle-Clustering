class SumOfMultiples:
    divisor_list = [3,5]
    
    def __init__(self,*arg):
        if len(arg) > 0:
            self.divisor_list = list(arg)
        
    def to(self,limit):
        c = []
        for x in range(1,limit):
            for d in self.divisor_list:
                if x % d == 0 and x not in c:
                    c.append(x)
        return sum(c)
