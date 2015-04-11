DEFAULT_MULTS = (3, 5)

class SumOfMultiples:

    def __init__(self, *args):
        
        if len(args) == 0:
            self.multiplcands = DEFAULT_MULTS
        else:
            self.multiplcands = args

    def divisable(self, num):
        for divisor in self.multiplcands:
            if num % divisor == 0:
                return num
        return 0

    def to(self, n):
        sum = 0
        for num in range(n):
            sum += self.divisable(num)
        return sum
