#
#
#

class SumOfMultiples:
    def __init__(self,*arg):
        if not arg:
            self.m_numbers = (3,5)
        else:
            self.m_numbers = arg

    def to(self,n):
        sum = 0
        for i in range(0,n):
            is_divisor = False
            # Test if i is a divisor of at least one quotient
            for q in self.m_numbers:
                is_divisor = ((i % q) == 0) or is_divisor
            if is_divisor:
                sum += i
        return sum
