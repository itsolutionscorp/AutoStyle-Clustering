class SumOfMultiples:
    def __init__(self, *args):
        self.multiples = args or [3, 5]

    def to(self, limit):
        return sum(value for value in range(limit)
                   if any(not value % multiple for multiple in self.multiples))
