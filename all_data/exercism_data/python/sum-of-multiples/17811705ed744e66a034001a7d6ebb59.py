class SumOfMultiples:
    """Write a program that, given a number, can find the sum of all the multiples of 3 or 5 up to but not including that number."""
    
    def __init__(self, first = 3, second = 5, third = None):
        self.number_1 = first
        self.number_2 = second
        if third != None:
            self.number_3 = third
    
    def to(self, ceiling):
        return sum(n
                   for n in range(0, ceiling)
                   if any(n % number == 0
                   for number in vars(self).values()))
