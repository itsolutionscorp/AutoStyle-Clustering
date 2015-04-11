class SumOfMultiples():
    def __init__(self, *n):
        self.sum_of_multiples = 0
        self.limit = 0
        self.multiples = n if n else (3, 5)

    def to(self, n):
        self.limit = n
        return self.count()

    def count(self):
        for num in self.multiples:
            for multiples in range(num, self.limit, num):
                self.sum_of_multiples += multiples
        return self.sum_of_multiples
