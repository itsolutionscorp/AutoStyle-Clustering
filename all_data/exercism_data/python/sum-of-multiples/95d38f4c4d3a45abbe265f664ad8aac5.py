class SumOfMultiples:
    def __init__(self, f1 = 3, f2 = 5, f3 = 0):
        self.f1 = f1
        self.f2 = f2
        self.f3 = f3

    def to(self, limit):
        if self.f3:
            return sum([i for i in range(limit) if not (i % self.f1 and i % self.f2 and i % self.f3)])
        else:
            return sum([i for i in range(limit) if not (i % self.f1 and i % self.f2)])
