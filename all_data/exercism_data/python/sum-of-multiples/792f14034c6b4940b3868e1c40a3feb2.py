class SumOfMultiples:
    def __init__(self, *args):
        if len(args):
            self.multiples = list(args)
        else:
            self.multiples = [3,5]
        
    def to(self, n):
        numbers = []
        for multiple in self.multiples:
            numbers += range(multiple, n, multiple)
        
        return sum(set(numbers))
