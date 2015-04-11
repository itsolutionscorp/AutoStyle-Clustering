class SumOfMultiples:


    def __init__(self,*args):
        if len(args) != 0:
            self.multiples = args
        else:
            self.multiples = (3,5)

    def to(self,limit):
        matches = set()            
        for x in self.multiples:
            for y in range(limit):
                if y % x == 0:
                    matches.add(y)
        return sum(matches)        
