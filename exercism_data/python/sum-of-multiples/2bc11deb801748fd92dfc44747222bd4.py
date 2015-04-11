class SumOfMultiples:
    def __init__(self, *args):
        self.vals = args or [3,5]
        
    def to(self, limit):
        return sum([i for i in range(limit) if any([i % val == 0 for val in self.vals])])
