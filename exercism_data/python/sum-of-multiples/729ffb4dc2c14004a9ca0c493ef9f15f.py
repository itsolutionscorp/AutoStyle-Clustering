class SumOfMultiples:
    
    def __init__(self, *arg):
        if len(arg) == 0:
            self.prods = (3, 5)
        else:
            self.prods = arg
        

    def to(self, n):
        sum_of_multiples = 0
        lcm = 1        
        for i in self.prods:
            sum_of_multiples += sum(xrange(i, n, i))
            lcm *= i
        if lcm < n:
            sum_of_multiples -= sum(xrange(lcm, n, lcm))
        return sum_of_multiples

#this will work correctly ONLY if the arguments passed to the class are
#prime numbers - self.prods should be a tuple containing only primes
