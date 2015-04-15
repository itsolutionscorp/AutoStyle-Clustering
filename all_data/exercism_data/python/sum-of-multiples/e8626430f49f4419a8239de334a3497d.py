class SumOfMultiples:
    def __init__(self, *args):
        self.vals = [3,5] if not args else list(map(int,args))

    def to(self, limit):
        return sum([i for i in range(limit) if not all([i % val for val in self.vals])])
