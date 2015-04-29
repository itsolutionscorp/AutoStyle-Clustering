class SumOfMultiples(object):
    factors = []

    def __init__(self, *factors):
        if len(factors) == 0:
            self.factors = [3, 5]
        else:
            self.factors = factors

    def to(self, upper):
        multiples = []
        for num in xrange(upper):
            for factor in self.factors:
                if num % factor == 0:
                    multiples.append(num)
                    break
        if len(multiples) == 0:
            return 0
        else:
            return sum(multiples)
