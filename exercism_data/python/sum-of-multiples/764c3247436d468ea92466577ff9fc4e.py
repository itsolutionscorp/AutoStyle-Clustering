class SumOfMultiples:
    def __init__(self, *arg):
        if not arg:
            arg = [3, 5]
        
        self.multiples = arg

    def to(self, value):
        return sum(i for i in range(0, value) if any(i % multiple == 0 for multiple in self.multiples))
