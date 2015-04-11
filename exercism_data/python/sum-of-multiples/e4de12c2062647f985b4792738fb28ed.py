class SumOfMultiples:
    def __init__(self, *args, **kwargs):
        self.mults = args or [3, 5]
        
    def to(self, num):
        is_div = lambda i: any(i % n == 0 for n in self.mults)
        return sum(filter(is_div, range(1, num)))
