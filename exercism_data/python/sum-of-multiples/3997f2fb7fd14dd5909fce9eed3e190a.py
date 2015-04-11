def divisible(a,b):
    """returns True if a is evenly divisible by b"""
    return not bool(a%b)  # returns True if a = k * b


class SumOfMultiples():
    """Class for exercism.io "sum_of_multiples" exercise."""
    def __init__(self,*factors):
        if factors:  # explicit factors were given
            self.factors = factors
        else:  # revert to default = (3,5)
            self.factors = (3,5)

    def to(self,n):
        # comprehend sum of numbers below n if divisible by any factor
        return sum( n for n in range(n)
                    if any(divisible(n,factor) for factor in self.factors) )
