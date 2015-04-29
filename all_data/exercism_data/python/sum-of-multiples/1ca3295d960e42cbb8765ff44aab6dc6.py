class SumOfMultiples(object):
    def __init__(self,*s):
        if s == (): self.numbers = [3,5]
        else:
            for each in s: assert type(each) == int, "All inputs must be integers"
            self.numbers = s
        
    def to(self,i):
        assert type(i) == int, "Input must be an integer."
        return sum(value for value in range(i) if any(value % n == 0 for n in self.numbers))

