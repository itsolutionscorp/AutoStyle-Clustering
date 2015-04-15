class SumOfMultiples:
    def __init__(self, *args):
        self.vals = [3,5] if not args else list(map(int,args))

    def to(self, limit):
        return sum([i for i in range(limit) if any([i % val == 0 for val in self.vals])])
