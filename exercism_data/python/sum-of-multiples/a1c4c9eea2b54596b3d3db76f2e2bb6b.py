class SumOfMultiples:
    def __init__(self, *multi):
        if not multi:
            self.multi = (3, 5)
        else:
            self.multi = multi

    def to(self, limit=0):
        self.sum = 0
        if limit > 1:
            for i in range(2, limit):
                for j in self.multi:
                    if i % j == 0:
                        self.sum += i
        return self.sum
