class SumOfMultiples:

    DEFAULT_MULTS = (3, 5)

    def __init__(self, *args):
        self.multiplcands = args or DEFAULT_MULTS


    def to(self, n):
        divisable = lambda x : any( not(x % div) for div in self.multiplcands)
        return sum([num for num in range(n) if divisable(num)])
