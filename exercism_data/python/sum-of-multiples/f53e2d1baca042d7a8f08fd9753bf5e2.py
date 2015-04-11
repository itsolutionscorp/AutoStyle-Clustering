class SumOfMultiples:
    def __init__(self, *args):
        if args==():
            self.multiples = [3, 5]
        else:
            self.multiples = [arg for arg in args] 

    def to(self, maxval):
        values = []
        for value in self.multiples:
            values += range(value, maxval, value) 
        return sum(set(values))
