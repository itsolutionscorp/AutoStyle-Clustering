class SumOfMultiples:
    def __init__(self, *arg):
        self.divisors = arg or [3, 5]

    def is_divisible(self, number):
        return len([divisor for divisor in self.divisors if number % divisor == 0])

    def to(self, number):
        multiples = [x for x in range(1, number) if self.is_divisible(x)]
        return sum(multiples) or 0
