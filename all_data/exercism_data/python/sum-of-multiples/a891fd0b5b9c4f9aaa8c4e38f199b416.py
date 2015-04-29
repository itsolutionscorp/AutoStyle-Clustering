class SumOfMultiples():
    def __init__(self, *factors):
        self.factors = factors or [3,5]
        
    def to(self, n):
        multiples = set()
        for x in self.factors:
            i = 1
            while x*i < n:
                multiples.add(x*i)
                i += 1
        return sum(multiples)
